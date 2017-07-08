package com.wuxian99.finance.basedata.domain.entity.system;

import com.wuxian99.finance.basedata.support.annotation.Ddic;

import javax.persistence.*;

@Entity(name = "task_log")
public class TaskLogEntity {
	@Id
	@GeneratedValue(generator="TASK_LOG_SEQ")
	@SequenceGenerator(name = "TASK_LOG_SEQ", sequenceName = "TASK_LOG_SEQ", allocationSize = 1)
	private Integer id;
	@Column(name = "is_single")
	private String isSingle;
	@Column(name = "task_purpose")
	private String taskPurpose;  
	@Column(name = "target_method")
	private String targetMethod;
	@Column(name = "target_parameter")
	private String targetParameter;
	@Column
	private Integer status;
	@Column(name = "error_message")
	private String errorMessage;
	
	@Transient
	private String taskTypeName;
	@Transient
	private String taskPurposeName;
	
	@Column
	private String target;
	@Column
	private String department;
	
	public TaskLogEntity() {}
	public TaskLogEntity(String targetMethod, String targetParameter) {
		this.targetMethod = targetMethod;
		this.targetParameter = targetParameter;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Ddic(name="task_type", mapTo="taskTypeName")
	public String getIsSingle() {
		return isSingle;
	}
	public void setIsSingle(String isSingle) {
		this.isSingle = isSingle;
	}
	@Ddic(name="task_purpose", mapTo="taskPurposeName")
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
	public String getTaskTypeName() {
		return taskTypeName;
	}
	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}
	public String getTaskPurposeName() {
		return taskPurposeName;
	}
	public void setTaskPurposeName(String taskPurposeName) {
		this.taskPurposeName = taskPurposeName;
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
}
