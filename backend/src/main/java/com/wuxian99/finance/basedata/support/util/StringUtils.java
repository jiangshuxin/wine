package com.wuxian99.finance.basedata.support.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	/**
	 * @param word
	 * @return
	 */
	public static String firstLetterToLower(String word) {
		return word.substring(0, 1).toLowerCase() + word.substring(1);
	}
	
	/**
	 * @param word
	 * @return
	 */
	public static String firstLetterToUpper(String word) {
		return certainToUpper(1, word);
	}
	
	public static String certainToUpper(int n, String word) {
		if (word == null || word.length() < n)
			return word;
		return word.substring(0, n - 1) + word.substring(n - 1, n).toUpperCase() + word.substring(n);
	}
	
	public static String findIgnoreSuffix(List<String> list, String str) {
		if (CollectionUtils.isEmpty(list)) {
			return "";
		}
		for (String suffix : list) {
			if (endsWithIgnoreCase(str, suffix)) {
				return suffix;
			}
		}
		return "";
	}
	
	public static void main(String[] args) {
		System.out.println(certainToUpper(5, "initproject"));
		System.out.println(certainToUpper(1, "initproject"));
	}
}
