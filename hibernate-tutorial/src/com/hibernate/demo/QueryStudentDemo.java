package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
  
import com.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		//create Session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			 
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display the students.
			displayStudents(theStudents);
			
			//query students with last name Doe
			List<Student> student = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			System.out.println("Students who have last name as Doe");
			displayStudents(student);
			
			//query students with email ends with luv2code.com
			student = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();		
			//display the students
			System.out.println("Students who have email ending with luv2code.com");
			displayStudents(student);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		}finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tmp:theStudents) {
			System.out.println(tmp);
		}
	}

}
