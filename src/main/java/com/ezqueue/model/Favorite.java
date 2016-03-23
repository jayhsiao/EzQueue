package com.ezqueue.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "favorite")
public class Favorite implements Serializable{

	private static final long serialVersionUID = -8609517864321196790L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "favorite_id")
	private Integer favoriteId;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@OneToOne
	@JoinColumn(name = "queue_id")
	private Queue queue;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}
	public Integer getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(Integer favoriteId) {
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
