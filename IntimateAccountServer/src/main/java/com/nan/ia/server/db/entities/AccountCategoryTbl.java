package com.nan.ia.server.db.entities;

// Generated 2015-5-27 14:32:56 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AccountCategoryTbl generated by hbm2java
 */
@Entity
@Table(name = "account_category_tbl", catalog = "intimate_account_db")
public class AccountCategoryTbl implements java.io.Serializable {

	private AccountCategoryTblId id;
	private String superCategory;
	private Date createTime;
	private Date updateTime;

	public AccountCategoryTbl() {
	}

	public AccountCategoryTbl(AccountCategoryTblId id, Date createTime,
			Date updateTime) {
		this.id = id;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public AccountCategoryTbl(AccountCategoryTblId id, String superCategory,
			Date createTime, Date updateTime) {
		this.id = id;
		this.superCategory = superCategory;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "accountBookId", column = @Column(name = "account_book_id", nullable = false)),
			@AttributeOverride(name = "category", column = @Column(name = "category", nullable = false, length = 45)) })
	public AccountCategoryTblId getId() {
		return this.id;
	}

	public void setId(AccountCategoryTblId id) {
		this.id = id;
	}

	@Column(name = "super_category", length = 45)
	public String getSuperCategory() {
		return this.superCategory;
	}

	public void setSuperCategory(String superCategory) {
		this.superCategory = superCategory;
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
