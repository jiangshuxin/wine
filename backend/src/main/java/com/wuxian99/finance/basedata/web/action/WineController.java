package com.wuxian99.finance.basedata.web.action;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.google.gson.Gson;
import com.wuxian99.finance.basedata.domain.*;
import com.wuxian99.finance.basedata.service.pay.WxPayService;
import com.wuxian99.finance.basedata.service.wine.*;
import com.wuxian99.finance.basedata.support.util.SmsUtils;
import com.wuxian99.finance.basedata.web.dto.*;
import com.wuxian99.finance.basedata.web.view.*;
import com.wuxian99.finance.common.Result;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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

    @Autowired
    MerchantService merchantService;

    private static final Logger logger = LoggerFactory.getLogger(WineController.class);

    /**
     * 获取酒庄基本信息
     * @param merchantId
     * @return
     */
    @RequestMapping(value="getMerchantInfo/{merchantId}", method={RequestMethod.GET})
    public Result<MerchantView> getMerchantInfo(@PathVariable String merchantId){
        logger.info("getMerchantInfo request: {}", merchantId);
        MerchantEntity merchant =  merchantService.findByMerchantId(merchantId);
        MerchantView view = new MerchantView();
        if(merchant == null){
            return Result.buildFail("酒庄不存在");
        }else if(merchant.getStatus() == 0){
            return Result.buildFail("酒庄已停用");
        }else{
            try {
                PropertyUtils.copyProperties(view, merchant);
            }catch (Exception e){
                e.printStackTrace();
            }

            List<String> landPics = new ArrayList<>();
            if(StringUtils.isNotBlank(merchant.getLandPic1())){
                landPics.add(picPath + merchant.getLandPic1());
            }
            if(StringUtils.isNotBlank(merchant.getLandPic2())){
                landPics.add(picPath + merchant.getLandPic2());
            }
            if(StringUtils.isNotBlank(merchant.getLandPic3())){
                landPics.add(picPath + merchant.getLandPic3());
            }
            if(StringUtils.isNotBlank(merchant.getLandPic4())){
                landPics.add(picPath + merchant.getLandPic4());
            }
            view.setLandPics(landPics.toArray(new String[0]));


            List<String> certPics = new ArrayList<>();
            if(StringUtils.isNotBlank(merchant.getCertPic1())){
                certPics.add(picPath + merchant.getCertPic1());
            }
            if(StringUtils.isNotBlank(merchant.getCertPic2())){
                certPics.add(picPath + merchant.getCertPic2());
            }
            if(StringUtils.isNotBlank(merchant.getCertPic3())){
                certPics.add(picPath + merchant.getCertPic3());
            }
            if(StringUtils.isNotBlank(merchant.getCertPic4())){
                certPics.add(picPath + merchant.getCertPic4());
            }
            view.setCertPics(certPics.toArray(new String[0]));


            List<String> prizePics = new ArrayList<>();
            if(StringUtils.isNotBlank(merchant.getPrizePic1())){
                prizePics.add(picPath + merchant.getPrizePic1());
            }
            if(StringUtils.isNotBlank(merchant.getPrizePic2())){
                prizePics.add(picPath + merchant.getPrizePic2());
            }
            if(StringUtils.isNotBlank(merchant.getPrizePic3())){
                prizePics.add(picPath + merchant.getPrizePic3());
            }
            if(StringUtils.isNotBlank(merchant.getPrizePic4())){
                prizePics.add(picPath + merchant.getPrizePic4());
            }
            view.setPrizePics(prizePics.toArray(new String[0]));

        }
        logger.info("getMerchantInfo response: {}", new Gson().toJson(view));
        return Result.buildSuccess(view);
    }

    /**
     * 获取首页Banner
     * @param merchantId
     * @return
     */
    @RequestMapping(value="getBanners/{merchantId}", method={RequestMethod.GET})
    public Result<List<BannerListView>> getBanners(@PathVariable String merchantId){
        logger.info("getBanners request: {}", merchantId);
        List<BannerEntity> banners =  bannerService.findByMerchantId(merchantId);
        List<BannerListView> bannerViews = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(banners)){
            for(BannerEntity banner : banners){
                BannerListView view = new BannerListView();
                view.setPic(picPath + banner.getPicPath());
                view.setMdseId(banner.getMdseId());
                bannerViews.add(view);
            }
        }
        logger.info("getBanners response: {}", new Gson().toJson(bannerViews));
        return Result.buildSuccess(bannerViews);
    }

    /**
     * 获取首页发现、动态列表
     * @param paras
     * @return
     */
    @RequestMapping(value="getDiscovers", method={RequestMethod.POST})
    public Result<List<DiscoverListView>> getDiscovers(@RequestBody QueryDiscoverListDto paras){
        logger.info("getDiscovers request: {}", paras);
        Page<DiscoverEntity> page =  discoverService.findDiscovers(paras);
        List<DiscoverListView> discoverListViews = new ArrayList<DiscoverListView>();
        if(CollectionUtils.isNotEmpty(page.getContent())){
            for(DiscoverEntity discover : page.getContent()){
                DiscoverListView discoverListView = new DiscoverListView();
                discoverListView.setDiscoverId(discover.getId());
                discoverListView.setPic(picPath + discover.getPicPath());
                discoverListView.setTitle(discover.getTitle());
                discoverListView.setTag(discover.getTag());
                discoverListView.setDescription(discover.getDescription());
                discoverListViews.add(discoverListView);
            }
        }
        logger.info("getDiscovers response: {}", new Gson().toJson(discoverListViews));
        return Result.buildSuccess(discoverListViews, page.getTotalElements());
    }

    /**
     * 获取发现、动态详情
     * @param discoverId
     * @return
     */
    @RequestMapping(value="getDiscoverDetails/{discoverId}", method={RequestMethod.GET})
    public Result<List<DiscoverDetailEntity>> getDiscoverDetails(@PathVariable Long discoverId){
        logger.info("getDiscoverDetails request: {}", discoverId);
        List<DiscoverDetailEntity> discoverDetails =  discoverService.findDetailByDiscoverId(discoverId);
        if(CollectionUtils.isNotEmpty(discoverDetails)){
            for(DiscoverDetailEntity discoverDetail : discoverDetails){
                discoverDetail.setPic(picPath + discoverDetail.getPic());
            }
        }
        logger.info("getDiscoverDetails response: {}", new Gson().toJson(discoverDetails));
        return Result.buildSuccess(discoverDetails);
    }

    /**
     * 获取商品列表
     * @param paras
     * @return
     */
    @RequestMapping(value="getMdses", method={RequestMethod.POST})
    public Result<List<MdseListView>> getMdses(@RequestBody QueryMdseListDto paras){
        logger.info("getMdses request: {}", paras);
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
                mdseListView.setStatus(mdse.getStatus());
                mdseListViews.add(mdseListView);
            }
        }
        logger.info("getMdses response: {}", new Gson().toJson(mdseListViews));
        return Result.buildSuccess(mdseListViews, page.getTotalElements());
    }

    /**
     * 获取商品详情
     * @param mdseId
     * @return
     */
    @RequestMapping(value="getMdseDetail/{mdseId}", method={RequestMethod.POST,RequestMethod.GET})
    public Result<MdseView> getMdseDetail(@PathVariable Long mdseId){
        logger.info("getMdseDetail request: {}", mdseId);
        MdseEntity mdse =  mdseService.findMdseById(mdseId);
        if(mdse == null) {
            logger.info("getMdseDetail response: {}", "商品不存在");
            return Result.buildFail("商品不存在");
        }else if(mdse.getStatus() == 0){
            logger.info("getMdseDetail response: {}", "商品已下架");
            return Result.buildFail("商品已下架");
        }else{
            MdseView view = new MdseView();
            view.setMdseId(mdse.getId());
            view.setName(mdse.getName());
            view.setNameEn(mdse.getNameEn());
            view.setPrice(mdse.getPrice());
            view.setStatus(mdse.getStatus());
            view.setWineType(mdse.getWineType());
            view.setGrapeType(mdse.getGrapeType());
            view.setYear(mdse.getYear());
            view.setDegree(mdse.getDegree());
            view.setMl(mdse.getMl());
            view.setTreeAge(mdse.getTreeAge());
            view.setWineMaker(mdse.getWineMaker());
            view.setMerchantName(mdse.getMerchantName());
            view.setProductArea(mdse.getProductArea());
            view.setSmallPic(picPath + mdse.getSmallPic());

            List<String> bigPics = new ArrayList<>();
            if(StringUtils.isNotBlank(mdse.getBigPic1())){
                bigPics.add(picPath + mdse.getBigPic1());
            }
            if(StringUtils.isNotBlank(mdse.getBigPic2())){
                bigPics.add(picPath + mdse.getBigPic2());
            }
            if(StringUtils.isNotBlank(mdse.getBigPic3())){
                bigPics.add(picPath + mdse.getBigPic3());
            }
            if(StringUtils.isNotBlank(mdse.getBigPic4())){
                bigPics.add(picPath + mdse.getBigPic4());
            }
            view.setBigPics(bigPics.toArray(new String[0]));

            view.setTechnology(mdse.getTechnology());
            view.setBarrel(mdse.getBarrel());
            view.setFillingTime(mdse.getFillingTime());
            view.setExpiryDate(mdse.getExpiryDate());
            view.setDrinkTemperature(mdse.getDrinkTemperature());
            view.setSoberTime(mdse.getSoberTime());
            view.setCollocation(mdse.getCollocation());
            view.setYield(mdse.getYield());
            view.setInventory(mdse.getInventory());

            List<String> prizePics = new ArrayList<>();
            if(StringUtils.isNotBlank(mdse.getPrizePic1())){
                prizePics.add(picPath + mdse.getPrizePic1());
            }
            if(StringUtils.isNotBlank(mdse.getPrizePic2())){
                prizePics.add(picPath + mdse.getPrizePic2());
            }
            if(StringUtils.isNotBlank(mdse.getPrizePic3())){
                prizePics.add(picPath + mdse.getPrizePic3());
            }
            if(StringUtils.isNotBlank(mdse.getPrizePic4())){
                prizePics.add(picPath + mdse.getPrizePic4());
            }
            view.setPrizePics(prizePics.toArray(new String[0]));


            List<String> inspectionReportPics = new ArrayList<>();
            if(StringUtils.isNotBlank(mdse.getInspectionReportPic1())){
                inspectionReportPics.add(picPath + mdse.getInspectionReportPic1());
            }
            if(StringUtils.isNotBlank(mdse.getInspectionReportPic2())){
                inspectionReportPics.add(picPath + mdse.getInspectionReportPic2());
            }
            if(StringUtils.isNotBlank(mdse.getInspectionReportPic3())){
                inspectionReportPics.add(picPath + mdse.getInspectionReportPic3());
            }
            if(StringUtils.isNotBlank(mdse.getInspectionReportPic4())){
                inspectionReportPics.add(picPath + mdse.getInspectionReportPic4());
            }
            view.setInspectionReportPics(inspectionReportPics.toArray(new String[0]));


            logger.info("getMdseDetail response: {}", view);
            return Result.buildSuccess(view);
        }
    }

    /**
     * 验证用户名
     * @param userName
     * @return
     */
    @RequestMapping(value="verifyUserName/{userName}", method={RequestMethod.POST,RequestMethod.GET})
    public Result<Map<String, Boolean>> verifyUserName(@PathVariable String userName){
        logger.info("verifyUserName request: {}", userName);
        if(StringUtils.isBlank(userName) || !StringUtils.isNumeric(userName) || userName.length() != 11){
            return Result.buildFail("手机号不正确");
        }

        UserEntity user = userService.findByUserName(userName);
        Boolean isExist = false;
        if(user != null){
            isExist = true;
        }
        Map<String, Boolean> data = new HashMap<>();
        data.put("isExist", isExist);
        logger.info("verifyUserName response: {}", new Gson().toJson(data));
        return Result.buildSuccess(data);
    }

    /**
     * 发送验证码
     * @param userName
     * @param type 1:登录，2:修改密码
     * @return
     */
    @RequestMapping(value="sendVerifyCode/{userName}/{type}", method={RequestMethod.POST,RequestMethod.GET})
    public Result<Map<String, String>> sendVerifyCode(@PathVariable String userName, @PathVariable Long type){
        logger.info("sendVerifyCode request: {}, {}", userName, type);
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

        Map<String,String> smsParas = new HashMap<>();
        smsParas.put("code", verifyCode);
        SendSmsResponse response = SmsUtils.sendSms(SmsUtils.SmsSendRequest.build(userName, type==1? SmsUtils.SmsSendType.Login : SmsUtils.SmsSendType.ModifyPwd, smsParas));
        logger.info("发送验证码短信返回: " + new Gson().toJson(response));

        Map<String, String> data = new HashMap<>();
        data.put("verifyCode", verifyCode);
        logger.info("sendVerifyCode response: {}", new Gson().toJson(data));
        return Result.buildSuccess(data);
    }

    /**
     * 用户登录
     * type 1:密码登录，2:验证码登录
     * @param paras
     * @return
     */
    @RequestMapping(value="login", method={RequestMethod.POST})
    public Result<UserInfoDto> login(@RequestBody LoginDto paras){
        logger.info("login request: {}", paras);
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

                //绑定推荐人
                if(StringUtils.isNotBlank(paras.getReferralCode())) {
                    UserEntity referralUser = userService.findByReferralCode(paras.getReferralCode());
                    if(referralUser != null)
                    user.setParentId(referralUser.getId());
                }
                user = userService.addUser(user);
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
        userInfoDto.setReferralCode(user.getReferralCode());
        logger.info("login response: {}", userInfoDto);
        return Result.buildSuccess(userInfoDto);
    }

    /**
     * 修改登录密码
     * @param paras
     * @return
     */
    @RequestMapping(value="modifyPwd", method={RequestMethod.POST})
    public Result<Map<String, Long>> modifyPwd(@RequestBody ModifyPwdDto paras){
        logger.info("modifyPwd request: {}", paras);
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
        userService.updateUser(user);

        Map<String, Long> data = new HashMap<>();
        data.put("userId", user.getId());
        logger.info("modifyPwd response: {}", new Gson().toJson(data));
        return Result.buildSuccess(data);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value="getUserInfo/{userId}", method={RequestMethod.POST,RequestMethod.GET})
    public Result<UserInfoDto> getUserInfo(@PathVariable Long userId) {
        logger.info("getUserInfo request: {}", userId);
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
        userInfoDto.setReferralCode(user.getReferralCode());
        logger.info("getUserInfo response: {}", userInfoDto);
        return Result.buildSuccess(userInfoDto);
    }

    /**
     * 修改用户信息
     * @param paras
     * @return
     */
    @RequestMapping(value="modifyUserInfo", method={RequestMethod.POST})
    public Result<UserInfoDto> modifyUserInfo(@RequestBody UserInfoDto paras){
        logger.info("modifyUserInfo request: {}", paras);
        UserEntity user = userService.findByUserId(paras.getUserId());
        if(user == null){
            return Result.buildFail("用户ID不正确");
        }
        user.setRealName(paras.getRealName());
        user.setGender(paras.getGender());
        user.setBirthday(paras.getBirthday());
        user = userService.updateUser(user);

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserId(user.getId());
        userInfoDto.setUserName(user.getUserName());
        userInfoDto.setType(user.getType());
        userInfoDto.setBalance(user.getBalance());
        userInfoDto.setRealName(user.getRealName());
        userInfoDto.setGender(user.getGender());
        userInfoDto.setBirthday(user.getBirthday());
        userInfoDto.setReferralCode(user.getReferralCode());
        logger.info("modifyUserInfo response: {}", userInfoDto);
        return Result.buildSuccess(userInfoDto);
    }

    /**
     * 获取收货地址列表
     * @param paras
     * @return
     */
    @RequestMapping(value="getUserAddresses", method={RequestMethod.POST,RequestMethod.GET})
    public Result<List<UserAddressDto>> getUserAddresses(@RequestBody QueryUserAddressListDto paras){
        logger.info("getUserAddresses request: {}", paras);
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        PageRequest pageRequest = paras.convert(sort);
        Page<UserAddressEntity> page = userService.findUserAddressesByUserId(paras.getUserId(), pageRequest);
        List<UserAddressDto> views = new ArrayList<UserAddressDto>();
        if(CollectionUtils.isNotEmpty(page.getContent())){
            for (UserAddressEntity address:page.getContent()){
                UserAddressDto view = new UserAddressDto();
                view.setAddressId(address.getId());
                view.setIsDefault(address.getIsDefault());
                view.setReceiver(address.getReceiver());
                view.setPhone(address.getPhone());
                view.setProvince(address.getProvince());
                view.setAddress(address.getAddress());
                views.add(view);
            }
        }
        logger.info("getUserAddresses response: {}", new Gson().toJson(views));
        return Result.buildSuccess(views, page.getTotalElements());
    }

    /**
     * 新增或修改用户收货地址信息
     * @param paras
     * @return
     */
    @RequestMapping(value="modifyUserAddress", method={RequestMethod.POST})
    public Result<Long> modifyUserAddress(@RequestBody UserAddressDto paras){
        logger.info("modifyUserAddress request: {}", paras);
        List<UserAddressEntity> allAddr = userService.findUserAddressesByUserId(paras.getUserId());
        UserAddressEntity address = new UserAddressEntity();
        //如果为用户的第一个地址，自动设置为默认地址
        if(CollectionUtils.isEmpty(allAddr)){
            address.setIsDefault(1L);
        }else{
            address.setIsDefault(paras.getIsDefault());
        }
        address.setUserId(paras.getUserId());
        address.setPhone(paras.getPhone());
        address.setReceiver(paras.getReceiver());
        address.setProvince(paras.getProvince());
        address.setAddress(paras.getAddress());
        if(paras.getAddressId() != null && paras.getAddressId().longValue() != 0L){
            address.setId(paras.getAddressId());
        }
        address = userService.saveOrUpdateUserAddress(address);

        //如果本次新增/修改的地址为默认地址，把本来是默认地址的改为非默认
        if(address.getIsDefault().longValue() == 1L){
            if(CollectionUtils.isNotEmpty(allAddr)) {
                for (UserAddressEntity addr : allAddr) {
                    if (addr.getId().longValue() != address.getId().longValue() && addr.getIsDefault().longValue() == 1L) {
                        addr.setIsDefault(0L);
                        userService.saveOrUpdateUserAddress(addr);
                    }
                }
            }
        }
        logger.info("modifyUserAddress response: {}", address.getId());
        return Result.buildSuccess(address.getId());
    }


    /**
     * 删除用户收货地址
     * @param addressId
     * @return
     */
    @RequestMapping(value="deleteUserAddress/{addressId}", method={RequestMethod.POST,RequestMethod.GET})
    public Result<UserInfoDto> deleteUserAddress(@PathVariable Long addressId) {
        logger.info("deleteUserAddress request: {}", addressId);
        userService.deleteUserAddressById(addressId);
        return Result.buildSuccess(null);
    }

    /**
     * 下单
     * @param paras
     * @return
     */
    @RequestMapping(value="createOrder", method={RequestMethod.POST})
    public Result<OrderView> createOrder(@RequestBody CreateOrderDto paras){
        logger.info("createOrder request: {}", paras);
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
        order.setReceiver(userAddress.getReceiver());

        order = orderService.createOrder(order, details);

        OrderView view = new OrderView();
        view.setOrderId(order.getId());
        view.setAmount(order.getAmount());
        logger.info("createOrder response: {}", view);
        return Result.buildSuccess(view);
    }

    /**
     * 获取订单列表
     * @param paras
     * @return
     */
    @RequestMapping(value="getOrders", method={RequestMethod.POST,RequestMethod.GET})
    public Result<List<OrderListView>> getOrders(@RequestBody QueryOrderListDto paras){
        logger.info("getOrders request: {}", paras);
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
        logger.info("getOrders response: {}", new Gson().toJson(views));
        return Result.buildSuccess(views, page.getTotalElements());
    }

    /**
     * 获取订单详情
     * @param orderId
     * @return
     */
    @RequestMapping(value="getOrderDetail/{orderId}", method={RequestMethod.POST,RequestMethod.GET})
    public Result<OrderView> getOrderDetail(@PathVariable Long orderId){
        logger.info("getOrderDetail request: {}", orderId);
        OrderView order = orderService.findOrderViewById(orderId);
        if(order != null){
            logger.info("getOrderDetail response: {}", order);
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
    public Result<Map<String, Long>> cancelOrder(@PathVariable Long orderId){
        logger.info("cancelOrder request: {}", orderId);
        String errMsg = orderService.cancelOrder(orderId);
        if(errMsg != null){
            return Result.buildFail(errMsg);
        }else{
            Map<String, Long> data = new HashMap<>();
            data.put("orderId", orderId);
            logger.info("cancelOrder response: {}", new Gson().toJson(data));
            return Result.buildSuccess(data);
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
        logger.info("pay request: {}, {}", orderId, payType);
        OrderEntity order = orderService.findOrderById(orderId);
        if(order == null){
            return Result.buildFail("订单不存在");
        }
        if(order.getStatus() != 1){
            return Result.buildFail("该订单状态不允许支付");
        }
        PayResultView result = null;
        if(payType == 2 && order.getPayPic() != null){
            result = new PayResultView();
            result.setPayPic(picPath + order.getPayPic());
        }else {
            result = wxPayService.pay(order, payType);
            if (result == null) {
                return Result.buildFail("发起支付异常");
            }
        }
        logger.info("pay response: {}", result);
        return Result.buildSuccess(result);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleIOException(HttpServletRequest request,Exception ex) throws URISyntaxException {
        Result result = Result.buildFail(ex.getMessage());
        return ResponseEntity.created(new URI((request.getRequestURI()))).header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString()).body(JSON.toJSONString(result));
    }

}
