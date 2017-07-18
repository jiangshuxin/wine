package com.wuxian99.finance.basedata.web.action;

import com.wuxian99.finance.basedata.domain.*;
import com.wuxian99.finance.basedata.service.wine.BannerService;
import com.wuxian99.finance.basedata.service.wine.DiscoverService;
import com.wuxian99.finance.basedata.service.wine.MdseService;
import com.wuxian99.finance.basedata.service.wine.UserService;
import com.wuxian99.finance.basedata.support.util.StringUtils;
import com.wuxian99.finance.basedata.web.view.DiscoverListView;
import com.wuxian99.finance.basedata.web.view.MdseListView;
import com.wuxian99.finance.basedata.web.view.UserAddressView;
import com.wuxian99.finance.basedata.web.view.UserView;
import com.wuxian99.finance.common.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    private int pageSize = 5;

    private Map<String, String> verifyCodeCache = new HashMap<String, String>();

    @Autowired
    BannerService bannerService;

    @Autowired
    DiscoverService discoverService;

    @Autowired
    MdseService mdseService;

    @Autowired
    UserService userService;

    /**
     * 获取首页Banner
     * @param merchantId
     * @return
     */
    @RequestMapping(value="getBanners", method={RequestMethod.POST,RequestMethod.GET})
    public Result<List<BannerEntity>> getBanners(@RequestParam String merchantId){
        List<BannerEntity> banners =  bannerService.findByMerchantId(merchantId);
        if(CollectionUtils.isNotEmpty(banners)){
            for(BannerEntity banner : banners){
                banner.setPic(picPath + banner.getPic());
            }
        }
        return Result.buildSuccess(banners);
    }

    /**
     * 获取首页发现、动态
     * @param merchantId
     * @param type
     * @param pageNumber
     * @return
     */
    @RequestMapping(value="getDiscovers", method={RequestMethod.POST,RequestMethod.GET})
    public Result<List<DiscoverListView>> getDiscovers(@RequestParam String merchantId, @RequestParam String type, @RequestParam String pageNumber){
        List<DiscoverEntity> discovers =  discoverService.findByMerchantIdAndType(merchantId, buildDiscoverType(type), buildPageNumber(pageNumber), pageSize);
        List<DiscoverListView> discoverListViews = new ArrayList<DiscoverListView>();
        if(CollectionUtils.isNotEmpty(discovers)){
            for(DiscoverEntity discover : discovers){
                DiscoverListView discoverListView = new DiscoverListView();
                discoverListView.setDiscoverId(discover.getId());
                discoverListView.setPic(picPath + discover.getPic());
                discoverListView.setTitle(discover.getTitle());
                discoverListView.setTag(discover.getTag());
                discoverListView.setDescription(discover.getDescription());
                discoverListViews.add(discoverListView);
            }
        }
        return Result.buildSuccess(discoverListViews);
    }

    /**
     * 获取发现、动态详情
     * @param discoverId
     * @return
     */
    @RequestMapping(value="getDiscoverDetails", method={RequestMethod.POST,RequestMethod.GET})
    public Result<List<DiscoverDetailEntity>> getDiscoverDetails(@RequestParam String discoverId){
        Long id = null;
        try{
            id = Long.parseLong(discoverId);
        }catch (Exception e){
            return Result.buildFail("discoverId不正确");
        }
        List<DiscoverDetailEntity> discoverDetails =  discoverService.findDetailByDiscoverId(id);
        if(CollectionUtils.isNotEmpty(discoverDetails)){
            for(DiscoverDetailEntity discoverDetail : discoverDetails){
                discoverDetail.setPic(picPath + discoverDetail.getPic());
            }
        }
        return Result.buildSuccess(discoverDetails);
    }

    /**
     * 获取商品列表
     * @param merchantId
     * @param catagory
     * @param year
     * @param price
     * @param pageNumber
     * @return
     */
    @RequestMapping(value="getMdses", method={RequestMethod.POST,RequestMethod.GET})
    public Result<List<MdseListView>> getMdses(@RequestParam String merchantId, @RequestParam String catagory,
                                               @RequestParam String year, @RequestParam String price, @RequestParam String pageNumber){
        List<MdseEntity> mdses =  mdseService.findMdses(merchantId, catagory, year, price, buildPageNumber(pageNumber), pageSize);
        List<MdseListView> mdseListViews = new ArrayList<MdseListView>();
        if(CollectionUtils.isNotEmpty(mdses)){
            for(MdseEntity mdse : mdses){
                MdseListView mdseListView = new MdseListView();
                mdseListView.setMdseId(mdse.getId());
                mdseListView.setName(mdse.getName());
                mdseListView.setNameEn(mdse.getNameEn());
                mdseListView.setSmallPic(picPath + mdse.getSmallPic());
                mdseListView.setPrice(mdse.getPrice());
                mdseListViews.add(mdseListView);
            }
        }
        return Result.buildSuccess(mdseListViews);
    }

    /**
     * 获取商品详情
     * @param mdseId
     * @return
     */
    @RequestMapping(value="getMdseDetail", method={RequestMethod.POST,RequestMethod.GET})
    public Result<MdseEntity> getMdseDetail(@RequestParam String mdseId){
        Long id = null;
        try{
            id = Long.parseLong(mdseId);
        }catch (Exception e){
            return Result.buildFail("商品编号不正确");
        }
        MdseEntity mdse =  mdseService.findMdseById(id);
        if(mdse != null){
            mdse.setSmallPic(picPath + mdse.getSmallPic());
            mdse.setBigPic(picPath + mdse.getBigPic());
            return Result.buildSuccess(mdse);
        }else{
            return Result.buildFail("商品不存在");
        }
    }

    /**
     * 发送验证码
     * @param userName
     * @param type
     * @return
     */
    @RequestMapping(value="sendVerifyCode", method={RequestMethod.POST,RequestMethod.GET})
    public Result<String> sendVerifyCode(@RequestParam String userName, @RequestParam String type){
        if(StringUtils.isBlank(userName) || !StringUtils.isNumeric(userName) || userName.length() != 11){
            return Result.buildFail("手机号不正确");
        }
        if(!"1".equals(type) && !"2".equals(type)){
            return Result.buildFail("验证码类型不正确");
        }
        String verifyCode = buildVerifyCode(4);
        verifyCodeCache.put(userName + "_" + type, verifyCode);
        //TODO 发送短信验证码
        return Result.buildSuccess(verifyCode);
    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @param type
     * @param parentId
     * @return
     */
    @RequestMapping(value="login", method={RequestMethod.POST,RequestMethod.GET})
    public Result<UserView> sendVerifyCode(@RequestParam String userName, @RequestParam String password, @RequestParam String type, @RequestParam String parentId){
        if(StringUtils.isBlank(userName) || !StringUtils.isNumeric(userName) || userName.length() != 11){
            return Result.buildFail("用户名不正确");
        }
        if(!"1".equals(type) && !"2".equals(type)){
            return Result.buildFail("登录方式不正确");
        }
        UserEntity user = userService.findByUserName(userName);

        //验证码登录
        if("2".equals(type)){
            if(!StringUtils.equals(verifyCodeCache.get(userName + "_1"), password)){
                return Result.buildFail("验证码不正确");
            }
            if(user == null){
                user = new UserEntity();
                user.setUserName(userName);
                user.setStatus(1L);
                user.setType(2L);
                user.setBalance(0L);
                user.setGender("男");
                user.setBirthday("1990-01-01");
                if(StringUtils.isNotBlank(parentId) && StringUtils.isNumeric(parentId)) {
                    user.setParentId(Long.parseLong(parentId));
                }
                user = userService.addUser(user);
            }
        }else{
            if(user == null){
                return Result.buildFail("用户名不存在");
            }else if(!StringUtils.equals(user.getPassword(), password)){
                return Result.buildFail("密码不正确");
            }
        }
        UserView userView = new UserView();
        userView.setUserId(user.getId()+"");
        userView.setUserName(user.getUserName());
        userView.setType(user.getType()+"");
        userView.setBalance(user.getBalance()+"");
        userView.setRealName(user.getRealName());
        userView.setGender(user.getGender());
        userView.setBirthday(user.getBirthday());
        return Result.buildSuccess(userView);
    }

    /**
     * 修改登录密码
     * @param userId
     * @param password
     * @param verifyCode
     * @return
     */
    @RequestMapping(value="modifyPassword", method={RequestMethod.POST,RequestMethod.GET})
    public Result<String> sendVerifyCode(@RequestParam String userId, @RequestParam String password, @RequestParam String verifyCode){
        if(StringUtils.isBlank(userId) || !StringUtils.isNumeric(userId)){
            return Result.buildFail("用户ID不正确");
        }
        if(StringUtils.isBlank(password)){
            return Result.buildFail("新密码不能为空");
        }
        UserEntity user = userService.findByUserId(Long.parseLong(userId));
        if(user == null){
            return Result.buildFail("用户ID不正确");
        }
        if(!StringUtils.equals(verifyCodeCache.get(user.getUserName() + "_2"), verifyCode)){
            return Result.buildFail("验证码不正确");
        }
        user.setPassword(password);
        userService.updateUser(user);
        return Result.buildSuccess(null);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value="getUserInfo", method={RequestMethod.POST,RequestMethod.GET})
    public Result<UserView> getUserInfo(@RequestParam String userId) {
        if (StringUtils.isBlank(userId) || !StringUtils.isNumeric(userId)) {
            return Result.buildFail("用户ID不正确");
        }
        UserEntity user = userService.findByUserId(Long.parseLong(userId));
        if (user == null) {
            return Result.buildFail("用户ID不正确");
        }
        UserView userView = new UserView();
        userView.setUserId(user.getId() + "");
        userView.setUserName(user.getUserName());
        userView.setType(user.getType() + "");
        userView.setBalance(user.getBalance() + "");
        userView.setRealName(user.getRealName());
        userView.setGender(user.getGender());
        userView.setBirthday(user.getBirthday());
        return Result.buildSuccess(userView);
    }

    /**
     * 修改用户信息
     * @param userId
     * @param realName
     * @param gender
     * @param birthday
     * @return
     */
    @RequestMapping(value="modifyUserInfo", method={RequestMethod.POST,RequestMethod.GET})
    public Result<UserView> modifyUserInfo(@RequestParam String userId, @RequestParam String realName, @RequestParam String gender, @RequestParam String birthday){
        if(StringUtils.isBlank(userId) || !StringUtils.isNumeric(userId)){
            return Result.buildFail("用户ID不正确");
        }
        UserEntity user = userService.findByUserId(Long.parseLong(userId));
        if(user == null){
            return Result.buildFail("用户ID不正确");
        }

        user.setRealName(realName);
        user.setGender(gender);
        user.setBirthday(birthday);
        userService.updateUser(user);

        UserView userView = new UserView();
        userView.setUserId(user.getId()+"");
        userView.setUserName(user.getUserName());
        userView.setType(user.getType()+"");
        userView.setBalance(user.getBalance()+"");
        userView.setRealName(user.getRealName());
        userView.setGender(user.getGender());
        userView.setBirthday(user.getBirthday());
        return Result.buildSuccess(userView);
    }

    /**
     * 获取收货地址列表
     * @param userId
     * @return
     */
    @RequestMapping(value="getUserAddresses", method={RequestMethod.POST,RequestMethod.GET})
    public Result<List<UserAddressView>> getUserAddresses(@RequestParam String userId){
        Long id = null;
        try{
            id = Long.parseLong(userId);
        }catch (Exception e){
            return Result.buildFail("商品编号不正确");
        }
        List<UserAddressEntity> addresses = userService.findUserAddresses(id);
        List<UserAddressView> views = new ArrayList<UserAddressView>();
        if(CollectionUtils.isNotEmpty(addresses)){
            for (UserAddressEntity address:addresses){
                UserAddressView view = new UserAddressView();
                view.setAddressId(address.getId()+"");
                view.setIsDefualt(address.getIsDefualt()+"");
                view.setReciver(address.getReciver());
                view.setPhone(address.getPhone());
                view.setProvince(address.getProvince());
                view.setAddress(address.getAddress());
                views.add(view);
            }
        }
        return Result.buildSuccess(views);
    }

    /**
     * 生成验证码
     * @param length
     * @return
     */
    private String buildVerifyCode(int length){
        StringBuffer code = new StringBuffer();
        for(int i=0; i<length; i++){
            code.append(new Random().nextInt(10));
        }
        return code.toString();
    }

    private Long buildDiscoverType(String type){
        try{
            return Long.parseLong(type);
        }catch (Exception e){
            return 1L;
        }
    }

    private int buildPageNumber(String pageNumber){
        try{
            return Integer.parseInt(pageNumber);
        }catch (Exception e){
            return 1;
        }
    }

}
