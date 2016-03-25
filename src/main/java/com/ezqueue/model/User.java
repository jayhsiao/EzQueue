package com.ezqueue.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = -8609517864321196790L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
