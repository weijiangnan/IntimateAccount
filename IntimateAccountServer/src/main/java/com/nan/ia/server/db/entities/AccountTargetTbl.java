package com.nan.ia.server.db.entities;

// Generated 2015-6-1 23:38:06 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AccountTargetTbl generated by hbm2java
 */
@Entity
@Table(name = "account_target_tbl", catalog = "intimate_account_db")
public class AccountTargetTbl implements java.io.Serializable {

	private Integer accountTargetId;
	private int accountBookId;
	private Double targetValue;
	private String description;
	private int createUserId;
	private Date createTime;
	private Date updateTime;

	public AccountTargetTbl() {
	}

	public AccountTargetTbl(int accountBookId, int createUserId,
			Date createTime, Date updateTime) {
		this.accountBookId = accountBookId;
		this.createUserId = createUserId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public AccountTargetTbl(int accountBookId, Double targetValue,
			String description, int createUserId, Date createTime,
			Date updateTime) {
		this.accountBookId = accountBookId;
		this.targetValue = targetValue;
		this.description = description;
		this.createUserId = createUserId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "account_target_id", unique = true, nullable = false)
	public Integer getAccountTargetId() {
		return this.accountTargetId;
	}

	public void setAccountTargetId(Integer accountTargetId) {
		this.accountTargetId = accountTargetId;
	}

	@Column(name = "account_book_id", nullable = false)
	public int getAccountBookId() {
		return this.accountBookId;
	}

	public void setAccountBookId(int accountBookId) {
		this.accountBookId = accountBookId;
	}

	@Column(name = "target_value", precision = 22, scale = 0)
	public Double getTargetValue() {
		return this.targetValue;
	}

	public void setTargetValue(Double targetValue) {
		this.targetValue = targetValue;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "create_user_id", nullable = false)
	public int getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
