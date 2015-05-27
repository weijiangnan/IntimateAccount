package com.nan.ia.server.db.entities;

// Generated 2015-5-27 14:32:56 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserExtendTbl generated by hbm2java
 */
@Entity
@Table(name = "user_extend_tbl", catalog = "intimate_account_db")
public class UserExtendTbl implements java.io.Serializable {

	private int userId;
	private byte[] avatarImage;
	private Date updateTime;

	public UserExtendTbl() {
	}

	public UserExtendTbl(int userId, Date updateTime) {
		this.userId = userId;
		this.updateTime = updateTime;
	}

	public UserExtendTbl(int userId, byte[] avatarImage, Date updateTime) {
		this.userId = userId;
		this.avatarImage = avatarImage;
		this.updateTime = updateTime;
	}

	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "avatar_image")
	public byte[] getAvatarImage() {
		return this.avatarImage;
	}

	public void setAvatarImage(byte[] avatarImage) {
		this.avatarImage = avatarImage;
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
