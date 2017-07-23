package com.wuxian99.finance.basedata.service.wine;

import com.wuxian99.finance.basedata.domain.OrderDetailEntity;
import com.wuxian99.finance.basedata.domain.OrderEntity;
import com.wuxian99.finance.basedata.domain.UserAddressEntity;
import com.wuxian99.finance.basedata.repository.wine.OrderDetailRepository;
import com.wuxian99.finance.basedata.repository.wine.OrderRepository;
import com.wuxian99.finance.basedata.repository.wine.UserAddressRepository;
import com.wuxian99.finance.basedata.web.dto.QueryOrderListDto;
import com.wuxian99.finance.basedata.web.view.OrderMdseView;
import com.wuxian99.finance.basedata.web.view.OrderListView;
import com.wuxian99.finance.basedata.web.view.OrderView;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Value("${wine.picPath}")
    private String picPath;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    UserAddressRepository userAddressRepository;

    public OrderEntity createOrder(OrderEntity order, List<OrderDetailEntity> details) {
        order = orderRepository.save(order);
        for(OrderDetailEntity detail : details){
            detail.setOrderId(order.getId());
        }
        orderDetailRepository.save(details);
        return order;
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
            view.setReciver(order.getReciver());
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
        if(paras.getStatus() == 1L){
            return orderRepository.findByUserIdAndStatus(paras.getUserId(), 0L, 1L, pageRequest);
        }else if(paras.getStatus() == 2L){
            return orderRepository.findByUserIdAndStatus(paras.getUserId(), 2L, 3L, pageRequest);
        }else{
            return null;
        }
    }

    public List<OrderDetailEntity> findOrderDetail(long orderId){
        return orderDetailRepository.findByOrderId(orderId);
    }

}
