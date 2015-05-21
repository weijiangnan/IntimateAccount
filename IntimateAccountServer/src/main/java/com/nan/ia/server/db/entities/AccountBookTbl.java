package com.nan.ia.server.db.entities;

// Generated 2015-5-21 14:20:58 by Hibernate Tools 3.4.0.CR1

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
 * AccountBookTbl generated by hbm2java
 */
@Entity
@Table(name = "account_book_tbl", catalog = "intimate_account_db")
public class AccountBookTbl implements java.io.Serializable {

	private Integer accountBookId;
	private String name;
	private String description;
	private int createUserId;
	private Date createTime;
	private Date updateTime;

	public AccountBookTbl() {
	}

	public AccountBookTbl(String name, int createUserId, Date createTime,
			Date updateTime) {
		this.name = name;
		this.createUserId = createUserId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public AccountBookTbl(String name, String description, int createUserId,
			Date createTime, Date updateTime) {
		this.name = name;
		this.description = description;
		this.createUserId = createUserId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "account_book_id", unique = true, nullable = false)
	public Integer getAccountBookId() {
		return this.accountBookId;
	}

	public void setAccountBookId(Integer accountBookId) {
		this.accountBookId = accountBookId;
	}

	@Column(name = "name", nullable = false, length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
