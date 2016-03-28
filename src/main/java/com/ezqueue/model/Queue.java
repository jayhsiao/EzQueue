package com.ezqueue.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "queue")
public class Queue implements Serializable{

	private static final long serialVersionUID = -8609517864321196790L;

	@Id
	@Column(name = "queue_id")
	private String queueId;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "dscr")
	private String dscr;
	@Column(name = "promotion_priority")
	private Integer promotionPriority;
	@Column(name = "create_date", insertable = false)
	private Date createDate;
	@Column(name = "enable")
	private boolean enable;
	
	@Transient
	private String queuingId;
	
	@Transient
	private String favoriteId;
	
	@Transient
	private Double avgWaittingTime;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}

	public String getQueueId() {
		return queueId;
	}

	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDscr() {
		return dscr;
	}

	public void setDscr(String dscr) {
		this.dscr = dscr;
	}

	public Integer getPromotionPriority() {
		return promotionPriority;
	}

	public void setPromotionPriority(Integer promotionPriority) {
		this.promotionPriority = promotionPriority;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getQueuingId() {
		return queuingId;
	}

	public void setQueuingId(String queuingId) {
		this.queuingId = queuingId;
	}

	public String getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}

	public Double getAvgWaittingTime() {
		return avgWaittingTime;
	}

	public void setAvgWaittingTime(Double avgWaittingTime) {
		this.avgWaittingTime = avgWaittingTime;
	}

}
