package com.wuxian99.finance.basedata.domain.model;

public class TaskLogInfo {
	private Integer id;
	private String taskPurpose;  
	private String targetMethod;
	private String targetParameter;
	private Integer status;
	private String errorMessage;
	
	private String isSingle;
	private String target;
	private String department;
	
	private String taskPurposeName;
	private String taskTypeName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskPurpose() {
		return taskPurpose;
	}

	public void setTaskPurpose(String taskPurpose) {
		this.taskPurpose = taskPurpose;
	}

	public String getTargetMethod() {
		return targetMethod;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

	public String getTargetParameter() {
		return targetParameter;
	}

	public void setTargetParameter(String targetParameter) {
		this.targetParameter = targetParameter;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getTaskPurposeName() {
		return taskPurposeName;
	}

	public void setTaskPurposeName(String taskPurposeName) {
		this.taskPurposeName = taskPurposeName;
	}
	
	public String getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(String isSingle) {
		this.isSingle = isSingle;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTaskTypeName() {
		return taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}
	
}
