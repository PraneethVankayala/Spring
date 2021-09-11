package spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterAppDemo {
		public static void main(String[] args) {
			// load the spring configuration file
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			
			// retrieve bean from spring container
			CricketCoach coach = context.getBean("myCoach2",CricketCoach.class);
			
			// call methods on the bean
			System.out.println(coach.getDailyWorkout());
			System.out.println(coach.getDailyFortune());
			
			// call our new methods to get literal values
			System.out.println(coach.getEmailAddress());
			System.out.println(coach.getTeam());
			
			// close the context
			context.close();
			
		}
}
