package com.wuxian99.finance.basedata.domain.model;

public class PeriodEndNumber {
	private String department;
	private String moduleName;
	private String unifiedId;
	private String period;
	private Double endValue;
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getUnifiedId() {
		return unifiedId;
	}
	public void setUnifiedId(String unifiedId) {
		this.unifiedId = unifiedId;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Double getEndValue() {
		return endValue;
	}
	public void setEndValue(Double endValue) {
		this.endValue = endValue;
	} 
}
