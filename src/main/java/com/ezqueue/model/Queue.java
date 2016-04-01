package com.ezqueue.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@Column(name = "dscr")
	private String dscr;
	@Column(name = "enable")
	private boolean enable;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "queue_id")
	private List<Favorite> favorites;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "queue_id")
	private List<Promotion> promotions;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "queue_id")
	private List<Queuing> queuings;
	
	@OneToMany(cascade = CascadeType.REMOVE)
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

	public String getDscr() {
		return dscr;
	}

	public void setDscr(String dscr) {
		this.dscr = dscr;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
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

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Queuing> getQueuings() {
		return queuings;
	}

	public void setQueuings(List<Queuing> queuings) {
		this.queuings = queuings;
	}

}
