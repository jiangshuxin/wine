package com.wuxian99.finance.basedata.service.pay.utils;

import java.io.File;   
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;   
import com.google.zxing.EncodeHintType;   
import com.google.zxing.MultiFormatWriter;   
import com.google.zxing.client.j2se.MatrixToImageWriter;   
import com.google.zxing.common.BitMatrix;   

/**
 * 二维码工具类
 * @author swang
 *
 */
public class QRCodeUtil {   

	private static final Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);
	
	/**
	 * 根据支付url生成二维码，图片文件名为：orderId_payType.png
	 * @param orderId
	 * @param payUrl
	 * @return
	 */
	public static String createQRCodeImg(Long orderId, String payUrl, String payType){
		try{
			HashMap<EncodeHintType, String> hints= new HashMap<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(payUrl, BarcodeFormat.QR_CODE, 200, 200, hints);
			String fileName = orderId + "_" + payType + ".png";
			String dir = "../static/wineStatic/images/pay/";
			if(!new File(dir).exists()){
				new File(dir).mkdirs();
			}
			File outputFile = new File(dir + fileName);
//			logger.info("iamgesPath:" + outputFile.getAbsoluteFile());
			MatrixToImageWriter.writeToFile(bitMatrix, "png", outputFile);
			return fileName;
		}catch(Exception e){
			logger.error("生成支付二维码异常, orderId:{}, payUrl:{}, payType:{}", orderId, payUrl, payType, e);
		}
		return null;
	}

}  