package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			
			//option2: Hibernate query with HQL
			
			// get the instructor from db
			int theId = 1;
			Query<Instructor> query = session.createQuery("select i from Instructor i "+
															"JOIN FETCH i.courses "+
															"where i.id=:theInstructorId",Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			Instructor tmpIns = query.getSingleResult();
		
			System.out.println("luv2code: Instructor: "+tmpIns);

			//commit transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();

			//get course for the instructor
			System.out.println("luv2code: Courses: "+tmpIns.getCourses());
			System.out.println("luv2code: Done!!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
