package com.ezqueue.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "queue")
public class Queue implements Serializable{

	private static final long serialVersionUID = -8609517864321196790L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "queue_id")
	private Integer queueId;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "dscr")
	private String dscr;
	@Column(name = "promotion_priority")
	private Integer promotionPriority;
	@Column(name = "create_date", insertable = false)
	private Date createDate;
	@Column(name = "enable")
	private boolean enable;
	
	@OneToMany
	@JoinColumn(name = "queue_id")
	private List<UserQueueMap> userQueueMaps;
	
	@Transient
	private double avgWaittingTime;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}
	public Integer getQueueId() {
		return queueId;
	}
	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public List<UserQueueMap> getUserQueueMaps() {
		return userQueueMaps;
	}
	public void setUserQueueMaps(List<UserQueueMap> userQueueMaps) {
		this.userQueueMaps = userQueueMaps;
	}
	public double getAvgWaittingTime() {
		return avgWaittingTime;
	}
	public void setAvgWaittingTime(double avgWaittingTime) {
		this.avgWaittingTime = avgWaittingTime;
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
}
