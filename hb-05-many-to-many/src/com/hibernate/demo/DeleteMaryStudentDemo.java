package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

public class DeleteMaryStudentDemo {

	public static void main(String[] args) {
		//create Session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			//start Transaction
			session.beginTransaction();
			
			// get the student mary from database
			int studentId = 2;
			Student tmpStudent = session.get(Student.class, studentId);
			
			System.out.println("\n Loaded Student: "+tmpStudent);
			System.out.println("Courses: "+tmpStudent.getCourses());
			
			//delete a student
			System.out.println("\n Deleting student:"+tmpStudent);
			session.delete(tmpStudent);
	
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
