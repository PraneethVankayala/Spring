Hibernate

- This framework persists/ save java objects in database

Benifits of Hibernate:

- Hibernate handles all of the lower-level SQL.
- Minimizes the amount of JDBC code you have to develop
- Hibernate provides the Object-to-Relational Mapping (ORM)

Object-to-Relational Mapping (ORM)

- The developer defines mapping between Java class and database table. We can define mapping using two methods
	- Configuration file(XML)
	- Java Annotations


Saving a java object using hibernate

// create Java object
Student theStudent = new Student("John", "Doe", "john@luv2code.com");

// save it to database
int id = (Integer) session.save(theStudent); // here session is a hibernate object id will get the primary key of that object 

// now retrieve from database using the primary key
Student myStudent = session.get(Student.class, id);

// querying for Java Objects 
Query query = session.createQuery("from Student"); //from student is Hibernate Query Langauage
List<Student> students = query.list();

How does hibernate relate to jdbc
- Hibernate uses JDBC for all database communication. It is just another layer of extraction on top of JDBC. 

Hibernate Dev Process:
- Add Hibernate configuration file
	- This file basically tells hibernate how to connect to database.
- Annotate java class
	-Map class to database table
		@Entity
		@Table(name="student")
		public class Student{}
	- Map fields to database columns
		@Id   //primary key
		@Column(name="id")
		private int id;
		
		@Column(name="first_name")
		private String firstName;
- Develop java code to perform database operations
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
			     

Two key players in the hibernate
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

To save a java object in database
// create a java object
Student tmp = new Student("Paul", "Weasely");
//start transaction
session.beginTransaction();
// save the object
session.save(tmp);
//commit the transaction
session.getTransaction().commit();

Hibernate and Primary key
Primary key: uniquely identifies each row in a table, must be unique and cannot contain null values. 

ID Generation Strategies
GenerationType.AUTO => Pick an appropriate strategy for particular database
GenerationType.IDENTITY => Assign primary keys using database identity column.            
GenerationType.SEQUENCE => Assign primary keys using a database sequence 
GenerationType.TABLE => Assign primary keys using an underlying database table to ensure uniqueness


Most common used for MySQL AUTO_INCREMENT - IDENTITY

Apart from these strategies we can develop a custom generation strategy by implementing org.hibernate.id.IdentifierGenerator, in subclass override the method: public Serializable generate(...)

Retrieving the object from Hibernate
Student theStudent = new Student("John", "Duck". "vb@gmail.com");
session.save(theStudent);
Student tmp = session.get(Student.class, theStudent.getId());

if the object is found it will return the object otherwise it will return null

Query objects using Hibernate
- Lanugage for retrieving objects known as HQL
- SImilar in nature to SQL -> where, like, order by, join, in etc .,

Syntax:
List<Student> st = session.createQuery("from student").getResultList();

//lastName="Doe"
List<Student> st = session.createQuery("from student s where s.lastName='Doe'").getResultList();

//lastName="Doe" or firstName="Daffy"
List<Student> st = session.createQuery("from student s where s.lastNam='Doe'"+" OR firstName='Daffy'").getResultList();

//like predicate
List<Student> st = session.createQuery("from student s where s.email LIKE '%luv2code.com'").getResultList();

Update an object from Hibernate
-> Update a single student
//get the student using the id
Student student  = session.get(Student.class, id);

//update first name to Bunny
student.setFirstName("Bunny");

//commit the transaction
session.getTransaction().commit();

--> This commit function will update the row present in the database

-> To update all the students in the database
session.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();
 
Delete Objects using Hibernate
//get the student using id
Student student = session.get(Student.class,id);

// delete the student
session.delete(student);

//commit the transaction
session.getTransaction().commit();

-> Another way to delete a row
session.createQuery("delete from Student where id=2").executeUpdate();

Advanced Mappings
- In database, you most likely will have
	- Multiple Tables
	- Relationships between Tables
- Need to model this with hibernate
- Three different models in hibernate
	- One to One
	- One to Many, Many to one
	- Many to Many

One to One Mapping
- An instructor can have an instructor detail entity
	- Instructor can have basic info like firstname, lastname etc.
	- Instructor detail will have more details about the instructor like their hobbies, linkedin handle etc.
- Dev Process
	- Define Database tables
	- create Instructor and Instructor Detail class
		- @OneToOne annotation will help us to connect these two classes
		public class instructor{
			@OneToOne
			@JoinColumn(name = "instructor_detail_id")
			private InstructorDetail instructorDetail;
		}
	- Create our Main App
		Instructor tmpInstructor = new Instructor("chad,"Darby", "darby@luv3code.com");
		InstructorDetail tmpInstructorDetail = new InstructorDetail("https://youtube.com","Luv 2 code");
		tmpInstructor.setInstructorDetail(tmpInstructorDetail);
		session.beginTransaction();
		session.save(tmpInstructor);
		session.getTransaction().commit();

Cascade types:
 - Persist: If entity is saved/persisted, related entity will also be persisted.
 - Remove: If entity is removed/ deleted, related entity will also be deleted.
 - Refresh : If entity is refreshed, related entity will also be refreshed
 - Detach: If entity is detached then related entity will also be detached
 - Merge: If entity is merged then related entity will also be merged.
 - All: All of the above cascade types.

Configure cascade types:
   @OneToOne(cascade = CascadeType.All)
    private InstructorDetail instructorDetail;

 - By default no operations are cascaded.
 - Configuring Multiple cascade types
  @OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, 			CascadeType.REFRESH, CascadeType.REMOVE});

	SENDER -----> RECIEVING
