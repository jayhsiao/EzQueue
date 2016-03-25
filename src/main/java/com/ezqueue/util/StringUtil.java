package com.ezqueue.util;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StringUtil {
	
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static String reflectionToString(Object object){
		 return ToStringBuilder.reflectionToString(object, ToStringStyle.JSON_STYLE);
	}
}
