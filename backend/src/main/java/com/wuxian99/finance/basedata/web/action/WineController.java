package com.wuxian99.finance.basedata.web.action;

import com.wuxian99.finance.basedata.domain.BannerEntity;
import com.wuxian99.finance.basedata.domain.DiscoverDetailEntity;
import com.wuxian99.finance.basedata.domain.DiscoverEntity;
import com.wuxian99.finance.basedata.domain.MdseEntity;
import com.wuxian99.finance.basedata.service.wine.BannerService;
import com.wuxian99.finance.basedata.service.wine.DiscoverService;
import com.wuxian99.finance.basedata.service.wine.MdseService;
import com.wuxian99.finance.basedata.web.view.DiscoverListView;
import com.wuxian99.finance.basedata.web.view.MdseListView;
import com.wuxian99.finance.common.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    BannerService bannerService;

    @Autowired
    DiscoverService discoverService;

    @Autowired
    MdseService mdseService;

    /**
     * 获取首页Banner
     * @param merchantId
     * @return
     */
    @RequestMapping("/getBanners/{merchantId}")
    public Result<List<BannerEntity>> getBanners(@PathVariable String merchantId){
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
    @RequestMapping("/getDiscovers/{merchantId}/{type}/{pageNumber}")
    public Result<List<DiscoverListView>> getDiscovers(@PathVariable String merchantId, @PathVariable String type, @PathVariable String pageNumber){
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
    @RequestMapping("/getDiscoverDetails/{discoverId}/")
    public Result<List<DiscoverDetailEntity>> getDiscoverDetails(@PathVariable String discoverId){
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
    @RequestMapping("/getMdses/{merchantId}/{catagory}/{year}/{price}/{pageNumber}")
    public Result<List<MdseListView>> getMdses(@PathVariable String merchantId, @PathVariable String catagory,
                                               @PathVariable String year, @PathVariable String price, @PathVariable String pageNumber){
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
    @RequestMapping("/getMdseDetail/{mdseId}/")
    public Result<MdseEntity> getMdseDetail(@PathVariable String mdseId){
        Long id = null;
        try{
            id = Long.parseLong(mdseId);
        }catch (Exception e){
            return Result.buildFail("mdseId不正确");
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
