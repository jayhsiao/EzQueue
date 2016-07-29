package com.ezqueue.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "user_account_map")
public class UserAccountMap implements Serializable {

	private static final long serialVersionUID = 6252677806180763045L;
	
	@Id
	@Column(name = "user_account_map_id")
	private String userAccountMapId;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "user_account_id")
	private String userAccountId;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}

	public String getUserAccountMapId() {
		return userAccountMapId;
	}

	public void setUserAccountMapId(String userAccountMapId) {
		this.userAccountMapId = userAccountMapId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}

}
