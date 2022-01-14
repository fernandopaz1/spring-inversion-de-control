package com.luv2code.springdemo;

public class TrackCoach implements Coach {
	
	//definimos un campo privado para la dependencia
	private FortuneService fortuneService;
	
	// definimos un constructor para la inyeccion de dependencias
	public TrackCoach (FortuneService theFortuneService) {
		fortuneService=theFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
