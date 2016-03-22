package com.ezqueue.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "user_queue_map")
public class UserQueueMap implements Serializable{

	private static final long serialVersionUID = -8609517864321196790L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_queue_id")
	private Integer userQueueId;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@OneToOne
	@JoinColumn(name = "queue_id")
	private Queue queue;
	@Column(name = "start_date", insertable = false)
	private Date startDate;
	@Column(name = "end_date", insertable = false)
	private Date endDate;
	@Column(name = "waitting_time", insertable = false)
	private Date waittingTime;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}
	public Integer getUserQueueId() {
		return userQueueId;
	}
	public void setUserQueueId(Integer userQueueId) {
		this.userQueueId = userQueueId;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getWaittingTime() {
		return waittingTime;
	}
	public void setWaittingTime(Date waittingTime) {
		this.waittingTime = waittingTime;
	}
}
