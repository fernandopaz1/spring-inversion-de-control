package com.luv2code.springdemo;

public class ChessCoach implements Coach {

	// definimos un campo privado para la dependencia
	private FortuneService fortuneService;

	// definimos un constructor para la inyeccion de dependencias
	public ChessCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Tactics, tactics, tactics";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	// agregando un init-method
	public void doMyStartupStuff() {
		System.out.println("ChessCoach: inside method doMyStartupStuff");
	}

	// agregando un detroy-method
	public void doMyCleanupStuff() {
		System.out.println("ChessCoach: inside method doMyCleanupStuff");
	}
}
