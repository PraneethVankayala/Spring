package com.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
  
import com.hibernate.demo.entity.Student;
import com.hibernate.demo.utils.DateUtils;

public class CreateStudentDemo2 {

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
			
			String dateOfBirth = "21/05/2000";
			Student tmpStudent = new Student("Paul","Wall","paul@luv2code.com",DateUtils.parseDate(dateOfBirth));
			
			//start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student..");
			session.save(tmpStudent);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			factory.close();
		}
	}

}
