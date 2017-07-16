package com.wuxian99.finance.basedata.domain.entity.system;

import com.google.gson.Gson;

import javax.persistence.*;


/**
 * @author fczheng 数据字典
 */
@Entity(name = "ddic_item")
public class DdicItemEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "item_key")
	private String itemKey;
	@Column(name = "item_value")
	private String itemValue;
	@Column
	private String description;
	@Column(name = "sort_no")
	private String sortNo;
	@Column(name = "category_id")
	private Integer categoryId;
	@Column(name = "attribute1")
	private String attribute1;
	@Column(name = "attribute2")
	private String attribute2;
	@Column(name = "attribute3")
	private String attribute3;
	@Column(name = "attribute4")
	private String attribute4;
	@Column(name = "attribute5")
	private String attribute5;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
