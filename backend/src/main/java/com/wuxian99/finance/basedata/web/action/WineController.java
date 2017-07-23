package com.wuxian99.finance.basedata.web.action;

import com.alibaba.fastjson.JSON;
import com.wuxian99.finance.basedata.domain.*;
import com.wuxian99.finance.basedata.service.pay.WxPayService;
import com.wuxian99.finance.basedata.service.wine.*;
import com.wuxian99.finance.basedata.support.util.StringUtils;
import com.wuxian99.finance.basedata.web.dto.*;
import com.wuxian99.finance.basedata.web.view.*;
import com.wuxian99.finance.common.Result;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/front")
public class WineController {

    /**
     * 图片存储规则：
     *  Banner图         :       酒庄编号/banner/时间戳.png
     *  商品列表小图      :       酒庄编号/mdse/时间戳_small.png
     *  商品详情大图      :       酒庄编号/mdse/时间戳_big.png
     *  商品品鉴长图      :       酒庄编号/mdse/时间戳_story.png
     *  发现/动态列表图   :       酒庄编号/discover/时间戳.png
     *  发现/动态详情图   :       酒庄编号/discover/时间戳_序号.png
     */
    @Value("${wine.picPath}")
    private String picPath;

    private Map<String, String> verifyCodeCache = new HashMap<String, String>();

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private DiscoverService discoverService;

