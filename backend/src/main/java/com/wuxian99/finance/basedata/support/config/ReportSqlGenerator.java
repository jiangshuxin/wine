package com.wuxian99.finance.basedata.support.config;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix ="sql")
public class ReportSqlGenerator {
	// 库存
	private String stockMdse;
	private String stockCoupon;
	private String stockExchange;
	
	// 资金账户
	private String middleAccountCheck;
	private String supplierAccountCheck;

	//业务结算
	private String bizSettlement;
	
	public String getStockMdse() {
		return stockMdse;
	}

	public void setStockMdse(String stockMdse) {
		this.stockMdse = stockMdse;
	}

	public String getStockCoupon() {
		return stockCoupon;
	}

	public void setStockCoupon(String stockCoupon) {
		this.stockCoupon = stockCoupon;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public String getMiddleAccountCheck() {
		return middleAccountCheck;
	}

	public void setMiddleAccountCheck(String middleAccountCheck) {
		this.middleAccountCheck = middleAccountCheck;
	}

	public String getSupplierAccountCheck() {
		return supplierAccountCheck;
	}

	public void setSupplierAccountCheck(String supplierAccountCheck) {
		this.supplierAccountCheck = supplierAccountCheck;
	}

	public String getBizSettlement() {
		return bizSettlement;
	}

	public void setBizSettlement(String bizSettlement) {
		this.bizSettlement = bizSettlement;
	}

	public String getSql(Object bean, String moduleName) {
		try {
			return (String) PropertyUtils.getProperty(bean, moduleName);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Map<String,String> toMap(){
		try {
			return BeanUtils.describe(this);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return new HashMap<>();
	}
}
