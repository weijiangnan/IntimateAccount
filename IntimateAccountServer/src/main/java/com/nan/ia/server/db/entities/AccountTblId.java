package com.nan.ia.server.db.entities;

// Generated 2015-6-7 4:02:23 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AccountTblId generated by hbm2java
 */
@Embeddable
public class AccountTblId implements java.io.Serializable {

	private int accountType;
	private String username;

	public AccountTblId() {
	}

	public AccountTblId(int accountType, String username) {
		this.accountType = accountType;
		this.username = username;
	}

	@Column(name = "account_type", nullable = false)
	public int getAccountType() {
		return this.accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	@Column(name = "username", nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AccountTblId))
			return false;
		AccountTblId castOther = (AccountTblId) other;

		return (this.getAccountType() == castOther.getAccountType())
				&& ((this.getUsername() == castOther.getUsername()) || (this
						.getUsername() != null
						&& castOther.getUsername() != null && this
						.getUsername().equals(castOther.getUsername())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAccountType();
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		return result;
	}

}
