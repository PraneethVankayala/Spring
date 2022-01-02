# Java Annotations #

- Special label/markers added to java classes
- Provide meta-data about the class
- Processed at compile time or runtime for special processing

**Why Spring configuration with Annotatitions?**
- XML configuration can be verbose
-  Configure your spring beans with Annotations
-  Annotations minimize the XML configuration

**Scanning for component classes**
- Spring will scan your java classes for special annotations.
- Automatically register the beans in the spring container

**Development Process**

- Enable component scanning in spring config file
	<pre> &ltbeans> 
        &ltcontext: component-scan base-package="Spring.BeanScopes.App"/> 
	&lt/beans></pre>
- Add the `@component` Annotation to your java classes
	<pre> @Component("theSillyCoach")
	public class TennisCoach implements Coach{
		public String getFortune(){
		}
	} </pre>
- Retrieve bean from spring container \
	`Coach theCoach = context.getBean("theSillyCoach", Coach.class);`

When we write `@Component` the default bean id will be classname with first letter as lower-case(Ex: Class Name: TennisCoach, Bean Id: tennisCoach)

**What is Spring Autowiring**
- For dependency injection spring will automatically wire up your objects together
- Spring will look into the class that matches the property
	- matches by type: class or interface
- Spring will inject it automatically .. hence it is called autowired

**Autowiring Example:**
- Inject FortuneService in coach implementation
- Spring will scan `@Components`
- Anyone out there implements FortuneService
- If so, let's inject them. For Example: Happy Fortune Service

**Autowiring Injection Types:**
- Constructor Injection
- Setter Injection
- Field Injection

**Dev Process for constructor-injection:**
- Define the dependency interface and class.
	<pre>public interface FortuneService
  { 
    public String getFortune();
  }
	@Component
  public class HappyFortuneService implements FortuneService
  {
    
  }</pre>
- Create a constructor in your class for Injection.
	<pre>@Component
	public class TennisCoach implements Coach{
		FortuneService fortuneService;
		public TennisCoach(fortuneService){
      this.fortuneService = fortuneService;
    }
      public String getFortune(){
      }
	}</pre>
- Configure the dependency injection with @Autowired Annotation
	<pre>@Autowired
	public TennisCoach(fortuneService){
    this.fortuneService = fortuneService;
  }</pre>

**Dev Process for setter-injection:**
- Create a setter method in your class for injections.
- Configure the dependency injection with `@Autowired` Annotation
  <pre>
	@Autowired
	public void setFortuneService(FortuneService fs)
  {
    fortuneService = fs;
  }
  </pre>

**Method Injection:**
- By applying autowired to any method that can be used for dependency injection

**Field Injection:**
- We can inject values directly to the any field without using any constructor or setter methods. Here spring uses java reflection concept to do this.
- Autowired annotation is directly placed on fields.

**Qualifiers:**
- This can be used when there are multiple implementations for the same interface and autowired is used. Now using this annotation we tell spring about the specific implementation that needs to be used for dependency injection.
- `@Qualifier("beanId")`
- this annotation can be used in
	- Constructor injection
	- Field injection
	- Setter injection,
- For constructor injection `@Qualifier` annotation is used inside arguments
	-Ex:
  <pre>
  @Autowired 
	public TennisCoach(@Qualifier("happyFortuneService") FortuneService fs){
		fortuneService=fs;
  }
  </pre>
- For field and setter injection we can use like this
  <pre>
  @Autowired
	@Qualifier("happyFortuneService")
	private FortuneService fs;
  </pre>

**Inject Properties file using java annotations**
- Create a properties file to hold properties. Ex Location: src/sports.properties
	foo.email=vbs@gmail.com
	foo.team=RCB
- Load the properties file in XML config file \
	`<context:property-placeholder location="classpath:sport.properties"/> `
- Inject the properties values into your Swim Coach: SwimCoach.java
  <pre>
  @Value("${foo.email}")
  private String email;
  
  @Value("${foo.team}")
  private String team;
  </pre>

## Bean Scopes with Annotations ##
- Scope refers to lifecycle of a bean
- How long does the bean live?
- How many instances are created?
- How is the bean shared?

**Specify bean scope explicitly**
<pre>
@Scope("singleton")
public class TennisCoach implements Coach{}
</pre>

**Bean Lifecycle Methods/ Hooks:**
- We can add custom code for bean Initialization and bean Destruction.

**Dev Process:**
- Define methods for init and destroy
- Add annotations `@PostConstruct` and `@PreDestory`

# Configuring Spring Container using Java Code: #

- Instead of configuring spring container using XML
- Configuring the spring container with Java code

**3 ways of configuring spring container**
- Full XML config
- XML component scan
- Java configuration class

**Dev Process:**
- Create a java class and annotate as `@Configuration`
  <pre>
	@Configuration
	public class SportConfig{}
  </pre>
- Add component scanning support: `@ComponentScan`
  <pre>
	@Configuration
	@ComponentScan("path of base package")
	public class SportConfig{}
  </pre>
- Read Spring Java configuration class. \
	`AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);`
- Retrieve bean from Spring Container. \
	`Coach theCoach = context.getBean("tennisCoach", Coach.class);`

 
**Define Beans using Java code:**

`public class SwimCoach implemets Coach{}` - No annotations

**Dev Process:**
- Define methods to expose bean
  <pre>
	@Configuration
	public class SportConfig{
		@Bean
		public Coach swimCoach(){
			SwimCoach mySC = new SwimCoach();
			return mySC;
		}
	}
  </pre>
- Inject Bean dependencies
  <pre>
	@Configuration
	public class SportConfig{
		@Bean
		public FortuneService happyFortuneService(){
			return new HappyFortuneService();
		}
		@Bean
		public Coach swimCoach(){
			SwimCoach mySC = new SwimCoach(happyFortuneService());
			return mySC;
		}
	}
  </pre>
- Read Spring Java configuration class \
	`AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);`
- Retrieve bean from Spring container \
	`Coach theCoach = context.getBean("swimCoach", Coach.class);`

**Read from Properties file:
Dev Process:**
- create a properties file
	foo.email=vbs@gmail.com	
	foo.team=RCB
- Load properties file in spring config
	`@PropertySource("classpath:sports.properties")`
- Reference values from properties file
  <pre>
	@Value("${foo.email}")
	private String email;
	@Value("${foo.team}")
	private String team;
  </pre>
