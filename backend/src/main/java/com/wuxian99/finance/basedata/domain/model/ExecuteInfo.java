package com.wuxian99.finance.basedata.domain.model;

import com.google.gson.Gson;

/**
 * @author fczheng
 * 封装通用service执行所需要的信息
 */
public class ExecuteInfo {
	private String action;
	private String repository;
	private Object[] args;
	private String verificationResult;
	
	private MetadataInfo metadata;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	public MetadataInfo getMetadata() {
		return metadata;
	}
	public void setMetadata(MetadataInfo metadata) {
		this.metadata = metadata;
	}
	public String getVerificationResult() {
		return verificationResult;
	}
	public void setVerificationResult(String verificationResult) {
		this.verificationResult = verificationResult;
	}
}
