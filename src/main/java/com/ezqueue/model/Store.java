package com.ezqueue.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "store")
public class Store extends ModelBase implements Serializable{

	private static final long serialVersionUID = 4159228849488565837L;
	
	@Id
	@Column(name = "store_id")
	private String storeId;
	@Column(name = "name")
	private String name;
	@Column(name = "phone")
	private String phone;
	@Column(name = "longitude")
	private String longitude;
	@Column(name = "latitude")
	private String latitude;
	@Column(name = "country")
	private String country;
	@Column(name = "city")
	private String city;
	@Column(name = "district")
	private String district;
	@Column(name = "address")
	private String address;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
