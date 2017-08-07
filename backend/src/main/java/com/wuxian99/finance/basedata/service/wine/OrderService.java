package com.wuxian99.finance.basedata.service.wine;

import com.wuxian99.finance.basedata.domain.OrderDetailEntity;
import com.wuxian99.finance.basedata.domain.OrderEntity;
import com.wuxian99.finance.basedata.repository.wine.OrderDetailRepository;
import com.wuxian99.finance.basedata.repository.wine.OrderRepository;
import com.wuxian99.finance.basedata.service.pay.WxPayService;
import com.wuxian99.finance.basedata.web.dto.QueryOrderListDto;
import com.wuxian99.finance.basedata.web.view.OrderMdseView;
import com.wuxian99.finance.basedata.web.view.OrderView;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Value("${wine.picPath}")
    private String picPath;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    private WxPayService wxPayService;

    public OrderEntity createOrder(OrderEntity order, List<OrderDetailEntity> details) {
        order = orderRepository.save(order);
        for(OrderDetailEntity detail : details){
            detail.setOrderId(order.getId());
        }
        orderDetailRepository.save(details);
        return order;
    }

    public OrderEntity updateOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    public String cancelOrder(Long orderId){
        OrderEntity order = orderRepository.findOne(orderId);
        if(order == null){
            return "订单不存在";
        }
        if(order.getStatus() == 0){
            return null;
        }else if(order.getStatus() == 2 || order.getStatus() == 3){
            return "订单已支付，不能取消";
        }else if(order.getStatus() == 1){
            order.setStatus(0L);
            orderRepository.save(order);
            return null;
        }else{
            return "订单状态异常";
        }
    }

    public OrderEntity findOrderById(Long orderId){
        return orderRepository.findOne(orderId);
    }

    public OrderView findOrderViewById(Long orderId){
        OrderEntity order = orderRepository.findOne(orderId);
        if(order == null){
            return null;
        }else{
            OrderView view = new OrderView();
            view.setOrderId(order.getId());
            view.setOrderTime(order.getTime());
            view.setAmount(order.getAmount());
            view.setStatus(order.getStatus());
            view.setMdseCount(order.getMdseCount());

            //收货地址
            view.setAddress(order.getAddress());
            view.setPhone(order.getPhone());
            view.setReceiver(order.getReceiver());
            view.setProvince(order.getProvince());

            //物流信息
            view.setLogisticsCompany(order.getLogisticsCompany());
            view.setLogisticsSeqs(order.getLogisticsSeqs());

            //发票信息
            view.setInvoiceInfo(order.getInvoiceInfo());

            //买家备注
            view.setComment(order.getComment());

            //商品信息
            List<OrderDetailEntity>  details = orderDetailRepository.findByOrderId(order.getId());
            if(CollectionUtils.isNotEmpty(details)){
                List<OrderMdseView> orderMdseViews = new ArrayList<>();
                for(OrderDetailEntity detail : details){
                    OrderMdseView orderMdseView = new OrderMdseView();
                    orderMdseView.setCount(detail.getCount());
                    orderMdseView.setName(detail.getMdseName());
                    orderMdseView.setPrice(detail.getPrice());
                    orderMdseView.setPic(picPath + detail.getMdseSmallPic());
                    orderMdseViews.add(orderMdseView);
                }
                view.setMdseInfos(orderMdseViews);
            }
            return view;
        }
    }

    public Page<OrderEntity> findOrders(QueryOrderListDto paras){
        Sort sort = new Sort(Sort.Direction.DESC,"time");
        PageRequest pageRequest = paras.convert(sort);
        return orderRepository.findAll(new Specification<OrderEntity>() {
            public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> merchantIdPath = root.get("userId");
                Predicate predicate = cb.equal(merchantIdPath, paras.getUserId());
                Path<Long> status = root.get("status");
                if(paras.getStatus() == 1L){
                    predicate = cb.and(predicate, cb.le(status, 1L));
                }else if(paras.getStatus() == 2L){
                    predicate = cb.and(predicate, cb.ge(status, 2L));
                }
                query.where(predicate);
                return query.getRestriction();
            }
        }, pageRequest);

    }

    public List<OrderDetailEntity> findOrderDetail(long orderId){
        return orderDetailRepository.findByOrderId(orderId);
    }

    //订单过期自动关闭处理线程
    private ExecutorService expiredExecuters;

    //订单支付状态查询线程
    private ExecutorService payingExecuters;

    //订单支付状态查询队列
    private BlockingQueue<OrderEntity> payingQueue;

    @PostConstruct
    private void init() {
        this.expiredExecuters = Executors.newSingleThreadExecutor();
        this.startExpiredThread();

        this.payingExecuters = Executors.newFixedThreadPool(5);
        this.payingQueue = new LinkedBlockingQueue<OrderEntity>();
        this.startPayingThread();
    }

    @PreDestroy
    private void destroy() {
        this.expiredExecuters.shutdown();
        this.payingExecuters.shutdown();
        this.payingQueue = null;
    }

    /**
     * 启动订单过期自动关闭线程
     */
    private void startExpiredThread(){
        this.expiredExecuters.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try{
                        logger.info("开始执行过期订单自动关闭任务...");
                        int count = closeExpiredOrders();
                        logger.info("自动关闭 {} 个已过期订单", count);
                    }catch(Exception ex){
                        logger.error("订单过期自动关闭异常", ex);
                    }
                    try{Thread.sleep(60*1000);}catch(Exception ex){};
                }
            }
        });
    }

    @Transactional
    public int closeExpiredOrders(){
        //订单30分钟未支付，自动关闭
        Date expiredTime = new Date(System.currentTimeMillis() - 30*60*1000);
        List<OrderEntity> orders = orderRepository.findExpiredOrders(new SimpleDateFormat("yyyyMMddHHmmss").format(expiredTime));
        if(CollectionUtils.isEmpty(orders)){
            return 0;
        }else{
            for(OrderEntity order : orders){
                order.setStatus(0L);
            }
            orderRepository.save(orders);
            return orders.size();
        }
    }

    /**
     * 启动支付状态查询线程
     */
    private void startPayingThread(){
        this.payingExecuters.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    OrderEntity order = null;
                    try{
                        order = popPayingOrder();
                        if(order != null){
                            //每10秒查询一次
                            if(System.currentTimeMillis() - order.getLastQueryPayStatusTime() > 10000){
                                queryPayStatus(order);
                            }else{
                                //未到查询时间，扔回队列
                                pushPayingQueue(order);
                            }
                        }
                    }catch(Exception e){
                        logger.error("同步支付状态异常, order:{}", order, e);
                    }
                    try{Thread.sleep(1000);}catch(Exception e){};
                }
            }
        });
    }

    private OrderEntity popPayingOrder() throws Exception{
        return this.payingQueue.take();
    }

    public void pushPayingQueue(OrderEntity order){
        try{
            boolean r = this.payingQueue.offer(order, 10, TimeUnit.SECONDS);
            if(!r){
                logger.error("订单放入支付状态同步队列失败, 当前队列长度:{}, order:{}", this.payingQueue.size(), order);
            }
        }catch(Exception e){
            logger.error("订单放入支付状态同步队列失败异常, 当前队列长度:{}, order:{}", this.payingQueue.size(), order, e);
        }
    }

    private void queryPayStatus(OrderEntity order) {

        if(orderRepository.findOne(order.getId()).getStatus() != 1){
            return;
        }

        OrderEntity result = wxPayService.queryPayStatus(order);
        if(result != null && result.getStatus() == 2L){
            //支付成功，更新订单
            orderRepository.save(result);
            logger.info("查询到支付结果，更新订单支付状态，order:{}", order);
        }else if(order.getQueryPayStatusCount() < 6*30){
            //待支付，未超过30分钟，继续放入支付状态同步队列
            order.setQueryPayStatusCount(order.getQueryPayStatusCount() + 1);
            pushPayingQueue(order);
        }else{
            //超过10分钟，还未支付完成，不再查询支付状态
        }
    }

}
