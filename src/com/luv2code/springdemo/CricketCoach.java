package com.luv2code.springdemo;

public class CricketCoach implements Coach {

	private FortuneService fortuneService;
	private String name;
	private String email;
	private String team;
	
	public CricketCoach() {
		System.out.println("CricketCoach: inside no args constructor");
	}
	
	public void setFortuneService(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Practice fast bowling for 15 minutes";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return this.name +" "+this.email+" "+ this.team;
	}
}
