package com.nan.ia.server.db.entities;

// Generated 2015-5-17 18:02:39 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AccountItemDeleteTbl generated by hbm2java
 */
@Entity
@Table(name = "account_item_delete_tbl", catalog = "intimate_account_db")
public class AccountItemDeleteTbl implements java.io.Serializable {

	private int accountCategoryId;
	private int deleteUserId;
	private Date createTime;
	private Date deleteTime;

	public AccountItemDeleteTbl() {
	}

	public AccountItemDeleteTbl(int accountCategoryId, int deleteUserId,
			Date createTime, Date deleteTime) {
		this.accountCategoryId = accountCategoryId;
		this.deleteUserId = deleteUserId;
		this.createTime = createTime;
		this.deleteTime = deleteTime;
	}

	@Id
	@Column(name = "account_category_id", unique = true, nullable = false)
	public int getAccountCategoryId() {
		return this.accountCategoryId;
	}

	public void setAccountCategoryId(int accountCategoryId) {
		this.accountCategoryId = accountCategoryId;
	}

	@Column(name = "delete_user_id", nullable = false)
	public int getDeleteUserId() {
		return this.deleteUserId;
	}

	public void setDeleteUserId(int deleteUserId) {
		this.deleteUserId = deleteUserId;
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
	@Column(name = "delete_time", nullable = false, length = 19)
	public Date getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

}