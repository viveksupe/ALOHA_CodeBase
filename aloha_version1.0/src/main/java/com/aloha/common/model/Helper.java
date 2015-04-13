package com.aloha.common.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Helper {

	public static String getLocalDate(String date) {
		Date lv_localDate = null;

		SimpleDateFormat lv_parser = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		lv_parser.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat lv_formatter = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss z'('Z')'");
		return lv_formatter.format(lv_localDate);
	}
}