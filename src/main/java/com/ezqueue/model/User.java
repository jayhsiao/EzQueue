package com.ezqueue.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "user")
public class User extends ModelBase implements Serializable{

	private static final long serialVersionUID = 6252677806180763045L;
	
	@Id
	@Column(name = "user_id")
	private String userId;
	@Column(name = "facebook_id")
	private String facebookId;
	@Column(name = "email")
	private String email;
	@Column(name = "name")
	private String name;
	
	@OneToMany
	@JoinColumn(name = "user_id")
	private List<UserAccountMap> userAccountMaps;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserAccountMap> getUserAccountMaps() {
		return userAccountMaps;
	}

	public void setUserAccountMaps(List<UserAccountMap> userAccountMaps) {
		this.userAccountMaps = userAccountMaps;
	}

}
