# Spring MVC
- Framework for building web applications in java
- Based on Model-View-Controller desgin pattern
- Leverages features of the core spring framework.(IoC, DI)

**Spring MVC benifits**
- The spring way of building web app UIs in java
- Leverage a set of reusable UI components..
- Help manage application state for web requests.
- Process form data: validation, conversion etc.
- Flexible configuration for the view layer.

**Components of Spring MVC application**
- A set of web pages to layout UI components.
- A collection of spring beans (controller, services..)
- Spring configuration (XML, Annotations or Java)

**Spring MVC Front controller**
- Front controller known as DispatcherServlet
	- Part of the spring framework
	- Alerady developed by the spring dev team
- We will create
	- Model objects (Where data is contained)
	- View templates (JSP or view page where we will render data)
	- Controller classes (Where we have our business logic to process)

**Controller**

- Code created by developer
- Contains your business logic
	- Handle the request
	- Store/ retrieve data(db, web service)
	- Place data in model
- Send to appropriate view template

**Model**
- Model: contains your data.
- Store/retrieve data via backend systems
	- database, web service, etc.,
	- use a spring bean if you like
- Place your data in model
	- Data can be a java object/ collection

**View Template**
- Spring MVC is flexible
	- Supprots  many view templates.
- Most common is JSP + JSTL
- Developer creates a page
	- Displays data
- Other templates supported
	- Thymeleaf, groovy
	- velocity, freemaker, etc..

**Spring MVC config process**
- Add configurations to file:WEB-INF/web.xml
	- configure spring MVC dispacher servlet
		``` xml     
      <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
      </servlet>
      <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/spring-mvc-demo-servlet.xml</param-value>
      </init-param>
      ```
	- set up URL mappings to Spring MVC Dispatcher servlet
     ``` xml
        <servlet-mapping>
          <servlet-name>dispatcher</servlet-name>
          <url-pattern>/</url-pattern>
        <servlet-mapping>
     ```
	- Note: servlet-name in servlet-mapping should match sevlet-name in sevlet.
- Add configuration to file: WEB-INF/spring-mvc-demo-servlet.xml
	- Add support to Spring component scanning 
		`<context:component-scan base-package="com.spring.mvc"/>`
	- Add support for conversion, formatting and validation
		`<mvc:annotation-driven/>`
	- Configure Spring MVC View Resolver
	``` xml
      <bean  class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/view/" />
        <property name="suffix"  value=".jsp"/>
      </bean>
    ```
**Development process:**
- Create controller class
	- Annotate class with `@Controller` (`@Controller` inherits from `@Component` ... supports scanning)
	``` java
	@Controller
	public class HomeController{}
  ```
- Define controller method
``` java
    @Controller
    public class HomeController{
      public String showMyPage(){
      }
    }
  ```
- add request mapping to controller method
``` java
	@Controller
	public class HomeController{
		@RequestMapping("/")
		public String showMyPage(){
		}
	}
 ```
- Return view name
``` java
	@Controller
	public class HomeController{
		@RequestMapping("/")
		public String showMyPage(){
			return "main-menu";
		}
	}
  ```
- Develop view page
	- create /WEB-INF/view/main-menu.jsp
	``` html
	<html>
    <body>
      <h2>Spring MVC Demo- Home Page</h2>
    </body>
  </html>
  ```
**Read HTML form using HTTPServeletRequest**
``` java
	@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		
		// read the request param from HTML form
		String name = request.getParameter("studentName");
	}
 ```
**Reading HTML form data using `@RequestParam` Annotation**

  ` processFormVersionThree(@RequestParam("studentName") String name, Model model)`

**Add parent controller**
``` java
    @Controller
    @RequestMapping("/hello")
    public class HelloWorldController {
      @RequestMapping("/showForm")
      public String showForm() {
        return "helloworld-form";
      }
    }
  ```
- so the method request mapping is relative to the parent request mapping. We can also avoid conflicts between some controller class which have same request mapping.

### Spring MVC Form Tags ###
- Spring MVC Form tags are building block for a web page
- Form Tags are configurable and reusable for a web page
- Spring MVC Form Tags can make use of data binding
- Automatically setting/ retriveing data from java object/ bean

**Form Tags**
- form:form- main form container
- form:input- text field
- form:textarea- multi-line text field
- form:checkbox- check box
- form:radiobutton- radio buttons
- form:select - drop down list

Form more info luv2code.com/spring-mvc-form-tags

**Web page Structure**
- JSP page with special Spring MVC Form tags
  ``` html
  <html>
    <body>
      ... regular html ...
      ... Spring MVC form tags ...
      ... more html ...
    </body>
	</html> ```

- Specify the spring namespace at beginning of JSP file

  `<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>`

**TextField Overview:**

In your Spring controller
- Before you show the form, you must add a model attribute.
- This is a bean that will hold form data for data binding.
``` java
	@RequestMapping("/showForm")
	public String showForm(Model theModel){

		theModel.addAttribute("student", new Student());
		return "student-form";
	}
  ```
- Setting up HTML Form - Data binding
 ``` xml
	<form:form action="processForm" modelAttribute="student">
		First name: <form:input path = "firstName"/>
		<br><br>
		Last name: <form:input path = "lastName"/>
		<br><br>
		<input type="submit" value="Submit"/>
	</form:form>
 ```
- modelAttribute name is same as the name passed in showForm model attribute
- path Attribute binds form field to the property of the bean/class
	- when form is loaded spring MVC uses this "path" to populate the form field using getters
	- when form is submitted spring will call the setter methods using "path"

