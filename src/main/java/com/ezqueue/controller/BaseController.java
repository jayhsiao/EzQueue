package com.ezqueue.controller;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;

public class BaseController {
	
	public Locale getLocale(String localeString) {
		Locale locale;
		if(localeString == null){
			locale = LocaleContextHolder.getLocale();
		}
		else{
			locale = new Locale(localeString);
		}
		return locale;
	}
	
	public ResponseEntity<Object> getResponse() {
        return this.getResponse(null);
    }
	
	public ResponseEntity<Object> getResponse(Object object) {
        return ResponseEntity.ok(object);
	}
}
