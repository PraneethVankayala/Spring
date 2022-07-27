package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Employee;

public class CURDEmployee {

	public static void main(String[] args) {
		// creating the session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			// create Employees object
			Employee emp1 = new Employee("Praneeth","Vankayala","Amazon");
			Employee emp2 = new Employee("Aravind","Sampath","Informatica");
			Employee emp3 = new Employee("Akhil","Tiwari","Startup");
			
			session.beginTransaction();
			
			//save the employees to the database
			session.save(emp1);
			session.save(emp2);
			session.save(emp3);
			
			session.getTransaction().commit();
			System.out.println("Done inserting the objects in Database");
			
			// Read objects from database
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Getting Student with id:"+emp1.getId());
			Employee readEmp = session.get(Employee.class, emp1.getId());
			System.out.println("Student fetched from database:"+readEmp.toString());
			
			session.getTransaction().commit();
			// Update the object
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Update the student with id:"+emp2.getId());
			Employee updEmp = session.get(Employee.class, emp2.getId());
			updEmp.setCompany("Amazon");
			session.getTransaction().commit();
			
			//deleting the object
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Deleting this object from database:"+emp3.getId());
			session.delete(emp3);
			session.getTransaction().commit();
			
			System.out.println("Done");
			
			
			
			
		}finally {
			factory.close();
		}
		
	}
}
