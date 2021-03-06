package com.nan.ia.server.db.entities;

// Generated 2015-6-7 4:02:23 by Hibernate Tools 3.4.0.CR1

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
 * AccountCategoryDeleteTbl generated by hbm2java
 */
@Entity
@Table(name = "account_category_delete_tbl", catalog = "intimate_account_db")
public class AccountCategoryDeleteTbl implements java.io.Serializable {

	private AccountCategoryDeleteTblId id;
	private int deleteUserId;
	private Date deleteTime;

	public AccountCategoryDeleteTbl() {
	}

	public AccountCategoryDeleteTbl(AccountCategoryDeleteTblId id,
			int deleteUserId, Date deleteTime) {
		this.id = id;
		this.deleteUserId = deleteUserId;
		this.deleteTime = deleteTime;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "accountBookId", column = @Column(name = "account_book_id", nullable = false)),
			@AttributeOverride(name = "category", column = @Column(name = "category", nullable = false, length = 45)) })
	public AccountCategoryDeleteTblId getId() {
		return this.id;
	}

	public void setId(AccountCategoryDeleteTblId id) {
		this.id = id;
	}

	@Column(name = "delete_user_id", nullable = false)
	public int getDeleteUserId() {
		return this.deleteUserId;
	}

	public void setDeleteUserId(int deleteUserId) {
		this.deleteUserId = deleteUserId;
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
