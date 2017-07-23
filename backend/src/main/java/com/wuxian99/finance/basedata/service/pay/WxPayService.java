package com.wuxian99.finance.basedata.service.pay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.wuxian99.finance.basedata.domain.OrderEntity;
import com.wuxian99.finance.basedata.service.pay.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信支付不支持指定收款账号，所以需要商家去申请微信支付商户
 * APP_ID		微信公众平台获取
 * MCH_ID		微信商户平台获取
 * SECRET_KEY	微信商户平台获取（需设置）
 * 证书			微信商户平台(pay.weixin.qq.com)-->账户中心-->账户设置-->API安全-->证书下载
 * @author swang
 *
 */
@Service
public class WxPayService {

	private final static String APP_ID = "wx79c1c7fa2255f655";
	private final static String MCH_ID = "1484630812";
	private final static String SECRET_KEY = "qwertyuiop87878983asdjfasdfasdtt";
	private final static String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	private final static String QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	private final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	private final static String NOTIFY_URL = "http://test.xxxwine.com/wine/pay/wxPayNotify";
	private final static String SERVER_IP = "";
	private static final Logger logger = LoggerFactory.getLogger(WxPayService.class);
	
	/**
	 * 支付
	 * @param order
	 * @param payType 1:微信支付，2:扫码支付
	 * @return
	 */
	public OrderEntity pay(OrderEntity order, Long payType) {
		
		try{
			String currTime = PayCommonUtil.getCurrTime();
			String strTime = currTime.substring(8, currTime.length());  
			String strRandom = PayCommonUtil.buildRandom(4) + "";  
			String nonce_str = strTime + strRandom;  
			SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
			packageParams.put("appid", APP_ID);
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("nonce_str", nonce_str);  
			packageParams.put("body", "财富酒庄");
			packageParams.put("out_trade_no", order.getId()+"");
			//单位分
			packageParams.put("total_fee", order.getPayAmount() + "");  
			packageParams.put("spbill_create_ip", SERVER_IP);  
			packageParams.put("notify_url", NOTIFY_URL);  
			packageParams.put("trade_type", "NATIVE");  

			String sign = PayCommonUtil.createSign("UTF-8", packageParams, SECRET_KEY);
			packageParams.put("sign", sign);  
			String requestXML = PayCommonUtil.getRequestXml(packageParams); 
			logger.info("订单[{}]发起微信支付请求参数:{}，", order.getId(), requestXML);
			String resXml = HttpUtil.postData(PAY_URL, requestXML);
			logger.info("订单[{}]发起微信支付返回参数:{}", order.getId(), resXml);
			Map<String, String> map = XMLUtil.doXMLParse(resXml);
			String return_code = (String) map.get("return_code");
			if(StringUtils.equals(return_code, "SUCCESS")){
				String payUrl = (String) map.get("code_url");
				String prepay_id = (String) map.get("prepay_id");  
				if(StringUtils.isNotEmpty(prepay_id)){
					order.setPaySeqs(prepay_id);
					if(payType == 2 && StringUtils.isNotBlank(payUrl)){
						String payPicName = QRCodeUtil.createQRCodeImg(order.getId(), payUrl, "weixin");
						order.setPayPic("pay/" + payPicName);
					}
					return order;
				}
			}
		}catch(Exception e){
			logger.error("订单[{}]发起微信支付异常", order.getId(), e);
		}
		return null; 
	}
	
	/**
	 * 查询支付状态
	 * @param order
	 * @return
	 */
	public OrderEntity queryPayStatus(OrderEntity order){
		
		try{
			String currTime = PayCommonUtil.getCurrTime();  
			String strTime = currTime.substring(8, currTime.length());  
			String strRandom = PayCommonUtil.buildRandom(4) + "";  
			String nonce_str = strTime + strRandom;  
			SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
			packageParams.put("appid", APP_ID);
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("nonce_str", nonce_str);  
			packageParams.put("out_trade_no", order.getId());
			String sign = PayCommonUtil.createSign("UTF-8", packageParams, SECRET_KEY);
			packageParams.put("sign", sign);
			String requestXML = PayCommonUtil.getRequestXml(packageParams); 
			logger.info("订单[{}]查询微信支付状态请求参数:{}，", order.getId(), requestXML);
			String resXml = HttpUtil.postData(QUERY_URL, requestXML);
			logger.info("订单[{}]查询微信支付状态返回参数:{}", order.getId(), resXml);
			Map<String, String> map = XMLUtil.doXMLParse(resXml); 
			String return_code = (String) map.get("return_code");
			String trade_state = (String) map.get("trade_state");
			if(StringUtils.equals(return_code, "SUCCESS")){
				if(StringUtils.equals(trade_state, "SUCCESS")){
					//TODO 更新支付完成时间、支付状态
					return order;
				}
			}
		}catch(Exception e){
			logger.error("订单[{}]查询微信支付状态异常", order.getId(), e);
		}
		return null;
	}
	