Dev Process: Bi-Directional
	- Make updates to RECIEVING class
		- Add new field to reference SENDER.
		- Add getter / setter methods for field Sender
		- Add @OneToOne annotation
		public class InstructorDetail{
			@OneToOne(mappedBy="instructorDetail", cascade = CascadeType.ALL)
			private Instructor instructor;
		}
		- mappedBy tells hibernate
			- Look at the instructorDetail property in the Instructor class
			- Use information from the Instructor class @JoinColumn
			- To help find the assosiated instructor
		- Add support for Cascading
	- Create Main App
	//create the objects
	Instructor tmpInstructor = new Instructor("Chad","Darby","darby@luv2code.com");
	InstructorDetail tmpInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube","Coding");
			
	// associate the objects
	tmpInstructor.setInstructorDetail(tmpInstructorDetail);
			
	//start Transaction
	session.beginTransaction();
			
	// save the instructor
	// This will also save the details object because of CascadeType.ALL
	System.out.println("Saving instructor: "+tmpInstructor.toString());
	session.save(tmpInstructor);
			
	//commit transaction
	session.getTransaction().commit();

One to Many Mappings
- An Instructor can have multiple courses
   INSTRUCTOR --> COURSE
	|
          COURSE
- If you delete an instructor, DO NOT delete the course
- If you delete a course, DO NOT delete the instructor
- Bi- directional

Dev Process:
- Define tables
- Create course and update Instructor class
	public class Course{
		@ManyToOne(cascade={PERSIST,MERGE,DETACH,REFRESH})
		@JoinColumn(name="instructor_id")
		private Instructor instructor;
	}
	public class Instructor{
		@OneToMany(mappedBy="instructor",cascade={PERSIST,MERGE,DETACH,REFRESH})
		private List<Course> courses;
		
		public void add(Course theCourse){
			if(course == null){ courses=new ArrayList<Course>();}
			courses.add(theCourse);
			theCourse.setInstructor(this);
		}
	}
- Create Main app
	main(String[] args){
		int theId = 1;
		Instructor tmpIns = session.get(Instructor.class, theId);
		Sysout("tmpInstructor:"+tempIns);
		Sysout("courses:"+tmpIns.getCourses());
	}

Many to Many Mappings
- A course can have many students and a student can have many courses
- Need to keep track of which student in which course
- We can use a join table. ( A table that provides a mapping between two tables. It has foriegn keys for each table to define the mapping relationship)

Dev Process:
- Define database tables
- Update course class
	@Entity
	@Table(name="course")
	public class Course{
		@ManyToMany
		@JoinTable(
			name="course_student",
			joinColumns=@JoinColumn(name="course_id"),
			inverseJoinColumns=@JoinColumn(name="student_id")
		)
		private List<Student> students;
		//getter / setters
	}
	- @JoinTable tells Hibernate
		- Look at the course_id column in course_student table.
		- For the other side(inverse), look at the student_id column in the course_student table.
		- Use this information to find relationship between course and student.
- Update student class
	@Entity
	@Table(name="student")
	public class Student{
		@ManyToMany
		@JoinTable(
			name="course_student",
			joinColumns=@JoinColumn(name="student_id"),
			inverseJoinColumns=@JoinColumn(name="course_id")	
		)
		private List<Course> courses;	
	}
- Create main app 
	public static void main(String[] args){
		int id = 10;
		Course course = session.get(Course.class,id);
		Sysout(course);
		Sysout(course.getStudents());
	}

Important Database Concepts
- Primary Key and foriegn Key
	- Primary Key: Uniquely identifies a row in a table
	- Foriegn key: It is a field in one table refers to the primary key of the other table, It's main purpose is to preserve relationship between tables. Referential Integrity
- Cascade
 	- Apply the same operation to related entities
	- if we delete instructor we should also delete instructor detail( CASCADE_DELETE)
	- This cascade depends on the usecaase

Fetch types: Eager vs Lazy loading
- When we fetch/retrieve data should we retrieve everything?
	- Eager will retrieve everything
	- Lazy will retrieve on request
- Only load data which is absolutely needed, Prefer Lazy loading over eager loading
- When you define the mapping relationship, you can specify EAGER or LAZY
	public class Instructor{
		@OneToMany(fetch=FetchType.LAZY, mappedBy="instructor")
		private List<Course> courses;
	}
- Default fetch types
	- @OneToOne: FetchType.EAGER
	- @OneToMany: FetchType.LAZY
	- @ManyToOne: FetchType.EAGER
	- @ManyToMany: FetchType.LAZY

- Retrieve lazy data using
	- session.get and call appropriate getter methods
	- Hibernate query with HQL
		int theId = 1;
			Query<Instructor> query = session.createQuery("select i from Instructor i "+
															"JOIN FETCH i.courses "+
															"where i.id=:theInstructorId",Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			Instructor tmpIns = query.getSingleResult();

- Lazy loading requires an open hibernate connection, need an connection to database to retrieve data. If session is closed hibernate will throw an exception.
Entity Lifecycle
- Detach: If entity is detached it is not associated with a Hibernate session
- Merge: If instance is detached from session, then merge will reattach to session
- Persist: Transitions new instances to managed state. Next flush/commit will save in db
- Remove: Transitions managed entity to be removed. Next flush/commit will delete from db
- Refresh: Reload/synch object with data from db. Prevents stale data.

Relationships:
- Uni-Directional
- Bi-Directional
	- To use Bi-Directional we can keep the existing database schema
	- Simply Update the java code.

