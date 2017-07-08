package com.wuxian99.finance.basedata.support.util;

public class MetadataUtils {
	public static final String STOCK_CHECK = "stockCheck"; // 库存盘点模块
	public static final String ACCOUNT_CHECK = "accountCheck"; // 账户对账模块

	public static final String BEGING_END_CACULATE = "beginEndCaculate"; // 期初期末计算
	public static final String PURCHASE_ORDER = "purchaseOrder";
	public static final String SALE_ORDER = "saleOrder";
	public static final String PAYMENT_ORDER = "paymentOrder";
	public static final String COMMISION = "commssion";

	public static final String STOCK = "stock";
	public static final String CHECK = "check";
	public static final String CHANGE_SUFFIX = "change"; // 变动模块后缀
	public static final String FILE_SUFFIX = ".xls";
	public static final String ALL_ORDER_PREFIX = "all";

	public static final String MDSE = "stockMdse";
	public static final String COUPON = "stockCoupon";
	public static final String EXCHANGE = "stockExchange";

	public static final String EXCEL = "Excel";
	public static final String PROCEDURE = "procedure";

	/**
	 * @param moduleName
	 * @return
	 */
	public static String getDimension(String moduleName) {
		return StringUtils.firstLetterToUpper(moduleName.replace(StringUtils.firstLetterToUpper(SALE_ORDER), ""));
	}

	public static boolean isAccountCheck(String moduleName) {
		return StringUtils.endsWithIgnoreCase(moduleName, ACCOUNT_CHECK);
	}

	public static boolean isStockCheck(String moduleName) {
		return moduleName.startsWith(STOCK) && !isChangeModule(moduleName);
	}

	private static boolean isChangeModule(String moduleName) {
		return StringUtils.endsWithIgnoreCase(moduleName, CHANGE_SUFFIX);
	}

	/**
	 * @param purpose
	 * @param moduleName
	 * @return
	 */
	public static String getMethodSuffix(String purpose, String moduleName) {
		if (BEGING_END_CACULATE.equals(purpose) || PROCEDURE.equals(purpose)) {
			return StringUtils.firstLetterToUpper(purpose);
		} else if (CHECK.equals(purpose)) {
			if (StringUtils.containsIgnoreCase(moduleName, STOCK))
				return StringUtils.firstLetterToUpper(STOCK_CHECK);
			else
				return StringUtils.firstLetterToUpper(ACCOUNT_CHECK);
		} else {
			return moduleName + StringUtils.firstLetterToUpper(purpose);
		}
	}
}
