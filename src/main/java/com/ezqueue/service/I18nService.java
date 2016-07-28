package com.ezqueue.service;

import java.util.Locale;

import com.ezqueue.model.I18n;

public interface I18nService {
	
	public String getDefaultEn(String value);
	
	public I18n getI18n(String value, Locale locale);
	
}
