package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		//create Session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects
			Instructor tmpInstructor = new Instructor("Chad","Darby","darby@luv2code.com");
			InstructorDetail tmpInstructorDetail = 
						new InstructorDetail("http://www.luv2code.com/youtube","Coding");
			
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
			System.out.println("Done!!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
