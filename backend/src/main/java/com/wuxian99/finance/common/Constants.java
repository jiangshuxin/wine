package com.wuxian99.finance.common;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

public class Constants {
	public static final String SAVE = "save";
	public static final String DELETE = "deleteInBatch";
	public static final String FIND = "findAll";
	public static final MultiValueMap<String, String> ACTION_MAP = new LinkedMultiValueMap<String, String>() {
		private static final long serialVersionUID = 4548427692855177385L;
		{
			put(SAVE, Arrays.asList("create", "save", "update", "edit", "modify"));
			put(DELETE, Arrays.asList("remove", "delete"));
			put(FIND, Arrays.asList("findAll", "findOne", "query"));
		}
	};
	
	
	public static final String DEFAULT_USER = "admin";
	public static final String UNDERLINE_SEPERATOR = "_";
	public static final String MINUS_SEPERATOR = "-";
	public static final String TILDE_SEPERATOR = "~";
	public static final Integer UN_CHECKED = 3;
	
	public static final String TOP = "top";
	public static final Double DIVISOR = 1000000.0d;
}
