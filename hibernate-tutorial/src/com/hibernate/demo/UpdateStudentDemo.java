package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
  
import com.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		//create Session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			
			//new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id:primary key
			System.out.println("\n Getting student with id: "+studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			//updating the student's first Name with id=studentId
			System.out.println("Updating student...");
			myStudent.setFirstName("Bunny1");
			
			//commit the transaction
			session.getTransaction().commit();
			
			//update email for  all rows in SQL
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Update email for all students");
			
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			session.getTransaction().commit();
			System.out.println("Done!!");
		}finally {
			factory.close();
		}
	}

}
