# Spring proyecto 1

La idea de este proyecto es ver como entender como funciona la inversión de control de spring.

## Para correr este proyecto

Hay que agregar spring de forma manual. Se puede hacer descargando spring del siguiente [enlace](https://repo.spring.io/ui/native/libs-release/org/springframework/spring/5.3.9/spring-5.3.9-dist.zip) y copiando el contenido de `spring-5.3.9-dist/spring-framework-5.3.9/libs` en una carpeta dentro de nuestro proyecto. Todos estos archivos copiados deben ser agregados al classpath.

La idea es poder usar en nuestro main una interfaz que nos permite cambiar dinámicamente el tipo de objetos que usamos, en el caso de nuestro ejemplo la interfaz seria un Coach que engloba distintos tipos de chaches de dferentes deportes. 

Para poder hacer estaap configurable debemos agregar un archivo de configuración y hacer uso del patrón factory.

Esta factory es proporcionada por el framework spring. 
Decide que tipo de objetos va a usar basado en el archivo de configuración.

La funcuiones principales del Spring Container son:

* Crear y  manejhar objetos (Inversión  de dependencias)
* Inyectar dependencias en objetos (Inyección de dependencias)

Las formas de configurar un container de spring son con:

* Archivo de configuración XML (es legacy pero muchas aplicacions legacy todavia lo usan)
* Java Annotations
* Codigo fuente Java

Los pasos para desarrollar en spring son

1. Configurar los Spring Beans
2. Crear un Spring Container
3. Recuperar Beans del Container de Spring

## ¿Que es un Java Bean?

Es simplemente un objeto de java.
Cuando los objetos de java son creados por el Spring container entonces spring se refiere a ellos como spring beans.

 `"A bean is an object that is instantiated, assembled, and managed by a Spring IoC container. Otherwise, a bean is simply one of many objects in your application. Beans, and the dependencies among them, are reflected in the configuration metadata used by a container."`


## Archivo de configuración

Detro de este archivo `src/applicationContext.xml` especificamos los beans con un id y la ruta directa a la clase

``` 
<bean id="myCoach"
    class="com.luv2code.springdemo.TrackCoach">
</bean>
```

La ruta debe estar completa incluyendo los nombres del paquete que contiene a mi clase.

# Injeccion de dependencias

El cliente delega la reponsabilidad de proveer dependencias llamando a otro objeto.
Los beans que creamos y configuramos via el archivo de configuración puede necesitar dependencias u objetos auxiliares. La idea es que en vez de instanciar el bean y los objetos de los cuales depende lo hacemos automaticamente usando el framework de spring.

Estas dependencias las llamamos helpers.

### Tipos de injección de de pendencia

* Constructor injection
* Setter injection
* Auto-wiring (con annotations) 

### Como crear una injeccion

1. Definir una interfaz para la dependencia
2. Crear un constructor en la clase para aceptar injecciones
3. Configurar la injección de dependencias en el archivo de configuraciones de spring

En el archivo nos debe quedar algo parecido a 

```
<bean id="myFortuneService"
	class="com.luv2code.springdemo.HappyFortuneService">
</bean> 

<bean id="myCoach"
    class="com.luv2code.springdemo.TrackCoach">
    <constructor-arg ref="myFortuneService"/>
</bean>
```

donde configuramos nuestro bean correspondiente al helper y se lo pasamos al constructor del bean Coach.

El archivo de configuracion a la hora de recuperar el bean se corresponde con el siguiente codigo

```
HappyFortuneService myFortuneService = new HappyFortuneService();
TackCoach myCoach = new TrackCoach(myFortuneService);
````