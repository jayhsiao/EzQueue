package com.ezqueue.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ezqueue.util.QueueStatus;
import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "queue")
public class Queue extends ModelBase implements Serializable{

	private static final long serialVersionUID = -8609517864321196790L;

	@Id
	@Column(name = "queue_id")
	private String queueId;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "title")
	private String title;
	@Column(name = "phone")
	private String phone;
	@Column(name = "address")
	private String address;
	@Column(name = "dscr")
	private String dscr;
	
	@OneToOne
	@JoinColumn(name = "queue_type_id")
	private QueueType queueType;
	
	@Column(name = "queue_num")
	private Integer queueNum;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private QueueStatus status;
	
	@Column(name = "start_date")
	private String startDate;
	
	@Column(name = "end_date")
	private String endDate;
	
	@OneToMany
	@JoinColumn(name = "queue_id")
	private List<Favorite> favorites;
	
	@OneToOne
	@JoinColumn(name = "queue_id")
	private Promotion promotion;
	
	@OneToMany
	@JoinColumn(name = "queue_id")
	private List<Queuing> queuings;
	
	@OneToMany
	@JoinColumn(name = "queue_id")
	private List<Star> stars;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDscr() {
		return dscr;
	}

	public void setDscr(String dscr) {
		this.dscr = dscr;
	}

	public QueueType getQueueType() {
		return queueType;
	}

	public void setQueueType(QueueType queueType) {
		this.queueType = queueType;
	}

	public Integer getQueueNum() {
		return queueNum;
	}

	public void setQueueNum(Integer queueNum) {
		this.queueNum = queueNum;
	}

	public QueueStatus getStatus() {
		return status;
	}

	public void setStatus(QueueStatus status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Double getAvgWaittingTime() {
		return avgWaittingTime;
	}

	public void setAvgWaittingTime(Double avgWaittingTime) {
		this.avgWaittingTime = avgWaittingTime;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public List<Star> getStars() {
		return stars;
	}

	public void setStars(List<Star> stars) {
		this.stars = stars;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotions(Promotion promotion) {
		this.promotion = promotion;
	}

	public List<Queuing> getQueuings() {
		return queuings;
	}

	public void setQueuings(List<Queuing> queuings) {
		this.queuings = queuings;
	}

}
