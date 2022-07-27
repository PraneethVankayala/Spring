## Hibernate ##

- This framework persists/ save java objects in database

**Benifits of Hibernate:**
- Hibernate handles all of the lower-level SQL.
- Minimizes the amount of JDBC code you have to develop
- Hibernate provides the Object-to-Relational Mapping (ORM)

**Object-to-Relational Mapping (ORM)**
- The developer defines mapping between Java class and database table. We can define mapping using two methods
	- Configuration file(XML)
	- Java Annotations


**Saving a java object using hibernate**

- create Java object
  ``` java
     Student theStudent = new Student("John", "Doe", "john@luv2code.com"); 
   ```

- save it to database
  ``` java
    int id = (Integer) session.save(theStudent); // here session is a hibernate object id, will get the primary key of that object 
  ```
- now retrieve from database using the primary key
  ``` java
    Student myStudent = session.get(Student.class, id);
   ```
**Querying for Java Objects**
 ``` java
Query query = session.createQuery("from Student"); //from student is Hibernate Query Langauage
List<Student> students = query.list();
 ``` 

**How does hibernate relate to jdbc**
- Hibernate uses JDBC for all database communication. It is just another layer of extraction on top of JDBC. 

**Hibernate Dev Process:**
- Add Hibernate configuration file
	- This file basically tells hibernate how to connect to database.
- Annotate java class
	- Map class to database table
    ``` java
      @Entity
      @Table(name="student")
      public class Student{}
    ```
	- Map fields to database columns
	  ``` java
      @Id   //primary key
      @Column(name="id")
      private int id;

      @Column(name="first_name")
      private String firstName;
      ```
- Develop java code to perform database operations
  ``` java
	SessionFactory factory = new Configuration()
			     .configure("hibernate.cfg.xml")	
			     .addAnnotatedClass(Student.class)
			     .buildSessionFactory();
	Session session = factory.getCurrentSession();
	
	try{
		//now use the session object to save/retrive Java objects
	}finally{
		factory.close();
	}
  ```
			     

**Two key players in the hibernate**
- Session Factory
	- Reads the hibernate config file 
	- creates session object.
	- Heavy-weight object
	- Only create once in your app
- Session
	- it is a wrapper around JDBC connection
	- Main object used to save/retrieve objects.
	- Short lived object.
	- Retrieved from sessionFactory

**To save a java object in database**
- create a java object
  ``` java
    Student tmp = new Student("Paul", "Weasely");
  ```
- start transaction
  ``` java
    session.beginTransaction();
  ```  
- save the object
  ``` java
    session.save(tmp);
  ```
- commit the transaction
  ``` java
    session.getTransaction().commit();
  ```

**Hibernate and Primary key**
Primary key: uniquely identifies each row in a table, must be unique and cannot contain null values. 

**ID Generation Strategies**
- GenerationType.AUTO => Pick an appropriate strategy for particular database
- GenerationType.IDENTITY => Assign primary keys using database identity column.            
- GenerationType.SEQUENCE => Assign primary keys using a database sequence 
- GenerationType.TABLE => Assign primary keys using an underlying database table to ensure uniqueness

>**Note**   Most common used for MySQL **AUTO_INCREMENT - IDENTITY**

- Apart from these strategies we can develop a custom generation strategy by implementing org.hibernate.id.IdentifierGenerator, in subclass override the method: public Serializable generate(...)

**Retrieving the object from Hibernate**
```java
  Student theStudent = new Student("John", "Duck". "vb@gmail.com");
  session.save(theStudent);
  Student tmp = session.get(Student.class, theStudent.getId());
```
- if the object is found it will return the object otherwise it will return null

**Query objects using Hibernate**
- Lanugage for retrieving objects known as HQL
- Similar in nature to SQL -> where, like, order by, join, in etc .,
```java
  List<Student> st = session.createQuery("from student").getResultList();

  //lastName="Doe"
  List<Student> st = session.createQuery("from student s where s.lastName='Doe'").getResultList();

  //lastName="Doe" or firstName="Daffy"
  List<Student> st = session.createQuery("from student s where s.lastNam='Doe'"+" OR firstName='Daffy'").getResultList();

  //like predicate
  List<Student> st = session.createQuery("from student s where s.email LIKE '%luv2code.com'").getResultList();
```

**Update an object from Hibernate**
- Update a single student
``` java
  //get the student using the id
  Student student  = session.get(Student.class, id);

  //update first name to Bunny
  student.setFirstName("Bunny");

  //commit the transaction
  session.getTransaction().commit();
```
- This commit function will update the row present in the database

- To update all the students in the database
  ``` java
    session.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();
  ```
 
**Delete Objects using Hibernate**
 ``` java
  //get the student using id
  Student student = session.get(Student.class,id);

  // delete the student
  session.delete(student);

  //commit the transaction
  session.getTransaction().commit();
```
- Another way to delete a row
  ``` java
  session.createQuery("delete from Student where id=2").executeUpdate();
  ```
  
**Advanced Mappings**
- In database, you most likely will have
	- Multiple Tables
	- Relationships between Tables
- Need to model this with hibernate
- Three different models in hibernate
	- One to One
	- One to Many, Many to one
	- Many to Many