    @Autowired
    private MdseService mdseService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    /**
     * 获取首页Banner
     * @param merchantId
     * @return
     */
    @RequestMapping(value="getBanners/{merchantId}", method={RequestMethod.GET})
    public Result<List<BannerListView>> getBanners(@PathVariable String merchantId){
        List<BannerEntity> banners =  bannerService.findByMerchantId(merchantId);
        List<BannerListView> bannerViews = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(banners)){
            for(BannerEntity banner : banners){
                BannerListView view = new BannerListView();
                view.setPic(picPath + banner.getPic());
                view.setMdseId(banner.getMdseId());
                bannerViews.add(view);
            }
        }
        return Result.buildSuccess(bannerViews);
    }

    /**
     * 获取首页发现、动态列表
     * @param paras
     * @return
     */
    @RequestMapping(value="getDiscovers", method={RequestMethod.POST})
    public Result<List<DiscoverListView>> getDiscovers(@RequestBody QueryDiscoverListDto paras){
        Page<DiscoverEntity> page =  discoverService.findDiscovers(paras);
        List<DiscoverListView> discoverListViews = new ArrayList<DiscoverListView>();
        if(CollectionUtils.isNotEmpty(page.getContent())){
            for(DiscoverEntity discover : page.getContent()){
                DiscoverListView discoverListView = new DiscoverListView();
                discoverListView.setDiscoverId(discover.getId());
                discoverListView.setPic(picPath + discover.getPic());
                discoverListView.setTitle(discover.getTitle());
                discoverListView.setTag(discover.getTag());
                discoverListView.setDescription(discover.getDescription());
                discoverListViews.add(discoverListView);
            }
        }
        return Result.buildSuccess(discoverListViews, page.getTotalElements());
    }

    /**
     * 获取发现、动态详情
     * @param discoverId
     * @return
     */
    @RequestMapping(value="getDiscoverDetails/{discoverId}", method={RequestMethod.GET})
    public Result<List<DiscoverDetailEntity>> getDiscoverDetails(@PathVariable Long discoverId){
        List<DiscoverDetailEntity> discoverDetails =  discoverService.findDetailByDiscoverId(discoverId);
        if(CollectionUtils.isNotEmpty(discoverDetails)){
            for(DiscoverDetailEntity discoverDetail : discoverDetails){
                discoverDetail.setPic(picPath + discoverDetail.getPic());
            }
        }
        return Result.buildSuccess(discoverDetails);
    }

    /**
     * 获取商品列表
     * @param paras
     * @return
     */
    @RequestMapping(value="getMdses", method={RequestMethod.POST})
    public Result<List<MdseListView>> getMdses(@RequestBody QueryMdseListDto paras){
        Page<MdseEntity> page = mdseService.findMdses(paras);
        List<MdseListView> mdseListViews = new ArrayList<MdseListView>();
        if(CollectionUtils.isNotEmpty(page.getContent())){
            for(MdseEntity mdse : page.getContent()){
                MdseListView mdseListView = new MdseListView();
                mdseListView.setMdseId(mdse.getId());
                mdseListView.setName(mdse.getName());
                mdseListView.setNameEn(mdse.getNameEn());
                mdseListView.setSmallPic(picPath + mdse.getSmallPic());
                mdseListView.setPrice(mdse.getPrice());
                mdseListView.setYear(mdse.getYear());
                mdseListViews.add(mdseListView);
            }
        }
        return Result.buildSuccess(mdseListViews, page.getTotalElements());
    }

    /**
     * 获取商品详情
     * @param mdseId
     * @return
     */
    @RequestMapping(value="getMdseDetail/{mdseId}", method={RequestMethod.POST,RequestMethod.GET})
    public Result<MdseEntity> getMdseDetail(@PathVariable Long mdseId){
        MdseEntity mdse =  mdseService.findMdseById(mdseId);
        if(mdse != null){
            MdseView view = new MdseView();
            view.setMdseId(mdse.getId());
            view.setName(mdse.getName());
            view.setNameEn(mdse.getNameEn());
            view.setPrice(mdse.getPrice());
            view.setWineType(mdse.getWineType());
            view.setGrapeType(mdse.getGrapeType());
            view.setYear(mdse.getYear());
            view.setDegree(mdse.getDegree());
            view.setMl(mdse.getMl());
            view.setTreeAge(mdse.getTreeAge());
            view.setWineMaker(mdse.getWineMaker());
            view.setMerchantName(mdse.getMerchantName());
            view.setProductArea(mdse.getProductArea());
            view.setReason(mdse.getReason());
            view.setSmallPic(picPath + mdse.getSmallPic());
            view.setBigPic1(picPath + mdse.getBigPic1());
            view.setBigPic2(picPath + mdse.getBigPic2());
            view.setBigPic3(picPath + mdse.getBigPic3());
            view.setBigPic4(picPath + mdse.getBigPic4());
            view.setStoryPic(picPath + mdse.getStoryPic());
            return Result.buildSuccess(mdse);
        }else{
            return Result.buildFail("商品不存在");
        }
    }

    /**
     * 发送验证码
     * @param userName
     * @param type 1:登录，2:修改密码
     * @return
     */
    @RequestMapping(value="sendVerifyCode/{userName}/{type}", method={RequestMethod.POST,RequestMethod.GET})
    public Result<String> sendVerifyCode(@PathVariable String userName, @PathVariable Long type){
        if(StringUtils.isBlank(userName) || !StringUtils.isNumeric(userName) || userName.length() != 11){
            return Result.buildFail("手机号不正确");
        }
        if(type != 1 && type != 2){
            return Result.buildFail("验证码类型不正确");
        }
        String verifyCode = "";
        for(int i=0; i<4; i++){
            verifyCode = verifyCode + new Random().nextInt(10);
        }
        verifyCodeCache.put(userName + "_" + type, verifyCode);
        //TODO 发送短信验证码
        return Result.buildSuccess(verifyCode);
    }

    /**
     * 用户登录
     * @param paras
     * @return
     */
    @RequestMapping(value="login", method={RequestMethod.POST})
    public Result<UserInfoDto> sendVerifyCode(@RequestBody LoginDto paras){
        String userName = paras.getUserName();
        if(StringUtils.isBlank(userName) || !StringUtils.isNumeric(userName) || userName.length() != 11){
            return Result.buildFail("用户名不正确");
        }
        Long type = paras.getType();
        if(type != 1 && type != 2){
            return Result.buildFail("登录类型不正确");
        }
        UserEntity user = userService.findByUserName(userName);

        //验证码登录
        if(type == 2L){
            if(!StringUtils.equals(verifyCodeCache.get(userName + "_1"), paras.getPassword())){
                return Result.buildFail("短信验证码不正确");
            }
            if(user == null){
                user = new UserEntity();
                user.setUserName(userName);
                user.setStatus(1L);
                user.setType(2L);
                user.setBalance(0L);
                user.setGender("男");
                user.setBirthday("1990-01-01");
                if(paras.getParentId() != null && paras.getParentId() != 0) {
                    user.setParentId(paras.getParentId());
                }
                user = userService.saveOrUpdateUser(user);
            }
        }else{
            if(user == null){
                return Result.buildFail("用户名不存在");
            }else if(!StringUtils.equals(user.getPassword(), paras.getPassword())){
                return Result.buildFail("密码不正确");
            }
        }
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserId(user.getId());
        userInfoDto.setUserName(user.getUserName());
        userInfoDto.setType(user.getType());
        userInfoDto.setBalance(user.getBalance());
        userInfoDto.setRealName(user.getRealName());
        userInfoDto.setGender(user.getGender());
        userInfoDto.setBirthday(user.getBirthday());
        return Result.buildSuccess(userInfoDto);
    }

    /**
     * 修改登录密码
     * @param paras
     * @return
     */
    @RequestMapping(value="modifyPwd", method={RequestMethod.POST})
    public Result<String> sendVerifyCode(@RequestBody ModifyPwdDto paras){
        if(StringUtils.isBlank(paras.getPassword())){
            return Result.buildFail("新密码不能为空");
        }
        UserEntity user = userService.findByUserId(paras.getUserId());
        if(user == null){
            return Result.buildFail("用户ID不正确");
        }
        if(!StringUtils.equals(verifyCodeCache.get(user.getUserName() + "_2"), paras.getVerifyCode())){
            return Result.buildFail("短信验证码不正确");
        }
        user.setPassword(paras.getPassword());
        userService.saveOrUpdateUser(user);
        return Result.buildSuccess("");
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value="getUserInfo/{userId}", method={RequestMethod.POST,RequestMethod.GET})
    public Result<UserInfoDto> getUserInfo(@PathVariable Long userId) {
        UserEntity user = userService.findByUserId(userId);
        if (user == null) {
            return Result.buildFail("用户ID不正确");
        }
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserId(user.getId());
        userInfoDto.setUserName(user.getUserName());
        userInfoDto.setType(user.getType());
        userInfoDto.setBalance(user.getBalance());
        userInfoDto.setRealName(user.getRealName());
        userInfoDto.setGender(user.getGender());
        userInfoDto.setBirthday(user.getBirthday());
        return Result.buildSuccess(userInfoDto);
    }

    /**
     * 修改用户信息
     * @param paras
     * @return
     */
    @RequestMapping(value="modifyUserInfo", method={RequestMethod.POST})
    public Result<UserInfoDto> modifyUserInfo(@RequestBody UserInfoDto paras){
        UserEntity user = userService.findByUserId(paras.getUserId());
        if(user == null){
            return Result.buildFail("用户ID不正确");
        }
        user.setRealName(paras.getRealName());
        user.setGender(paras.getGender());
        user.setBirthday(paras.getBirthday());
        user = userService.saveOrUpdateUser(user);

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserId(user.getId());
        userInfoDto.setUserName(user.getUserName());
        userInfoDto.setType(user.getType());
        userInfoDto.setBalance(user.getBalance());
        userInfoDto.setRealName(user.getRealName());
        userInfoDto.setGender(user.getGender());
        userInfoDto.setBirthday(user.getBirthday());
        return Result.buildSuccess(userInfoDto);
    }

    /**
     * 获取收货地址列表
     * @param paras
     * @return
     */
    @RequestMapping(value="getUserAddresses", method={RequestMethod.POST,RequestMethod.GET})
    public Result<List<UserAddressDto>> getUserAddresses(@RequestBody QueryUserAddressListDto paras){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        PageRequest pageRequest = paras.convert(sort);
        Page<UserAddressEntity> page = userService.findUserAddressesByUserId(paras.getUserId(), pageRequest);
        List<UserAddressDto> views = new ArrayList<UserAddressDto>();
        if(CollectionUtils.isNotEmpty(page.getContent())){
            for (UserAddressEntity address:page.getContent()){
                UserAddressDto view = new UserAddressDto();
                view.setAddressId(address.getId());
                view.setIsDefualt(address.getIsDefualt());
                view.setReciver(address.getReciver());
                view.setPhone(address.getPhone());
                view.setProvince(address.getProvince());
                view.setAddress(address.getAddress());
                views.add(view);
            }
        }
        return Result.buildSuccess(views, page.getTotalElements());
    }

    /**
     * 新增或修改用户收货地址信息
     * @param paras
     * @return
     */
    @RequestMapping(value="modifyUserAddress", method={RequestMethod.POST})
    public Result<Long> modifyUserAddress(@RequestBody UserAddressDto paras){
        Page<UserAddressEntity> allAddr = userService.findUserAddressesByUserId(paras.getUserId(), null);
        UserAddressEntity address = new UserAddressEntity();
        //如果为用户的第一个地址，自动设置为默认地址
        if(CollectionUtils.isEmpty(allAddr.getContent())){
            address.setIsDefualt(1L);
        }else{
            address.setIsDefualt(paras.getIsDefualt());
        }
        address.setUserId(paras.getUserId());
        address.setPhone(paras.getPhone());
        address.setReciver(paras.getReciver());
        address.setProvince(paras.getProvince());
        address.setAddress(paras.getAddress());
        if(paras.getAddressId() != null && paras.getAddressId().longValue() != 0L){
            address.setId(paras.getAddressId());
        }
        address = userService.saveOrUpdateUserAddress(address);

        //如果本次新增/修改的地址为默认地址，把本来是默认地址的改为非默认
        if(address.getIsDefualt().longValue() == 1L){
            if(CollectionUtils.isNotEmpty(allAddr.getContent())) {
                for (UserAddressEntity addr : allAddr.getContent()) {
                    if (addr.getId().longValue() != address.getId().longValue() && addr.getIsDefualt().longValue() == 1L) {
                        addr.setIsDefualt(0L);
                        userService.saveOrUpdateUserAddress(addr);
                    }
                }
            }
        }
        return Result.buildSuccess(address.getId());
    }


    /**
     * 下单
     * @param paras
     * @return
     */
    @RequestMapping(value="createOrder", method={RequestMethod.POST})
    public Result<OrderView> createOrder(@RequestBody CreateOrderDto paras){
        if(paras.getUserId() == null || paras.getAddressId() == null || paras.getAddressId() == null || paras.getMdseInfo() == null){
            return Result.buildFail("必填参数不能为空");
        }
        OrderEntity order = new OrderEntity();
        List<OrderDetailEntity> details = new ArrayList<OrderDetailEntity>();
        order.setUserId(paras.getUserId());
        order.setMerchantId(paras.getMerchantId());
        order.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        order.setStatus(1L);
        order.setComment(paras.getComment());
        order.setInvoiceInfo(paras.getInvoiceInfo());
        String[] mdses = paras.getMdseInfo().split(",");
        Long amount = 0L;
        Long allCount = 0L;
        for(String mdseStr : mdses){
            if(StringUtils.isBlank(mdseStr)){
                continue;
            }
            String mdseIdStr = mdseStr.split(":")[0];
            String countSrt = mdseStr.split(":")[1];
            MdseEntity mdse = mdseService.findMdseById(Long.parseLong(mdseIdStr));
            if(mdse == null){
                return Result.buildFail("商品不存在:" +mdseIdStr);
            }
            if(mdse.getStatus() == 0L){
                return Result.buildFail("商品已下架:" + mdseIdStr);
            }
            if(mdse.getStatus() == 2L){
                return Result.buildFail("商品已售罄:" + mdseIdStr);
            }
            OrderDetailEntity detail = new OrderDetailEntity();
            detail.setMdseId(mdse.getId());
            detail.setPrice(mdse.getPrice());
            detail.setCount(Long.parseLong(countSrt));
            detail.setMdseName(mdse.getName());
            detail.setMdseSmallPic(mdse.getSmallPic());
            details.add(detail);

            amount = amount + (detail.getCount()*detail.getPrice());
            allCount = allCount + detail.getCount();
        }
        order.setAmount(amount);
        order.setPayAmount(amount);
        order.setMdseCount(allCount);

        //收货地址
        UserAddressEntity userAddress = userService.findUserAddressById(paras.getAddressId());
        if(userAddress == null){
            return Result.buildFail("收货地址不存在");
        }
        order.setAddress(userAddress.getAddress());
        order.setPhone(userAddress.getPhone());
        order.setProvince(userAddress.getProvince());
        order.setReciver(userAddress.getReciver());

        order = orderService.createOrder(order, details);

        OrderView view = new OrderView();
        view.setOrderId(order.getId());
        view.setAmount(order.getAmount());
        return Result.buildSuccess(view);
    }

    /**
     * 获取订单列表
     * @param paras
     * @return
     */
    @RequestMapping(value="getOrders", method={RequestMethod.POST,RequestMethod.GET})
    public Result<List<OrderListView>> getOrders(@RequestBody QueryOrderListDto paras){
        List<OrderListView> views = new ArrayList<>();
        Page<OrderEntity> page = orderService.findOrders(paras);
        if(CollectionUtils.isNotEmpty(page.getContent())){
            for(OrderEntity order : page.getContent()){
                OrderListView view = new OrderListView();
                view.setAmount(order.getAmount());
                view.setMdseCount(order.getMdseCount());
                view.setOrderId(order.getId());
                view.setOrderTime(order.getTime());
                view.setStatus(order.getStatus());

                //订单商品信息
                List<OrderDetailEntity> details = orderService.findOrderDetail(order.getId());
                if(CollectionUtils.isNotEmpty(details)){
                    List<OrderMdseView> orderMdseViews = new ArrayList<>();
                    for(OrderDetailEntity detail : details){
                        OrderMdseView orderMdseView = new OrderMdseView();
                        orderMdseView.setName(detail.getMdseName());
                        orderMdseView.setCount(detail.getCount());
                        orderMdseView.setPic(picPath + detail.getMdseSmallPic());
                        orderMdseView.setPrice(detail.getPrice());
                        orderMdseViews.add(orderMdseView);
                    }
                    view.setMdseInfos(orderMdseViews);
                }
                views.add(view);
            }
        }
        return Result.buildSuccess(views, page.getTotalElements());
    }

    /**
     * 获取订单详情
     * @param orderId
     * @return
     */
    @RequestMapping(value="getOrderDetail/{orderId}", method={RequestMethod.POST,RequestMethod.GET})
    public Result<OrderView> getOrderDetail(@PathVariable Long orderId){
        OrderView order = orderService.findOrderViewById(orderId);
        if(order != null){
            return Result.buildSuccess(order);
        }else{
            return Result.buildFail("商品不存在");
        }
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @RequestMapping(value="cancelOrder/{orderId}", method={RequestMethod.POST,RequestMethod.GET})
    public Result<String> cancelOrder(@PathVariable Long orderId){
        String errMsg = orderService.cancelOrder(orderId);
        if(errMsg != null){
            return Result.buildFail(errMsg);
        }else{
            return Result.buildSuccess(null);
        }
    }

    /**
     * 发起支付
     * @param orderId
     * @param payType
     * @return
     */
    @RequestMapping(value="pay/{orderId}/{payType}", method={RequestMethod.POST,RequestMethod.GET})
    public Result<PayResultView> pay(@PathVariable Long orderId, @PathVariable Long payType){
        OrderEntity order = orderService.findOrderById(orderId);
        if(order == null){
            return Result.buildFail("订单不存在");
        }
        if(order.getStatus() != 1){
            return Result.buildFail("该订单状态不允许支付");
        }
        order = wxPayService.pay(order, payType);
        if(order == null){
            return Result.buildFail("发起支付异常");
        }
        orderService.updateOrder(order);
        PayResultView payResultView = new PayResultView();
        payResultView.setPrepayId(order.getPaySeqs());
        if(payType == 2){
            payResultView.setPayPic(picPath + order.getPayPic());
        }
        return Result.buildSuccess(payResultView);
    }

    @Value("${wine.storePath}")
    private String storePath;

    @RequestMapping("/upload/{module}")
    public Result<?> singleFileUpload(@RequestParam("upload") MultipartFile file, @PathVariable String module) throws IOException {
        String yyyyMMdd = DateFormatUtils.format(new Date(),"/yyyy/MM/dd/");
        String dirStr = org.apache.commons.lang3.StringUtils.join(new Object[]{storePath,module,yyyyMMdd});
        FileUtils.forceMkdir(new File(dirStr));

        // Save the file locally
        Path path = Paths.get(dirStr + file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        Files.write(path, bytes);
        return Result.buildSuccess(path.toString());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleIOException(HttpServletRequest request,Exception ex) throws URISyntaxException {
        Result result = Result.buildFail(ex.getMessage());
        return ResponseEntity.created(new URI((request.getRequestURI()))).header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString()).body(JSON.toJSONString(result));
    }

}