	/**
	 * 退款
	 * @param order
	 * @return
	 */
	public OrderEntity refund(OrderEntity order) {
		
		try {
			String currTime = PayCommonUtil.getCurrTime();  
			String strTime = currTime.substring(8, currTime.length());  
			String strRandom = PayCommonUtil.buildRandom(4) + "";  
			String nonce_str = strTime + strRandom;  
			SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
			packageParams.put("appid", APP_ID);  
			packageParams.put("mch_id", MCH_ID);  
			packageParams.put("nonce_str", nonce_str);  
			packageParams.put("out_trade_no", order.getId());
			packageParams.put("out_refund_no", order.getId());
			packageParams.put("total_fee", order.getPayAmount() + "");
			packageParams.put("refund_fee", order.getPayAmount() + "");
			//packageParams.put("refund_fee_type", "CNY");
			//packageParams.put("op_user_id", MCH_ID);
			String sign = PayCommonUtil.createSign("UTF-8", packageParams, SECRET_KEY);  
			packageParams.put("sign", sign);  
			String requestXML = PayCommonUtil.getRequestXml(packageParams);  
			logger.info("订单[{}]发起微信退款请求参数:{}，", order.getId(), requestXML);
			//微信退款需要用证书
			String resXml = HttpsUtil.postData(MCH_ID, REFUND_URL, requestXML);
			logger.info("订单[{}]发起微信退款返回参数:{}", order.getId(), resXml);
			if(StringUtils.isNotEmpty(resXml)){
				Map<String, String> map = XMLUtil.doXMLParse(resXml);
				String return_code = (String) map.get("return_code");
				String result_code = (String) map.get("result_code");
				if("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
					//TODO 更新退款发起时间和退款状态
					return order;
				}
			}
		}catch(Exception e){
			logger.error("订单[{}]发起微信退款异常", order.getId(), e);
		}
		
		return null;
	}
	
	/**
	 * 查询退款状态
	 * @param order
	 * @return
	 */
	public OrderEntity queryRefundStatus(OrderEntity order){
		
		try{
			String currTime = PayCommonUtil.getCurrTime();  
			String strTime = currTime.substring(8, currTime.length());  
			String strRandom = PayCommonUtil.buildRandom(4) + "";  
			String nonce_str = strTime + strRandom;  
			SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
			packageParams.put("appid", APP_ID);  
			packageParams.put("mch_id", MCH_ID);  
			packageParams.put("nonce_str", nonce_str);  
			packageParams.put("out_trade_no", order.getId());
			String sign = PayCommonUtil.createSign("UTF-8", packageParams, SECRET_KEY);  
			packageParams.put("sign", sign);
			String requestXML = PayCommonUtil.getRequestXml(packageParams); 
			logger.info("订单[{}]查询微信退款状态请求参数:{}，", order.getId(), requestXML);
			String resXml = HttpUtil.postData(QUERY_URL, requestXML);
			logger.info("订单[{}]查询微信退款状态返回参数:{}", order.getId(), resXml);
			Map<String, String> map = XMLUtil.doXMLParse(resXml); 
			String return_code = (String) map.get("return_code");
			String trade_state = (String) map.get("trade_state");
			if(StringUtils.equals(return_code, "SUCCESS")){
				if(StringUtils.equals(trade_state, "SUCCESS")){
					//TODO 更新退款完成时间、退款状态
					return order;
				}
			}
		}catch(Exception e){
			logger.error("订单[{}]查询支付状态异常", order.getId(), e);
		}
		return null;
	}
	
