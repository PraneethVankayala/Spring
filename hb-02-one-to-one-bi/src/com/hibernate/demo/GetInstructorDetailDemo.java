package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		//create Session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			//start Transaction
			session.beginTransaction();
			
			//get the instructorDetail by its primary key
			int id = 2333;
			InstructorDetail tmpInsDetail = session.get(InstructorDetail.class, id);
			System.out.println("Found instructorDetail: "+tmpInsDetail);
			System.out.println("Associated Instructor:"+tmpInsDetail.getInstructor());

			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			// handle connection leak issue
			session.close();
			factory.close();
		}
	}

}
