package com.wuxian99.finance.basedata.web.action;

import com.wuxian99.finance.basedata.domain.MerchantEntity;
import com.wuxian99.finance.basedata.domain.UserEntity;
import com.wuxian99.finance.basedata.domain.model.Select;
import com.wuxian99.finance.basedata.service.wine.MerchantService;
import com.wuxian99.finance.basedata.service.wine.OrderService;
import com.wuxian99.finance.basedata.service.wine.UserService;
import com.wuxian99.finance.basedata.web.dto.ChangeBalanceDto;
import com.wuxian99.finance.basedata.web.dto.ChangeOrderStatusDto;
import com.wuxian99.finance.common.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by za-jiangshuxin on 2017/8/9.
 */
@RestController
@RequestMapping("/backend")
public class BackendController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/merchant/queryAll", method={RequestMethod.POST,RequestMethod.GET})
    public Result<List<Select>> getAllMerchant(){
        List<Select> selectList = new ArrayList<>();
        List<MerchantEntity> merchantEntities = merchantService.queryAll();
        if(CollectionUtils.isNotEmpty(merchantEntities)){
            merchantEntities.forEach(merchantEntity -> {
                Select select = new Select(merchantEntity.getName(),merchantEntity.getMerchantId());
                selectList.add(select);
            });
        }
        return Result.buildSuccess(selectList);
    }

    @RequestMapping(value="/order/updateStatus", method={RequestMethod.POST})
    public Result<Integer> changeOrderStatus(@RequestBody ChangeOrderStatusDto changeOrderStatusDto){
        if(changeOrderStatusDto.getOrderId() == null || changeOrderStatusDto.getStatus() == null){
            return Result.buildFail("orderId or status cannot be null");
        }
        int data = orderService.updateOrderStatus(changeOrderStatusDto.getStatus(), changeOrderStatusDto.getOrderId());
        return Result.buildSuccess(data);
    }

    @RequestMapping(value="/user/updateBalance", method={RequestMethod.POST})
    public Result<Integer> changeUserBalance(@Validated @RequestBody ChangeBalanceDto changeBalanceDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return Result.buildFail(bindingResult.getFieldError());
        }
        UserEntity userEntity = userService.findByUserId(changeBalanceDto.getUserId());
        if(userEntity == null){
            return Result.buildFail("找不到用户信息");
        }
        BigDecimal original = new BigDecimal(userEntity.getBalance());
        if(changeBalanceDto.getAmount().compareTo(original) > 0){
            return Result.buildFail("打款金额不能超过余额");
        }
        BigDecimal balance = original.subtract(changeBalanceDto.getAmount());
        int result = userService.updateBalanceByUserId(balance.longValue(), changeBalanceDto.getUserId());
        return Result.buildSuccess(result);
    }
}
