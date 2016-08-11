package com.ezqueue.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ezqueue.util.QueuingStatus;
import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "queuing")
public class Queuing extends ModelBase implements Serializable{

	private static final long serialVersionUID = -3845762232327273130L;
	
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private QueuingStatus status;
	
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
	public QueuingStatus getStatus() {
		return status;
	}
	public void setStatus(QueuingStatus status) {
		this.status = status;
	}
	
}