- Handle form submission in the controller
``` java
	@RequestMapping("/processForm")
	public String processForm(@ModelAttribute("student") Student theStudent){
		// logging
		System.out.println("theStudent:" + theStudent.getLastName());

		return "student-confirmation";
	}
 ```
`@ModelAttribute` binds all that form data to our object passed in the controller. This is data binding.

- confirmation page
``` html
	<html>
	<body>
		The Student is confirmed: ${student.firstName} ${student.lastName}
	</body>
	</html>
```

**Drop-Down List Overview:**
- drop down list is represented by the tag `<form:select>` in spring MVC
``` xml
	<form:select path="country">
		<form:option value="brazil" label="Brazil"/>
		<form:option value="India" label="India"/>
	</form:select>
``` 
  -label is the attribute and its value is used to diplay.
  -value is the attribute which is actual code that is passed to controller when user hits on submit

- To get the values from Java
``` xml
	<form:select path="country">
			<form:options items="${student.countryOptions}"/>
	</form:select>
```

**Radio Button Overview:**
- A radio button is represented by the tag `<form:radiobutton>`
``` xml
	Java <form:radiobutton path="favouriteLanguage" value="Java"/>
	C     <form:radiobutton path="favouriteLanguage" value="C"/>
```
CheckBox
- A checkbox is represented by `<form:checkbox>`
``` xml
	Linux <form:checkbox path="operatingSystems" value="Linux"/>
	Mac OS<form:checkbox path="operatingSystems" value="Mac OS"/>
```

### Spring MVC Form Validation ###

**Need for validation:**
 - Check the user input form for
	- required fields.
	- valid numbers in a range.
	- valid format(postal code)
	- custom business value

**Java Standard Bean Validation API**
- Java has a standard bean validation API
- Defines a metadata model and API for entity validation
- Not tied to either the web tier or the persistence tier
- Available for server-side apps and also client side JavaFX/ Swing apps

**Spring and validation**
- Spring version 4 and heigher supports Bean validation API
- Preffered method for validation when building spring apps
- Simply add validation JARs to our project

**Validation Feature**
- required
- validate length
- validate numbers
- validate with regular expressions
- custom validation

**Validation Annotations**
- `@NotNull` checks that the annotated value is not null
- `@Min` must be a number>=value
- `@Max` must be a number <=value
- `@Size` size must match the given size
- `@Pattern`must match a regular expression pattern
- `@Future`/`@Past` date must be in future or past of given date

**Checking for required fields overview:**
- Inorder to check weather the field is required or not we must use `@NotNull` and `@Size` annotation
``` java
	@NotNull(message="is required")	//Error message if validation fails
	@Size(min=1, message="is required")	//min size = 1
	private String lastName;
  ```
- Display error message on HTML form by adding it to the attribute
``` xml
	<form:errors path="lastName" cssClass="error"/>
```
- Perform validation in controller class 
``` java
	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("customer") Customer cust, BindingResult bindRes){
		if(bindRes.hasErrors()){
			return "customer-form";
		}
		else{
			return "customer-confirmation";
		}
	}
  ```
`@Valid`: Perform validation rules on customer object
*BindingResult*: Results of the validation placed in this.The BindingResult parameter must appear immediately after the model attribute. 
- Update confirmation page

**@InitBinder**
- It works as a pre-processor
- It will pre-process each web request to our controller
- Method annotated with `@InitBinder` is executed
- We will use this annotation to remove leading and trailing white space in string passed as request and if only white space is present it will trim it to null

**Validate a Number range**
- Here we use `@Min` and `@Max` to validate the range
- Add validation rule to customer class
``` java
	@Min(value=0, message="number must be greater than or equal to zero")
	@Max(value=10, message="number must be less than or equal to 10")
	private int freePasses;
  ```
- Display error message on HTML form
- Perform validation in the controller class
- Update confirmation page

**Apply Regular expressions**
- A sequence of characters that define a search pattern
	- This pattern is used to find or match strings
- Regular expression is like its own language
- Validate a postal code
	- User can only enter 6 digits
	- Apply regular expression.
- Here we use @Pattern to validate
``` java
@Pattern(regexp="^[a-zA-Z0-9]{6}", message="only 6 chars/digits")
```

**Custom error messages**
- create a messages.properties file in src/resources
	- add message in this format `errorType.modelName.fieldName= message`
	- Ex: `typeMismatch.customer.freePasses=Invalid number.`
- Add the location of the message.properties in spring-mvc-demo-servlet.xml
``` xml
	<bean id="messageSource"
		class="org.springframework.context.support.ResoucrBundleMessageSource">
		<property name="basenames" value="resources/messages"/>	
	</bean>
```

**Custom Form Validation**
- Create `@CourseCode` annotation.
  ``` java
    @Constraint(validatedBy = CourseCodeConstraintValidator.class)
    @Target({ElementType.METHOD, ElementType.FIELD})
    @Retension(RetensionPolicy.RUNTIME)
    public @interface CourseCode{
      // define default course code
      public String value() default "LUV"

      // define default error message
      public String message() default "must start with LUV";
    }
    ```
 	- Constriant is used to saw which class we use to validate our logic
	- Target specifies where can we apply our annotation to a method or field
	- Retension says how long it will retain it. RUNTIME =  keep this annotation in java byte code so that we can use it 	  and introspect on it and instrument it during runtime
- Create CourseCodeConstraintValidator. ( this contains business class which contains rules )

