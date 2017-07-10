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
	private String sortNo;

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

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
}
