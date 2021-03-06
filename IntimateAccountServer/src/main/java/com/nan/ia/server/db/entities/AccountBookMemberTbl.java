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
 * AccountBookMemberTbl generated by hbm2java
 */
@Entity
@Table(name = "account_book_member_tbl", catalog = "intimate_account_db")
public class AccountBookMemberTbl implements java.io.Serializable {

	private AccountBookMemberTblId id;
	private Date createTime;

	public AccountBookMemberTbl() {
	}

	public AccountBookMemberTbl(AccountBookMemberTblId id, Date createTime) {
		this.id = id;
		this.createTime = createTime;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "accountBookId", column = @Column(name = "account_book_id", nullable = false)),
			@AttributeOverride(name = "memberUserId", column = @Column(name = "member_user_id", nullable = false)) })
	public AccountBookMemberTblId getId() {
		return this.id;
	}

	public void setId(AccountBookMemberTblId id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
