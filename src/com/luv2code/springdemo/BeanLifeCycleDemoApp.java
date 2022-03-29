package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp {

  public static void main(String[] args) {
    // obtenemos el archivo de configuraciones
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");

    Coach myCoach = context.getBean("myCoach", Coach.class);

    System.out.println("Este es el coach: " + myCoach);

    // cerramos el context
    context.close();
  }
}
