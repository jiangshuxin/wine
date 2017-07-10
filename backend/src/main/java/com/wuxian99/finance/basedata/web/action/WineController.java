package com.wuxian99.finance.basedata.web.action;

import com.wuxian99.finance.basedata.domain.BannerEntity;
import com.wuxian99.finance.basedata.service.wine.BannerService;
import com.wuxian99.finance.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sxjiang on 2017/7/10.
 */
@RestController
@RequestMapping("/api/front")
public class WineController {
    @Autowired
    BannerService bannerService;

    @RequestMapping("/getBannerByMerchantId/{merchangId}")
    public Result<List<BannerEntity>> getBannerByMerchantId(@PathVariable String merchangId){
        return Result.buildSuccess(bannerService.findByMerchantId(merchangId));
    }
}
