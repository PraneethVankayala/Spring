# Spring Dependency Injection & IoC #

**Inversion of Control**: It is the design process of externalizing, the construction and management of your objects. It means we need to outsource the creation and management of objects and that outsourcing is handled by object factory.

#### Spring Container: ####
- Create and manage Objects(IoC)
- Inject object's dependencies(Dependency Injection)

#### Configuring Spring Container: ####
- XML configuration file
- Java annotation
- Java source code

#### Spring Dev Process: ####
- Configure your Spring Beans
	application.xml
	`<beans>
		<bean id="myCoach" class = "spring.demo.BaseBallCoach">
		</bean>
	</beans>`
- Create a Spring Container
	- Spring container is generally known as ApplicationContext.
	- Specialized Implementations:
		- ClassPathXmlApplicationContext
		- AnnotationConfigApplicationContext
		- GenericWebApplicationContext.
	`ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");`

- Retrive Beans from Spring container
	`Coach coach = context.getBean("myCoach", Coach.class);`
	
**Spring Bean:** It is the java objects that are created by spring container.

**Spring Dependency Injection:** Client deligates to call the another object(Factory) responsible of providing its dependencies. In other words: outsource the construction and injection of your object to an external entity(Factory).

#### Injection Types: ####
- There are many types of injection with Spring
- The most common injections:
	- Constructor Injection
		`<constructor-arg ref="reference"/>`
	- Setter Injection
		- Injecting reference
			`<property name="function_name" ref="reference"/>`
		- Injecting value
			`<property name="function_name" value=val/>`
	- Auto Wiring

#### Property file: ####
- create a property file
	`filename:sports.properties`
- Load properties file in spring config file
	`<context:property-placeholder location="classpath:sports.properties"/>`
- Reference values from properties file
	`<property name="fun_name" value="${email}"/> `
