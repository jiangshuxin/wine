package com.wuxian99.finance.basedata.domain.model;

/**
 * 下拉框
 *
 */
public class Select {
	private String key;
	private String value;

	public Select() {
	}

	public Select(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
