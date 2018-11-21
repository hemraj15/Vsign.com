package com.vsign.tech.rest;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.vsign.tech.rest.utils.SignatureConst;
import com.vsign.tech.rest.utils.SignatureGenerate;

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
		
		
		Date sample =new Date();
		
		System.out.println("HelloWorld.main() " + new SimpleDateFormat("dd/mm/yyyy HH:mm:ss").format(sample) );
		
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
		
				 /*try {
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
				}*/
		
		int i=0;
		
		System.out.println("hjdgvchd : "+String.valueOf(i));
		
		System.out.println(" ================ ");
		
		//String login = "197";
		//String pass = "Test@123";
		//String ttype = "NBFundTransfer";
		//String prodid = "NSE";
		//String txnid = "30";
		//String amt = "0";
		//String txncurr = "INR";
		//String reqHashKey = "KEY123657234";
		String login = SignatureConst.login_id;
		String pass = SignatureConst.Password;
		
		String ttype = SignatureConst.ttype;
		String prodid = SignatureConst.prod_id;
		Long txnid = 50l;
		Double amt = 0.0;
		DecimalFormat df = new DecimalFormat("####0.00");
	     String amount= df.format(amt);
		String txncurr = SignatureConst.txncurr;
		String reqHashKey = SignatureConst.ReqHashKey;
		// login,pass,ttype,prodid,txnid,amt,txncurr
		//getEncodedValueWithSha2(reqHashKey, login,pass,ttype,prodid,txnid,amt,txncurr);
		System.out.println(" login = "+login+" pass = "+pass+" ttype = "+ttype+" prodId = "+prodid+" txnid = "+txnid+" amt = "+amount+" txncurr = "+txncurr+" reqHashKey = "+reqHashKey );
		String signature_request = SignatureGenerate.getEncodedValueWithSha2(reqHashKey, login, pass, ttype, prodid, txnid.toString(), amount, txncurr);
		System.out.println("Request signature ::" + signature_request);
		
	}
}
