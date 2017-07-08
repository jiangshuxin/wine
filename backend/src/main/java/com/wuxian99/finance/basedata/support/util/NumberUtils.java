package com.wuxian99.finance.basedata.support.util;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {
	/**
	 * @param source
	 * @return
	 */
	public static Integer longToInteger(Long source) {
		return source == null ? null : source.intValue();
	}

	public static Double toDouble(Object source) {
		if(source==null) return null;
		return Double.valueOf(source.toString());
	}

	public static Integer toInt(Object source) {
		if(source==null) return null;
		return Integer.valueOf(source.toString());
	}
}
