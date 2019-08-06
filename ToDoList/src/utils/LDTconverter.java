package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface LDTconverter {
	public static final String DATE_TIME_FORMAT = "dd.MM.yyyy. HH:mm";
	
	public static String LocalDateTimeToString(LocalDateTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
		String timeString = time.format(formatter);
		
		return timeString;
	}
	
	public static LocalDateTime StringToLocalDateTime(String timeString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
		LocalDateTime dateTime = LocalDateTime.parse(timeString, formatter);
		return dateTime;
	}
}
