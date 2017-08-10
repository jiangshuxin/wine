package com.wuxian99.finance.basedata.domain.model;

/**
 * DataTable单选/多选组件使用的数据类型
 */
public class Select {
	private String label;
	private String value;

	public Select() {
	}

	public Select(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
