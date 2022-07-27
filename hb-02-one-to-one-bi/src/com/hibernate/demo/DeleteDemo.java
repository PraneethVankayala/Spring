package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

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
			
			//get the instructor by its primary key
			int id = 2;
			Instructor tmpIns = session.get(Instructor.class, id);
			System.out.println("Found instructor: "+tmpIns);

			// delete the instructor
			if(tmpIns!=null) {
				System.out.println("Deleting: "+tmpIns);
				// This will also delete details object because cascadeType.ALL
				session.delete(tmpIns);
			}
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			factory.close();
		}
	}

}
