package com.aloha.common.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Helper {

	public static String getLocalDate(Date date) {
		Date lv_localDate = null;

		TimeZone tz = TimeZone.getDefault();
		/*SimpleDateFormat lv_parser = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		lv_parser.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat lv_formatter = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss z'('Z')'");
		return lv_formatter.format(lv_localDate);
*/	
		SimpleDateFormat sdfAmerica = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
		sdfAmerica.setTimeZone(TimeZone.getDefault());
		String sDateInAmerica = sdfAmerica.format(date);
		return sDateInAmerica;
	}
}