	/**
	 * 支付结果通知
	 * @param content
	 * @return
	 * @throws DocumentException
	 */
	public void payNotify(String content) throws DocumentException {
		
		Document doc = DocumentHelper.parseText(content);
		Element rootElt = doc.getRootElement();
		String return_code = rootElt.element("return_code").getText();
		String return_msg = rootElt.elementText("return_msg");
		if(!"SUCCESS".equals(return_code)){
			return;
		}
		String result_code = rootElt.element("result_code").getText();
		if(!"SUCCESS".equals(result_code)){
			return;
		}
		String appid = rootElt.element("appid").getText();
		String mch_id = rootElt.element("mch_id").getText();
		String device_info = rootElt.elementText("device_info");
		String nonce_str = rootElt.element("nonce_str").getText();
		String sign1 = rootElt.element("sign").getText();
		String err_code = rootElt.elementText("err_code");
		String err_code_des = rootElt.elementText("err_code_des");
		String openid = rootElt.elementText("openid");
		String is_subscribe = rootElt.elementText("is_subscribe");
		String trade_type = rootElt.elementText("trade_type");
		String bank_type = rootElt.elementText("bank_type");
		String total_fee = rootElt.elementText("total_fee");
		String fee_type = rootElt.elementText("fee_type");
		String cash_fee = rootElt.elementText("cash_fee");
		String cash_fee_type = rootElt.elementText("cash_fee_type");
		String coupon_fee = rootElt.elementText("coupon_fee");
		String coupon_count = rootElt.elementText("coupon_count");
		String transaction_id = rootElt.elementText("transaction_id");
		String out_trade_no = rootElt.element("out_trade_no").getText();
		String out_refund_no = rootElt.elementText("out_refund_no");
		String refund_id = rootElt.elementText("refund_id");
		String refund_channel = rootElt.elementText("refund_channel");
		String refund_fee = rootElt.elementText("refund_fee");
		String cash_refund_fee = rootElt.elementText("cash_refund_fee");
		String coupon_refund_fee = rootElt.elementText("coupon_refund_fee");
		String coupon_refund_count = rootElt.elementText("coupon_refund_count");
		String coupon_refund_id = rootElt.elementText("coupon_refund_id");
		String refund_count  = rootElt.elementText("refund_count");
		String attach = rootElt.elementText("attach");
		String time_end = rootElt.elementText("time_end");
		SortedMap<String, String> map1 = new TreeMap<String, String>();
		if(StringUtils.isNotEmpty(return_code))map1.put("return_code", return_code);
		if(StringUtils.isNotEmpty(return_msg))map1.put("return_msg", return_msg);
		if(StringUtils.isNotEmpty(appid))map1.put("appid", appid);
		if(StringUtils.isNotEmpty(mch_id))map1.put("mch_id", mch_id);
		if(StringUtils.isNotEmpty(device_info))map1.put("device_info", device_info);
		if(StringUtils.isNotEmpty(nonce_str))map1.put("nonce_str", nonce_str);
		if(StringUtils.isNotEmpty(result_code))map1.put("result_code", result_code);
		if(StringUtils.isNotEmpty(err_code))map1.put("err_code", err_code);
		if(StringUtils.isNotEmpty(err_code_des))map1.put("err_code_des", err_code_des);
		if(StringUtils.isNotEmpty(openid))map1.put("openid", openid);
		if(StringUtils.isNotEmpty(is_subscribe))map1.put("is_subscribe", is_subscribe);
		if(StringUtils.isNotEmpty(trade_type))map1.put("trade_type", trade_type);
		if(StringUtils.isNotEmpty(bank_type))map1.put("bank_type", bank_type);
		if(StringUtils.isNotEmpty(total_fee))map1.put("total_fee", total_fee);
		if(StringUtils.isNotEmpty(fee_type))map1.put("fee_type", fee_type);
		if(StringUtils.isNotEmpty(cash_fee))map1.put("cash_fee", cash_fee);
		if(StringUtils.isNotEmpty(cash_fee_type))map1.put("cash_fee_type", cash_fee_type);
		if(StringUtils.isNotEmpty(coupon_fee))map1.put("coupon_fee", coupon_fee);
		if(StringUtils.isNotEmpty(coupon_count)){
			map1.put("coupon_count", coupon_count);
			int iCount = Integer.parseInt(coupon_count);
			for(int i = 0; i < iCount; i++){
				String coupon_batch_id_$n = rootElt.elementText("coupon_batch_id_"+i);
				if(StringUtils.isNotEmpty(coupon_batch_id_$n))map1.put("coupon_batch_id_"+i, coupon_batch_id_$n);
				String coupon_id_$n = rootElt.elementText("coupon_id_"+i);
				if(StringUtils.isNotEmpty(coupon_id_$n))map1.put("coupon_id_"+i, coupon_id_$n);
				String coupon_fee_$n = rootElt.elementText("coupon_fee_"+i);
				if(StringUtils.isNotEmpty(coupon_fee_$n))map1.put("coupon_fee_"+i, coupon_fee_$n);
			}
		}
		if(StringUtils.isNotEmpty(transaction_id))map1.put("transaction_id", transaction_id);
		if(StringUtils.isNotEmpty(out_trade_no))map1.put("out_trade_no", out_trade_no);
		if(StringUtils.isNotEmpty(out_refund_no))map1.put("out_refund_no", out_refund_no);
		if(StringUtils.isNotEmpty(refund_id))map1.put("refund_id", refund_id);
		if(StringUtils.isNotEmpty(refund_channel))map1.put("refund_channel", refund_channel);
		if(StringUtils.isNotEmpty(refund_fee))map1.put("refund_fee", refund_fee);
		if(StringUtils.isNotEmpty(cash_refund_fee))map1.put("cash_refund_fee", cash_refund_fee);
		if(StringUtils.isNotEmpty(coupon_refund_fee))map1.put("coupon_refund_fee", coupon_refund_fee);
		if(StringUtils.isNotEmpty(coupon_refund_count))map1.put("coupon_refund_count", coupon_refund_count);
		if(StringUtils.isNotEmpty(coupon_refund_id))map1.put("coupon_refund_id", coupon_refund_id);
		if(StringUtils.isNotEmpty(refund_count)){
			map1.put("refund_count", refund_count);
			int iCount = Integer.parseInt(refund_count);
			for(int i = 0; i < iCount; i++){
				String out_refund_no_$n = rootElt.elementText("out_refund_no_"+i);
				if(StringUtils.isNotEmpty(out_refund_no_$n))map1.put("out_refund_no_"+i, out_refund_no_$n);
				String refund_id_$n = rootElt.elementText("refund_id_"+i);
				if(StringUtils.isNotEmpty(refund_id_$n))map1.put("refund_id_"+i, refund_id_$n);
				String refund_channel_$n = rootElt.elementText("refund_channel_"+i);
				if(StringUtils.isNotEmpty(refund_channel_$n))map1.put("refund_channel_"+i, refund_channel_$n);
				String refund_fee_$n = rootElt.elementText("refund_fee_"+i);
				if(StringUtils.isNotEmpty(refund_fee_$n))map1.put("refund_fee_"+i, refund_fee_$n);
				String fee_type_$n = rootElt.elementText("fee_type_"+i);
				if(StringUtils.isNotEmpty(fee_type_$n))map1.put("fee_type_"+i, fee_type_$n);
				String coupon_refund_fee_$n = rootElt.elementText("coupon_refund_fee_"+i);
				if(StringUtils.isNotEmpty(coupon_refund_fee_$n))map1.put("coupon_refund_fee_"+i, coupon_refund_fee_$n);
				String coupon_refund_count_$n = rootElt.elementText("coupon_refund_count_"+i);
				if(StringUtils.isNotEmpty(coupon_refund_count_$n)){
					map1.put("coupon_refund_count_"+i, coupon_refund_count_$n);
					int jCount = Integer.parseInt(coupon_refund_count_$n);
					for(int j = 0; j < jCount; j++){
						String coupon_refund_batch_id_$n_$m = rootElt.elementText("fee_type_"+i+"_"+j);
						if(StringUtils.isNotEmpty(coupon_refund_batch_id_$n_$m))map1.put("fee_type_"+i+"_"+j, coupon_refund_batch_id_$n_$m);
						String coupon_refund_id_$n_$m = rootElt.elementText("coupon_refund_id_"+i+"_"+j);
						if(StringUtils.isNotEmpty(coupon_refund_id_$n_$m))map1.put("coupon_refund_id_"+i+"_"+j, coupon_refund_id_$n_$m);
						String coupon_refund_fee_$n_$m = rootElt.elementText("coupon_refund_fee_"+i+"_"+j);
						if(StringUtils.isNotEmpty(coupon_refund_fee_$n_$m))map1.put("coupon_refund_fee_"+i+"_"+j, coupon_refund_fee_$n_$m);
					}
				}
				String refund_status_$n = rootElt.elementText("refund_status_"+i);
				if(StringUtils.isNotEmpty(refund_status_$n))map1.put("refund_status_"+i, refund_status_$n);
			}
		}
		if(StringUtils.isNotEmpty(attach))map1.put("attach", attach);
		if(StringUtils.isNotEmpty(time_end))map1.put("time_end", time_end);
		if(StringUtils.isEmpty(out_trade_no)){
			return;
		}

		if(verify(map1, sign1, SECRET_KEY)){
			//OrderEntity order = orderDao.findByOrderNo(out_trade_no);
			//TODO 更新支付完成时间、支付状态
		}
	}
	
	public boolean verify(Map<String,String> map, String sign, String SECRET_KEY){
		String signExp = sign(map, SECRET_KEY);
		if(signExp.equals(sign)){
			return true;
		}
		return false;
	}
	
	public String sign(Map<String, String> sortedMap, String SECRET_KEY){
		List<String> kvList = new ArrayList<>();
		for(String key : sortedMap.keySet()){
			kvList.add(key + "=" + sortedMap.get(key));
		}
		String original = StringUtils.join(kvList, "&");
		String secret = "&key=" + SECRET_KEY;
		return CipherUtil.encodeByMD5(original+secret).toUpperCase();
	}
	
	public static void main(String[] args) {
//		new WxPayService().pay(order);
//		new WxPayService().refund(order);
	}
}
