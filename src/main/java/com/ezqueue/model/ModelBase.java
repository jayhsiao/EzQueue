package com.ezqueue.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class ModelBase implements Serializable {
	
	private static final long serialVersionUID = -7751304918692194237L;
	
	@JsonIgnore
	@Column(name = "create_date")
    private Date createDate;
	@JsonIgnore
	@Column(name = "update_date")
	private Date updateDate;
	@JsonIgnore
	@Column(name = "create_user")
	private String createUser;
	@JsonIgnore
	@Column(name = "update_user")
	private String updateUser;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}
