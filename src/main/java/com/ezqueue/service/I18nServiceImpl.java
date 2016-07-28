package com.ezqueue.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ezqueue.model.I18n;
import com.ezqueue.repository.I18nRepository;

@Service
public class I18nServiceImpl implements I18nService {
	
	@Autowired
	private I18nRepository i18nRepository;
	
	@Override
	@Cacheable("i18n")
	public String getDefaultEn(String value) {
		return i18nRepository.getByValue(value);
	}
	
	@Override
	@Cacheable("i18n")
	public I18n getI18n(String defaultEn, Locale locale) {
		return i18nRepository.getI18nByValue(defaultEn, locale.toString());
	}
	
}
