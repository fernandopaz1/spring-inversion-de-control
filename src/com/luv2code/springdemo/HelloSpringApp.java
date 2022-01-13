package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {

		// carga el archivo de configuraciones de spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// obtiene el bean del container de spring
		// Para poder instanciar un bean a partir del archivo de configuraciones
		// debemos pasarle el id del bean y la interfaz que esta implementando.
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		// llama metodos sobre el bean
		System.out.println(theCoach.getDailyWorkout());
		
		// cierra el aplication context
		context.close();
	}

}
