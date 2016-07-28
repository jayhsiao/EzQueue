package com.ezqueue.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "user")
public class User extends ModelBase implements Serializable{

	private static final long serialVersionUID = 6252677806180763045L;
	
	@Id
	@Column(name = "user_id")
	private String userId;
	@Column(name = "fb_id")
	private String fbId;
	@Column(name = "email")
	private String email;
	@Column(name = "name")
	private String name;
	@Column(name = "parent")
	private String parent;
	
	@OneToMany
	@JoinColumn(name = "user_id")
	private List<Queue> queues;
	
	@OneToMany
	@JoinColumn(name = "user_id")
	private List<Favorite> favorites;
	
	@OneToMany
	@JoinColumn(name = "user_id")
	private List<Queuing> queuings;
	
	@OneToMany
	@JoinColumn(name = "user_id")
	private List<Star> stars;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFbId() {
		return fbId;
	}

	public void setFbId(String fbId) {
		this.fbId = fbId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public List<Queue> getQueues() {
		return queues;
	}

	public void setQueues(List<Queue> queues) {
		this.queues = queues;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public List<Queuing> getQueuings() {
		return queuings;
	}

	public void setQueuings(List<Queuing> queuings) {
		this.queuings = queuings;
	}

	public List<Star> getStars() {
		return stars;
	}

	public void setStars(List<Star> stars) {
		this.stars = stars;
	}

}
