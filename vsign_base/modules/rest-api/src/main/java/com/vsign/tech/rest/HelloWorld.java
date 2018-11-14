package com.vsign.tech.rest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


import org.apache.http.client.utils.URIBuilder;

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
		
	/*	
		URIBuilder builder = new URIBuilder() ;
				builder.setScheme("https") ;
				builder.setHost("paynetzuat.atomtech.in") ;
				builder.setPath("/paynetz/epi/fts") ;
				builder.addParameter("login", "197") ;
				builder.addParameter("pass", "Test@123") ;
				builder.addParameter("trxid", "1234") ;
				builder.addParameter("amt", "230") ;
				builder.addParameter("prodid", "NSE") ;
				try {
					URL url = builder.build().toURL() ;
					System.out.println("target url : "+url);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		
				 try {
					URI uri = new URIBuilder()
					            .setScheme("http")
					            .setHost("www.google.com")
					            .setPath("/search")
					            .setParameter("q", "httpclient")
					            .setParameter("btnG", "Google Search")
					            .setParameter("aq", "f")
					            .setParameter("oq", "")
					            .build();
					try {
						URL url =uri.toURL();
						System.out.println("URL : "+url);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
