package dev.ipg.util;


import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class Utility {
	
	public static final int getDaysInMonth(LocalDateTime localDateTime) {
		//For simplicity, we will have the following day convention: each month has 30 days
		return 30;
	}
	
	public static final int getDaysInYear(LocalDateTime localDateTime) {
		//For simplicity, we will have the following day convention: each year has 360 days.
		return 360;
	}
}
