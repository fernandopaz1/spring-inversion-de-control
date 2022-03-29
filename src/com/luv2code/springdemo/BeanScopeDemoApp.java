package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {
  public static void main(String[] args) {
    // carcamos el archivo de configuracion
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");

    // obtenemos el bean del spring container
    Coach alphaCoach = context.getBean("myCoach", Coach.class);

    // obtenemos nuevamente el bean del contenedor de spring
    Coach betaCoach = context.getBean("myCoach", Coach.class);

    // comparamos si la referencia en memoria de ambos coach es la misma
    boolean result = (alphaCoach == betaCoach);

    System.out.println("\nLos coachs son iguales?: " + result);

    System.out.println("\nLa referencia a alphaCoach es: " + alphaCoach);
    System.out.println("\nLa referencia a betaCoach es: " + betaCoach);

    context.close();
  }
}
