package com.ezqueue.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StringUtil {
	
	public static String reflectionToString(Object object){
		 return ToStringBuilder.reflectionToString(object, ToStringStyle.JSON_STYLE);
	}
}
