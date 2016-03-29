package com.ezqueue.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "queuing")
public class Queuing extends ModelBase implements Serializable{

	private static final long serialVersionUID = -8609517864321196790L;

	@Id
	@Column(name = "queuing_id")
	private String queuingId;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@OneToOne
	@JoinColumn(name = "queue_id")
	private Queue queue;
	@Column(name = "queue_num")
	private Integer queueNum;
	@Column(name = "start_date", insertable = false)
	private Date startDate;
	@Column(name = "end_date", insertable = false)
	private Date endDate;
	@Column(name = "waitting_time", insertable = false)
	private Integer waittingTime;
	@Column(name = "status")
	private Integer status;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}
	public String getQueuingId() {
		return queuingId;
	}
	public void setQueuingId(String queuingId) {
		this.queuingId = queuingId;
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
	public Integer getQueueNum() {
		return queueNum;
	}
	public void setQueueNum(Integer queueNum) {
		this.queueNum = queueNum;
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
	public Integer getWaittingTime() {
		return waittingTime;
	}
	public void setWaittingTime(Integer waittingTime) {
		this.waittingTime = waittingTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
