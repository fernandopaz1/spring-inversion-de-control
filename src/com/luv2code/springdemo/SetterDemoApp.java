package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemoApp {

	public static void main(String[] args) {
		
		// carga el archivo de configuraciones de spring
				ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				
				Coach theCoach = context.getBean("myCricketCoach", Coach.class);
				
				// llama metodos sobre el bean
				System.out.println(theCoach.getDailyWorkout());
				
				System.out.println(theCoach.getDailyFortune());
				
				// cierra el aplication context
				context.close();
	}

}
