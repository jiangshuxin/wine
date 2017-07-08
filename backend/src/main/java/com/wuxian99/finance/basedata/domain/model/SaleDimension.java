package com.wuxian99.finance.basedata.domain.model;

import java.util.List;

public class SaleDimension {
	private String dimension;
	private List<Select> selectList;
	public SaleDimension(String dimension) {
		this.dimension = dimension;
	}
	
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public List<Select> getSelectList() {
		return selectList;
	}
	public void setSelectList(List<Select> selectList) {
		this.selectList = selectList;
	}
}
