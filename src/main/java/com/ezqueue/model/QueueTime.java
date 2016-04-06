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
@Table(name = "queue_time")
public class QueueTime extends ModelBase implements Serializable{

	private static final long serialVersionUID = 513547821108496859L;
	
	@Id
	@Column(name = "queue_time_id")
	private String queueTimeId;
	@OneToOne
	@JoinColumn(name = "queue_id")
	private Queue queue;
	@Column(name = "week")
	private String week;
	@Column(name = "start_time")
	private String startTime;
	@Column(name = "end_time")
	private String endTime;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}

	public String getQueueTimeId() {
		return queueTimeId;
	}

	public void setQueueTimeId(String queueTimeId) {
		this.queueTimeId = queueTimeId;
	}

	public Queue getQueue() {
		return queue;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
