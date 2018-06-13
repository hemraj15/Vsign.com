package com.vsign.tech.data;

import java.math.BigDecimal;

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("Welcome to Data Module");
		
		
		Double d=21.26;
		Double d1=20.0;
		
		Double result=((d/6)*100)/100D;
		
		
		
		Double result1=Math.ceil(d/6);
		
		Double result2=Math.nextUp(result);
		
		
		Double result3 =Math.floor(result);
		
		System.out.println("Result is : "+result);
		System.out.println("Result 1 is : "+result1);
		
		System.out.println("Result 2 is : "+result2);
		
		System.out.println("Result 3 is : "+result3);
		
		System.out.println("round off : "+Math.round(result*100)/100D);
	}
}
