package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

public class AddCoursesForMaryDemo {

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
			
			// create more courses
			Course tmpCourse1 = new Course("Rubik's Cube - How to Speed Cube");
			Course tmpCourse2 = new Course("Atari 2600 - Game Development");
			 
			// add student to the courses
			tmpCourse1.addStudent(tmpStudent);
			tmpCourse2.addStudent(tmpStudent);
			;
			// save the courses
			System.out.println("\n Saving the courses...");
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
