## Many to Many Mappings ##
- A course can have many students and a student can have many courses
- Need to keep track of which student in which course
- We can use a join table. ( A table that provides a mapping between two tables. It has foriegn keys for each table to define the mapping relationship)

**Dev Process:**
- Define database tables
- Update course class
  ``` java
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
  ```
- @JoinTable tells Hibernate
	- Look at the course_id column in course_student table.
	- For the other side(inverse), look at the student_id column in the course_student table.
	- Use this information to find relationship between course and student.
- Update student class
  ``` java
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
  ```
- Create main app 
  ``` java
	public static void main(String[] args){
		int id = 10;
		Course course = session.get(Course.class,id);
		Sysout(course);
		Sysout(course.getStudents());
	}
