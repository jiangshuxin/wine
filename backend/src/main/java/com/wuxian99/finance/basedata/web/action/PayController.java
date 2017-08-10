package com.wuxian99.finance.basedata.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wuxian99.finance.basedata.service.pay.WxPayService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;


@Controller
@RequestMapping("/pay")
public class PayController {
	

	@Autowired
	private WxPayService wxPayService;
	
	private static final Logger logger = LoggerFactory.getLogger(PayController.class);
	
	@RequestMapping(value="/wxPayNotify", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String wxPayNotify(HttpServletRequest request){
		String content = null;
		try{
			content = IOUtils.toString(request.getInputStream());
			logger.info("收到微信支付结果通知, content:{}", content);
			wxPayService.payNotify(content);
		}catch(Exception e){
			logger.error("处理微信支付结果通知异常, content:{}", content, e);
		}
		return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
	}
	
}
