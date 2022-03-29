# Spring proyecto 1

La idea de este proyecto es ver como entender como funciona la inversión de control de spring.

## Para correr este proyecto

Hay que agregar spring de forma manual. Se puede hacer descargando spring del siguiente [enlace](https://repo.spring.io/ui/native/libs-release/org/springframework/spring/5.3.9/spring-5.3.9-dist.zip) y copiando el contenido de `spring-5.3.9-dist/spring-framework-5.3.9/libs` en una carpeta dentro de nuestro proyecto. Todos estos archivos copiados deben ser agregados al classpath.

La idea es poder usar en nuestro main una interfaz que nos permite cambiar dinámicamente el tipo de objetos que usamos, en el caso de nuestro ejemplo la interfaz seria un Coach que engloba distintos tipos de chaches de dferentes deportes.

Para poder hacer estaap configurable debemos agregar un archivo de configuración y hacer uso del patrón factory.

Esta factory es proporcionada por el framework spring.
Decide que tipo de objetos va a usar basado en el archivo de configuración.

La funcuiones principales del Spring Container son:

- Crear y manejhar objetos (Inversión de dependencias)
- Inyectar dependencias en objetos (Inyección de dependencias)

Las formas de configurar un container de spring son con:

- Archivo de configuración XML (es legacy pero muchas aplicacions legacy todavia lo usan)
- Java Annotations
- Codigo fuente Java

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

# Inyección de dependencias

El cliente delega la reponsabilidad de proveer dependencias llamando a otro objeto.
Los beans que creamos y configuramos via el archivo de configuración puede necesitar dependencias u objetos auxiliares. La idea es que en vez de instanciar el bean y los objetos de los cuales depende lo hacemos automaticamente usando el framework de spring.

Estas dependencias las llamamos helpers.

### Tipos de injección de de pendencia

- Constructor injection
- Setter injection
- Auto-wiring (con annotations)

## Constructor Injection

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
```

## Setter Injection

Injecta dependencias llamando a metodos setter dentro de la clase. Hay que hacerlo mediante los siguientes pasos:

1. Crear los metodos setter en la clase para permitir injecciones
2. Configurar la inyección de deppendencias en el archivo de configuraciones de Spring.

Básicamente en vez de instanciar las dependencias con el contructor, lo que hacermos es asignarle el objeto de dependencias nuestro bean luego de que fue creado usando un setter.

```
<bean id="myCoach"
    class="com.luv2code.springdemo.TrackCoach">
    <property name="fortuneService" ref="myFortuneService"/>
</bean>
```

En el archivo de configuraciondes, dentro de un tag `property` especificamos que setter usar con el campo `name` le asignamos el helper previamente definido en el archivo con el canpo `ref`.

Spring lo que hace es tomar el `name` capitaliza la primer letra y agrega el set, ese es el método que va a usar.

El resultado final es equivalente a

```
HappyFortuneService myFortuneService = new HappyFortuneService();
TackCoach myCoach = new TrackCoach();
myCoach.setFortuneService(myFortuneService);
```

Esto tambien puede hacerse para en vez de inyectar dependencias en forma de objetos, podamos inyectar literales, tales como un campo nombre, email, telefono.

Estas propiedades tambien pueden ser cargadas desde un archivo de propiedades, cargando ese archivo en el spring config File y referenciando los valores del archivo de propiedades.

# Scope de Beans

El scope se refiere al ciclo de vida del bean, es decir cuanto tiempo vive un bean, cuantas instancias son creades, quien tiene acceso a el y como es compartido.

Por defecto es scope de los Beans es Singleton

```
<bean id="myCoach"
    class="com.luv2code.springdemo.TrackCoach">
    ...
</bean>
```

## Bean como singleton

El container de spring crea solo una instancia del bean por defecto, que es chacheado en memeoria. Todas las request a ese bean obtienen la misma referencia compartida, es decir es siempre el mismo objeto no hay copias.

El scope de un bean se peude especificar de la siguiente formas

```
<bean id="myCoach"
    class="com.luv2code.springdemo.TrackCoach"
    scope="<tipo de scope>">
</bean>
```

Los tipos de scope son:

- singleton: Crea una instancia compartida del bean. Es el scope por defecto de
- prototype: Crea una nueva instancia por cada pedido al contenedor de spring.
- request: esta atado a una peticion HTTP. Sólo se usa para web apps.
- session: esta atado a una sesion web HTTP. Sólo se usa para web apps.
- global-session: esta atado a una sesion web global HTTP. Sólo se usa para web apps.

# Ciclo de vida de bean

El ciclo de vida del bean esta bien diferenciado en etapas:

- El bean es instanciado: es cuando se crea el bean
- Inyeccion de dependencias
- Procesamiento interno de spring
- **Custom Init method**: podemos configurar un metodo init que se ejecuta en esta parte
- Vida util: El bean esta listo para uso
- **Custom Destroy method**: tambien se puede configurar un metodo que se ejecuta al eliminar el bean.

Estos Hooks cuando se crean o destruyen beans pueden ser usados para llamar a metodos de lógica de negocio o incluso para inicializar o limpiar/cerrar recursos (conexiones a bases de datos, sockets, archivos, etc)

```
<bean id="myCoach"
    class="com.luv2code.springdemo.TrackCoach"
    init-method="doMyStartupStuff"
    destroy-method="doMyCleanupStuff">
</bean>
```

estos metodos que usamos como hooks pueden tener cualquier modificador de acceso (public, protected, private) y cualquier tipo de return, pero lo mas comun es usar viod ya que el valor que devuelva no puede ser recuperado. Estos metodos no puede aceptar argumentos.

### Aclaracion

Si usamos el scope prototype Spring no sigue el ciclo de vida del bean de forma completa. El container inicializa, configura, ensabla el objeto y lo habilita para que lo use el cliente, pero spring no mantiene registro de las instancias prototype de ahi en adelante, por lo que el metodo de destrucción nunca sera llamada. Si queremos que se ejecute un metodo de destrucción con scope prototype hay que programarlo
