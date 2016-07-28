package com.ezqueue.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "star")
public class Star extends ModelBase implements Serializable{

	private static final long serialVersionUID = 6452911642499660819L;
	
	@Id
	@Column(name = "star_id")
	private String starId;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@OneToOne
	@JoinColumn(name = "queue_id")
	private Queue queue;
	@Column(name = "star_num")
	private Integer starNum;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}

	public String getStarId() {
		return starId;
	}

	public void setStarId(String starId) {
		this.starId = starId;
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

	public Integer getStarNum() {
		return starNum;
	}

	public void setStarNum(Integer starNum) {
		this.starNum = starNum;
	}

}
