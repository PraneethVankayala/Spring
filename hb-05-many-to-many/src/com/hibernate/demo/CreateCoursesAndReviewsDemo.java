package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {
		//create Session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			//start Transaction
			session.beginTransaction();
			
			// create a course
			Course tmpCourse = new Course("Pacman - How to score one Million Points");
			//add some reviews
			tmpCourse.addReview(new Review("Great Course ... loved it!"));
			tmpCourse.addReview(new Review("Cool course, job well done"));
			tmpCourse.addReview(new Review("What a dumb course, you are an idiot"));

			//save the course.. and leverage the cascade all :-)
			System.out.println("Saving the course");
			System.out.println(tmpCourse);
			System.out.println(tmpCourse.getReviews());
			session.save(tmpCourse);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
