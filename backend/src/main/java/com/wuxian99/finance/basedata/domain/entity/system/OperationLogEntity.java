package com.wuxian99.finance.basedata.domain.entity.system;

import com.wuxian99.finance.common.Constants;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "operation_log")
public class OperationLogEntity {
	@Id
	@GeneratedValue(generator="OPERATION_LOG_SEQ")
	@SequenceGenerator(name = "OPERATION_LOG_SEQ", sequenceName = "OPERATION_LOG_SEQ", allocationSize = 1)
	private Long id;

	@Column(name = "operate_table")
	private String operateTable;
	@Column(name = "operate_type")
	private String operateType;
	@Column(name = "pre_persist", length=1024)
	private String prePersist;
	@Column(name = "post_persist", length=1024)
	private String postPersist;
	@Column
	private String operator;
	@Column(name = "operate_time")
	private Timestamp operateTime;
	@Column
	private String memo;

	@Transient
	private String operateTypeDisplayName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperateTable() {
		return operateTable;
	}

	public void setOperateTable(String operateTable) {
		this.operateTable = operateTable;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPrePersist() {
		return prePersist;
	}

	public void setPrePersist(String prePersist) {
		this.prePersist = prePersist;
	}

	public String getPostPersist() {
		return postPersist;
	}

	public void setPostPersist(String postPersist) {
		this.postPersist = postPersist;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Timestamp getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOperateTypeDisplayName() {
		return operateTypeDisplayName;
	}

	public void setOperateTypeDisplayName(String operateTypeDisplayName) {
		this.operateTypeDisplayName = operateTypeDisplayName;
	}

	@PrePersist
	protected void preOperate() {
		operateTime = new Timestamp(System.currentTimeMillis());
		if (StringUtils.isEmpty(this.operator))
			this.operator = Constants.DEFAULT_USER;
	}
}
