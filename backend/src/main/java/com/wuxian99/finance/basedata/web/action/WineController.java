package com.wuxian99.finance.basedata.web.action;

import com.wuxian99.finance.basedata.domain.BannerEntity;
import com.wuxian99.finance.basedata.domain.DiscoverEntity;
import com.wuxian99.finance.basedata.service.wine.BannerService;
import com.wuxian99.finance.basedata.service.wine.DiscoverService;
import com.wuxian99.finance.common.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/getDiscovers/{merchantId}/{pageNumber}")
    public Result<List<DiscoverEntity>> getBanners(@PathVariable String merchantId, @PathVariable String pageNumber){
        List<DiscoverEntity> discovers =  discoverService.findByMerchantId(merchantId, buildPageNumber(pageNumber), pageSize);
        if(CollectionUtils.isNotEmpty(discovers)){
            for(DiscoverEntity discover : discovers){
                discover.setPic(picPath + discover.getPic());
            }
        }
        return Result.buildSuccess(discovers);
    }

    private int buildPageNumber(String pageNumber){
        try{
            return Integer.parseInt(pageNumber);
        }catch (Exception e){
            return 1;
        }
    }
}
