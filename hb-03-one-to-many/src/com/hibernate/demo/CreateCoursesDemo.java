package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			
			//start Transaction
			session.beginTransaction();
			
			// get the instructor from db
			int theId = 1;
			Instructor tmpIns = session.get(Instructor.class, theId);
			
			// create some courses
			Course tmpCourse1 = new Course("Air Guitar - The Ultimate Guide");
			Course tmpCourse2 = new Course("The PinBall Masterclass");
			
			// add the courses to instructor
			tmpIns.add(tmpCourse1);
			tmpIns.add(tmpCourse2);
			
			// save the courses
			session.save(tmpCourse1);
			session.save(tmpCourse2);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
