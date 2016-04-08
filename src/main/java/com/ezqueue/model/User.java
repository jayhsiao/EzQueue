package com.ezqueue.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
	@Column(name = "id")
	private String id;
	@Column(name = "email")
	private String email;
	@Column(name = "name")
	private String name;
	@Column(name = "parent")
	private String parent;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id")
	private List<Queue> queues;	
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public List<Queue> getQueues() {
		return queues;
	}

	public void setQueues(List<Queue> queues) {
		this.queues = queues;
	}

}
