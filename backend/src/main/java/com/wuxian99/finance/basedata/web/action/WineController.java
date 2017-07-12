package com.wuxian99.finance.basedata.web.action;

import com.wuxian99.finance.basedata.domain.BannerEntity;
import com.wuxian99.finance.basedata.domain.DiscoverDetailEntity;
import com.wuxian99.finance.basedata.domain.DiscoverEntity;
import com.wuxian99.finance.basedata.service.wine.BannerService;
import com.wuxian99.finance.basedata.service.wine.DiscoverService;
import com.wuxian99.finance.basedata.web.view.DiscoverView;
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

    @Value("${wine.picPath}")
    private String picPath;

    private int pageSize = 5;

    @Autowired
    BannerService bannerService;

    @Autowired
    DiscoverService discoverService;

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

    @RequestMapping("/getDiscovers/{merchantId}/{type}/{pageNumber}")
    public Result<List<DiscoverView>> getBanners(@PathVariable String merchantId, @PathVariable String type, @PathVariable String pageNumber){
        List<DiscoverEntity> discovers =  discoverService.findByMerchantIdAndType(merchantId, buildDiscoverType(type), buildPageNumber(pageNumber), pageSize);
        List<DiscoverView> discoverViews = new ArrayList<DiscoverView>();
        if(CollectionUtils.isNotEmpty(discovers)){
            for(DiscoverEntity discover : discovers){
                DiscoverView discoverView = new DiscoverView();
                discoverView.setDiscoverId(discover.getId());
                discoverView.setPic(picPath + discover.getPic());
                discoverView.setTitle(discover.getTitle());
                discoverView.setTag(discover.getTag());
                discoverView.setDescription(discover.getDescription());
                discoverViews.add(discoverView);
            }
        }
        return Result.buildSuccess(discoverViews);
    }

    @RequestMapping("/getDiscoverDetails/{discoverId}/")
    public Result<List<DiscoverDetailEntity>> getDiscoverDetails(@PathVariable String discoverId){

        Long discvId = null;
        try{
            discvId = Long.parseLong(discoverId);
        }catch (Exception e){
            return Result.buildFail("discoverId不正确");
        }
        List<DiscoverDetailEntity> discoverDetails =  discoverService.findDetailByDiscoverId(discvId);
        if(CollectionUtils.isNotEmpty(discoverDetails)){
            for(DiscoverDetailEntity discoverDetail : discoverDetails){
                discoverDetail.setPic(picPath + discoverDetail.getPic());
            }
        }
        return Result.buildSuccess(discoverDetails);
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
