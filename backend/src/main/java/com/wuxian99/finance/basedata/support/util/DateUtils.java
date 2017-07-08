package com.wuxian99.finance.basedata.support.util;

import com.wuxian99.finance.common.Constants;

import java.time.LocalDate;
import java.time.YearMonth;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	/**
	 * 2017-04--->2017-04-01~2017-04-30
	 * 
	 * @param yearMonthStr
	 * @return
	 */
	public static String getPeriodByYearMonth(String yearMonthStr) {
		YearMonth yearMonth = YearMonth.parse(yearMonthStr);
		return yearMonth.atDay(1) + Constants.TILDE_SEPERATOR + yearMonth.atEndOfMonth();
	}
	
	public static String getYesterday() {
		return LocalDate.now().plusDays(-1).toString();
	}
	
	public static String getLastMonth() {
		return YearMonth.now().plusMonths(-1).toString();
	}
}
