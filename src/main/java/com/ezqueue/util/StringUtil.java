package com.ezqueue.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class StringUtil {
	
	public static final NumberFormat NF = new DecimalFormat("#,###,###,##0");
	
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static String reflectionToString(Object object){
		 return ToStringBuilder.reflectionToString(object, ToStringStyle.JSON_STYLE);
	}
	
	public static String getEncrypPassword(String input) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    return passwordEncoder.encode(input);
	}
	
	public static String formatNumber(int number) {
		return NF.format(number);
	}
	
}
