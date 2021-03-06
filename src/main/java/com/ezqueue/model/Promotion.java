package com.ezqueue.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.ezqueue.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "promotion")
@Where(clause="now() between start_date and end_date")
public class Promotion extends ModelBase implements Serializable{

	private static final long serialVersionUID = -5758156456033975093L;
	
	@Id
	@Column(name = "promotion_id")
	private String promotionId;
	@OneToOne
	@JoinColumn(name = "queue_id")
	@JsonIgnore
	private Queue queue;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
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
	
}
