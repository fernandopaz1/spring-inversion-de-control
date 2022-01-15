package com.luv2code.springdemo;

import java.util.Random;

public class RandomFortuneService implements FortuneService {

	private String[] fortuneOptions;
	private static Random generator = new Random();
	
	public void setFortuneOptions(String[] fortuneOptions) {
		this.fortuneOptions = fortuneOptions;
	}



	@Override
	public String getFortune() {
		String[] options= fortuneOptions ;//.split(","); 
		int index = generator.nextInt(options.length);
		return options[index];
	}

}
