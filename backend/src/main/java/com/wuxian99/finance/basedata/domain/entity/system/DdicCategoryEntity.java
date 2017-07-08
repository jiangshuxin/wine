package com.wuxian99.finance.basedata.domain.entity.system;

import javax.persistence.*;

/**
 * @author fczheng 数据字典目录
 *
 */
@Entity(name = "ddic_category")
public class DdicCategoryEntity {
	@Id
	@GeneratedValue(generator="DDIC_CATEGORY_SEQ")
	@SequenceGenerator(name = "DDIC_CATEGORY_SEQ", sequenceName = "DDIC_CATEGORY_SEQ", allocationSize = 1)
	private Integer id;
	@Column(nullable=false, updatable=false)
	private String name;
	@Column
	private String description;
	@Column(name="cn_name")
	private String cnName;

	public DdicCategoryEntity() {
	}

	public DdicCategoryEntity(Integer id) {
		this.id = id;
	}

	/*@OneToMany(mappedBy = "ddicCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DdicItemEntity> ddicItems;

	public List<DdicItemEntity> getDdicItems() {
		return ddicItems;
	}

	public void setDdicItems(List<DdicItemEntity> ddicItems) {
		this.ddicItems = ddicItems;
	}*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	@Override
	public String toString() {
		return "DdicCategoryEntity [id=" + id + ", name=" + name + ", description=" + description + ", cnName=" + cnName
				+ "]";
	}
	
}
