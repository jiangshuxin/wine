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
    public Result<List<DiscoverListView>> getDiscovers(@RequestParam String merchantId, @RequestParam Long type, @RequestParam Integer pageNumber){
        List<DiscoverEntity> discovers =  discoverService.findByMerchantIdAndType(merchantId, type, pageNumber, pageSize);
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
    public Result<List<DiscoverDetailEntity>> getDiscoverDetails(@RequestParam Long discoverId){
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
     * @param merchantId
     * @param catagory
     * @param year
     * @param price
     * @param pageNumber
     * @return
     */
    @RequestMapping(value="getMdses", method={RequestMethod.POST,RequestMethod.GET})
    public Result<List<MdseListView>> getMdses(@RequestParam String merchantId, @RequestParam String catagory,
                                               @RequestParam String year, @RequestParam String price, @RequestParam Integer pageNumber){
        List<MdseEntity> mdses =  mdseService.findMdses(merchantId, catagory, year, price, pageNumber, pageSize);
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
    public Result<MdseEntity> getMdseDetail(@RequestParam Long mdseId){
        MdseEntity mdse =  mdseService.findMdseById(mdseId);
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
     * @param type 1:登录，2:修改密码
     * @return
     */
    @RequestMapping(value="sendVerifyCode", method={RequestMethod.POST,RequestMethod.GET})
    public Result<String> sendVerifyCode(@RequestParam String userName, @RequestParam Long type){
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
     * @param userName
     * @param password
     * @param type 1:密码登录，2:验证码登录
     * @param parentId
     * @return
     */
    @RequestMapping(value="login", method={RequestMethod.POST,RequestMethod.GET})
    public Result<UserView> sendVerifyCode(@RequestParam String userName, @RequestParam String password, @RequestParam Long type, @RequestParam Long parentId){
        if(StringUtils.isBlank(userName) || !StringUtils.isNumeric(userName) || userName.length() != 11){
            return Result.buildFail("用户名不正确");
        }
        if(type != 1 && type != 2){
            return Result.buildFail("登录类型不正确");
        }
        UserEntity user = userService.findByUserName(userName);

        //验证码登录
        if("2".equals(type)){
            if(!StringUtils.equals(verifyCodeCache.get(userName + "_1"), password)){
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
                if(parentId != null && parentId.longValue() != 0) {
                    user.setParentId(parentId);
                }
                user = userService.saveOrUpdateUser(user);
            }
        }else{
            if(user == null){
                return Result.buildFail("用户名不存在");
            }else if(!StringUtils.equals(user.getPassword(), password)){
                return Result.buildFail("密码不正确");
            }
        }
        UserView userView = new UserView();
        userView.setUserId(user.getId());
        userView.setUserName(user.getUserName());
        userView.setType(user.getType());
        userView.setBalance(user.getBalance());
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
    public Result<String> sendVerifyCode(@RequestParam Long userId, @RequestParam String password, @RequestParam String verifyCode){
        if(StringUtils.isBlank(password)){
            return Result.buildFail("新密码不能为空");
        }
        UserEntity user = userService.findByUserId(userId);
        if(user == null){
            return Result.buildFail("用户ID不正确");
        }
        if(!StringUtils.equals(verifyCodeCache.get(user.getUserName() + "_2"), verifyCode)){
            return Result.buildFail("短信验证码不正确");
        }
        user.setPassword(password);
        userService.saveOrUpdateUser(user);
        return Result.buildSuccess("");
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value="getUserInfo", method={RequestMethod.POST,RequestMethod.GET})
    public Result<UserView> getUserInfo(@RequestParam Long userId) {
        UserEntity user = userService.findByUserId(userId);
        if (user == null) {
            return Result.buildFail("用户ID不正确");
        }
        UserView userView = new UserView();
        userView.setUserId(user.getId());
        userView.setUserName(user.getUserName());
        userView.setType(user.getType());
        userView.setBalance(user.getBalance());
        userView.setRealName(user.getRealName());
        userView.setGender(user.getGender());
        userView.setBirthday(user.getBirthday());
        return Result.buildSuccess(userView);
    }

    /**
     * 修改用户信息
     * @param paras
     * @return
     */
    @RequestMapping(value="modifyUserInfo", method={RequestMethod.POST,RequestMethod.GET})
    public Result<UserView> modifyUserInfo(UserView paras){
        UserEntity user = userService.findByUserId(paras.getUserId());
        if(user == null){
            return Result.buildFail("用户ID不正确");
        }
        user.setRealName(paras.getRealName());
        user.setGender(paras.getGender());
        user.setBirthday(paras.getBirthday());
        userService.saveOrUpdateUser(user);

        UserView userView = new UserView();
        userView.setUserId(user.getId());
        userView.setUserName(user.getUserName());
        userView.setType(user.getType());
        userView.setBalance(user.getBalance());
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
    public Result<List<UserAddressView>> getUserAddresses(@RequestParam Long userId){
        List<UserAddressEntity> addresses = userService.findUserAddressesByUserId(userId);
        List<UserAddressView> views = new ArrayList<UserAddressView>();
        if(CollectionUtils.isNotEmpty(addresses)){
            for (UserAddressEntity address:addresses){
                UserAddressView view = new UserAddressView();
                view.setAddressId(address.getId());
                view.setIsDefualt(address.getIsDefualt());
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
     * 新增或修改用户收货地址信息
     * @param paras
     * @return
     */
    @RequestMapping(value="modifyUserAddress", method={RequestMethod.POST,RequestMethod.GET})
    public Result<Long> modifyUserInfo(UserAddressView paras){
        System.out.println(paras);
        List<UserAddressEntity> addresses = userService.findUserAddressesByUserId(paras.getUserId());
        UserAddressEntity address = new UserAddressEntity();
        //如果为用户的第一个地址，自动设置为默认地址
        if(CollectionUtils.isEmpty(addresses)){
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
            if(CollectionUtils.isNotEmpty(addresses)) {
                for (UserAddressEntity addr : addresses) {
                    if (addr.getId().longValue() != address.getId().longValue() && addr.getIsDefualt().longValue() == 1L) {
                        addr.setIsDefualt(0L);
                        userService.saveOrUpdateUserAddress(addr);
                    }
                }
            }
        }
        return Result.buildSuccess(address.getId());
    }

}
