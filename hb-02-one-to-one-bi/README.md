## One To One Bidirectional ##
**Dev Process: Bi-Directional**
- Make updates to RECIEVING class
	- Add new field to reference SENDER.
	- Add getter / setter methods for field Sender
	- Add @OneToOne annotation
	  ``` java
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
    ``` java
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
