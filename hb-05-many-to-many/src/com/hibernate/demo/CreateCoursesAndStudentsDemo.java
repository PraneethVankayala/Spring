package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

public class CreateCoursesAndStudentsDemo {

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
			
			// create a course
			Course tmpCourse = new Course("Pacman - How to score one Million Points");
			
			System.out.println("\n Saving the course....");
			session.save(tmpCourse);
			System.out.println("Saved the course: "+tmpCourse);
			
			// create the students
			Student tmpStudent1 = new Student("John","Doe","john@gmail.com");
			Student tmpStudent2 = new Student("Mary","Public","mary@gmail.com");
			
			// add students to the course
			tmpCourse.addStudent(tmpStudent1);
			tmpCourse.addStudent(tmpStudent2);
			
			// save the students  
			System.out.println("\n Saving students ...");
			session.save(tmpStudent1);
			session.save(tmpStudent2);
			System.out.println("Saved students: "+tmpCourse.getStudents());
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
