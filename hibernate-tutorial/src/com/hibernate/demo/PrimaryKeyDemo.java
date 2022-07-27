package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {
	public static void main(String[] args) {
		//create Session Factory
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
				
				Session session = factory.getCurrentSession();
				
				try {
					// create 3 student object
					System.out.println("creating a new student object..");
					Student tmpStudent1 = new Student("John","Doe","paul@luv2code.com");
					Student tmpStudent2 = new Student("Mary","Public","mary@luv2code.com");
					Student tmpStudent3 = new Student("Bonita","Applebum","bonita@luv2code.com");

					//start a transaction
					session.beginTransaction();
					
					// save the student object
					System.out.println("Saving the student..");
					session.save(tmpStudent1);
					session.save(tmpStudent2);
					session.save(tmpStudent3);

					
					//commit transaction
					session.getTransaction().commit();
					System.out.println("Done!!");
				}finally {
					factory.close();
				}
	}
}
