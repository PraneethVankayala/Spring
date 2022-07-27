package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
  
import com.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		//create Session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			// create a student object
			System.out.println("creating a new student object..");
			Student tmpStudent = new Student("Daffy","Duck","paul@luv2code.com");
			//start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student..");
			System.out.println(tmpStudent);
			session.save(tmpStudent);
			
			//commit transaction
			session.getTransaction().commit();
						
			//find out the student's id: primary key
			System.out.println("Saved student. Generated id: "+tmpStudent.getId());
			
			//new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id:primary key
			System.out.println("\n Getting student with id: "+tmpStudent.getId());
			Student myStudent = session.get(Student.class, tmpStudent.getId());
			System.out.println("Get Complete: "+myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		}finally {
			factory.close();
		}
	}

}
