package com.wuxian99.finance.basedata.domain.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "metadata")
public class MetadataEntity {
	@Id
	@Column(name = "module_name")
	private String moduleName;
	@Column(name = "table_name")
	private String tableName;
	@Column(name = "display_name")
	private String displayName;
	@Column(name = "sort_no")
	private Integer sortNo;
	@Column(name = "need_log")
	private Integer needLog;
	@Column(name = "batch_size")
	private Integer batchSize;
	@Column(name = "batch_sql", length = 2048)
	private String batchSql;
	@Column(name = "check_option")
	private String checkOption;
	@Column
	private String encoding;
	@Column(name = "need_upload")
	private Integer needUpload;

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

	public Integer getNeedLog() {
		return needLog;
	}

	public void setNeedLog(Integer needLog) {
		this.needLog = needLog;
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

	public Integer getNeedUpload() {
		return needUpload;
	}

	public void setNeedUpload(Integer needUpload) {
		this.needUpload = needUpload;
	}
}
