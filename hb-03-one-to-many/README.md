## One to Many Mappings ##
- An Instructor can have multiple courses
   INSTRUCTOR --> COURSE
	        |
          COURSE
- If you delete an instructor, DO NOT delete the course
- If you delete a course, DO NOT delete the instructor
- Bi- directional

**Dev Process**
- Define tables
- Create course and update Instructor class
  ``` java
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
  ```
- Create Main app
  ``` java
    main(String[] args){
      int theId = 1;
      Instructor tmpIns = session.get(Instructor.class, theId);
      Sysout("tmpInstructor:"+tempIns);
      Sysout("courses:"+tmpIns.getCourses());
    }
  ```
