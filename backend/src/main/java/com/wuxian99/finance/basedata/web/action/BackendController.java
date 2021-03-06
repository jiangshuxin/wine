package com.wuxian99.finance.basedata.web.action;

import com.wuxian99.finance.basedata.domain.AmountDetailEntity;
import com.wuxian99.finance.basedata.domain.MerchantEntity;
import com.wuxian99.finance.basedata.domain.UserEntity;
import com.wuxian99.finance.basedata.domain.model.Select;
import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.repository.wine.AmountDetailRepository;
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

import javax.servlet.http.HttpSession;
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

    @Autowired
    AmountDetailRepository amountDetailRepository;

    @RequestMapping(value="/merchant/queryAll", method={RequestMethod.POST,RequestMethod.GET})
    public Result<List<Select>> getAllMerchant(HttpSession session){
        List<Select> selectList = new ArrayList<>();
        SigninUser user = (SigninUser)session.getAttribute("signinUser");
        if("admin".equals(user.getAccount())){
            Select all = new Select("------请选择-----","");
            selectList.add(all);
            List<MerchantEntity> merchantEntities = merchantService.queryAll();
            if(CollectionUtils.isNotEmpty(merchantEntities)){
                merchantEntities.forEach(merchantEntity -> {
                    Select select = new Select(merchantEntity.getName(),merchantEntity.getMerchantId());
                    selectList.add(select);
                });
            }
        }else{
            Select select = new Select(user.getName(),user.getAccount());
            selectList.add(select);
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
    public Result<Integer> changeUserBalance(@Validated @RequestBody ChangeBalanceDto changeBalanceDto, BindingResult bindingResult, HttpSession session){
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

        SigninUser operator = (SigninUser)session.getAttribute("signinUser");
        AmountDetailEntity detail1 = new AmountDetailEntity();
        detail1.setOperator(operator.getAccount());
        detail1.setPreBalance(original.longValue());
        detail1.setAmount(changeBalanceDto.getAmount().longValue());
        detail1.setAfterBalance(original.longValue() - changeBalanceDto.getAmount().longValue());
        detail1.setType(4L);
        detail1.setUserId(userEntity.getId());
        detail1.setUserName(userEntity.getUserName());
        amountDetailRepository.save(detail1);

        return Result.buildSuccess(result);
    }
}
