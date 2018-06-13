package com.vsign.tech.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class HelloWorld {

	public static void main(String[] args) throws ParseException {
		System.out.println("Welcome to REST API Module");
		
		Calendar c=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		
		
		DateTimeFormatter format1=	DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//
		System.out.println("date :"+format1);
		
		LocalDateTime  date= LocalDateTime .now();
		System.out.println("calender :"+c.getTime().getSeconds());
		System.out.println("Date before format  :"+date);
		System.out.println("Date after format :"+date.format(format1));
		
		Date format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date.format(format1));
		
		System.out.println("Date after parsing : "+format);
		
		
		
		
		
	}
}
