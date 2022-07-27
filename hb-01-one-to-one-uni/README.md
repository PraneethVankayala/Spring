## One to One Mapping ##
- An instructor can have an instructor detail entity
	- Instructor can have basic info like firstname, lastname etc.
	- Instructor detail will have more details about the instructor like their hobbies, linkedin handle etc.
**Dev Process**	
- Define Database tables
	- create Instructor and Instructor Detail class
		- @OneToOne annotation will help us to connect these two classes
	
		  ``` java
          public class instructor{
            @OneToOne
            @JoinColumn(name = "instructor_detail_id")
            private InstructorDetail instructorDetail;
          }
  - Create our Main App
	  ```java
      Instructor tmpInstructor = new Instructor("chad,"Darby", "darby@luv3code.com");
      InstructorDetail tmpInstructorDetail = new InstructorDetail("https://youtube.com","Luv 2 code");
      tmpInstructor.setInstructorDetail(tmpInstructorDetail);
      session.beginTransaction();
      session.save(tmpInstructor);
      session.getTransaction().commit();
    ```

**Cascade types**
 - Persist: If entity is saved/persisted, related entity will also be persisted.
 - Remove: If entity is removed/ deleted, related entity will also be deleted.
 - Refresh : If entity is refreshed, related entity will also be refreshed
 - Detach: If entity is detached then related entity will also be detached
 - Merge: If entity is merged then related entity will also be merged.
 - All: All of the above cascade types.

**Configure cascade types:**  
  ``` java
   @OneToOne(cascade = CascadeType.All)
    private InstructorDetail instructorDetail;
  ```
 - By default no operations are cascaded.
 - Configuring Multiple cascade types
   ``` java
    @OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE});
   ```

	SENDER -----> RECIEVING
