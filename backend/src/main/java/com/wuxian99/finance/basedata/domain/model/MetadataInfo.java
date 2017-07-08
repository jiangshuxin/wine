package com.wuxian99.finance.basedata.domain.model;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import java.beans.PropertyDescriptor;

public class MetadataInfo {
	private String moduleName;
	private String tableName;
	private String entityClass;
	private Class<?> entityJavaType;
	private PropertyDescriptor[] pds;
	private String repositoryInstance;
	private Object repositoryBean;
	private String displayName;
	private Integer sortNo;
	private Integer needLog;
	private Integer needUpload;
	private MultiMap ddicInfo = new MultiValueMap();
	//TODO entity同步更新
	private Integer batchSize;
	private String batchSql;
	private String checkOption;
	private String encoding;
	
	public MetadataInfo(){
		
	}
	public MetadataInfo(String moduleName){
		this.moduleName =moduleName;
	}
	
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}
	public String getRepositoryInstance() {
		return repositoryInstance;
	}
	public void setRepositoryInstance(String repositoryInstance) {
		this.repositoryInstance = repositoryInstance;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public MultiMap getDdicInfo() {
		return ddicInfo;
	}
	public void setDdicInfo(MultiMap ddicInfo) {
		this.ddicInfo = ddicInfo;
	}
	public Class<?> getEntityJavaType() {
		return entityJavaType;
	}
	public void setEntityJavaType(Class<?> entityJavaType) {
		this.entityJavaType = entityJavaType;
	}
	public PropertyDescriptor[] getPds() {
		return pds;
	}
	public void setPds(PropertyDescriptor[] pds) {
		this.pds = pds;
	}

	public Integer getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}

	public String getBatchSql() {
		return batchSql;
	}

	public void setBatchSql(String batchSql) {
		this.batchSql = batchSql;
	}

	public String getCheckOption() {
		return checkOption;
	}

	public void setCheckOption(String checkOption) {
		this.checkOption = checkOption;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public Integer getNeedLog() {
		return needLog;
	}
	public void setNeedLog(Integer needLog) {
		this.needLog = needLog;
	}
	public Integer getNeedUpload() {
		return needUpload;
	}
	public Object getRepositoryBean() {
		return repositoryBean;
	}
	public void setRepositoryBean(Object repositoryBean) {
		this.repositoryBean = repositoryBean;
	}
	public void setNeedUpload(Integer needUpload) {
		this.needUpload = needUpload;
	}
	
}
