package com.ezqueue.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ezqueue.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "favorite")
public class Favorite extends ModelBase implements Serializable{

	private static final long serialVersionUID = 5995000827130756685L;
	
	@Id
	@Column(name = "favorite_id")
	private String favoriteId;
	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	@OneToOne
	@JoinColumn(name = "queue_id")
	@JsonIgnore
	private Queue queue;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}
	public String getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Queue getQueue() {
		return queue;
	}
	public void setQueue(Queue queue) {
		this.queue = queue;
	}
	
}
