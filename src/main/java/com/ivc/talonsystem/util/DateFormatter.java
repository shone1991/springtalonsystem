package com.ivc.talonsystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	
	public static SimpleDateFormat sdf;
	public static String format(Date d) {
		sdf=new SimpleDateFormat("dd.MM.yyyy");
		try {
			return sdf.format(d);
		} catch (Exception e) {
			return "";
		}
		
	}

}
