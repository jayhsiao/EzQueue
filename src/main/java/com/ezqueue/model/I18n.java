package com.ezqueue.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "i18n")
public class I18n implements Serializable{

	private static final long serialVersionUID = 4159228849488565837L;
	
	@Id
	@Column(name = "i18n_id")
	private Integer i18nId;
	@Column(name = "default_en")
	private String defaultEn;
	@Column(name = "value")
	private String value;
	@Column(name = "locale")
	private String locale;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}

	public Integer getI18nId() {
		return i18nId;
	}

	public void setI18nId(Integer i18nId) {
		this.i18nId = i18nId;
	}

	public String getDefaultEn() {
		return defaultEn;
	}

	public void setDefaultEn(String defaultEn) {
		this.defaultEn = defaultEn;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
