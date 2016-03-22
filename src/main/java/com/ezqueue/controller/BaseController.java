package com.ezqueue.controller;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;

public class BaseController {
	
	public Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}
	
	public ResponseEntity<Object> getResponse(Object object) {
        return ResponseEntity.ok(object);
	}
}
