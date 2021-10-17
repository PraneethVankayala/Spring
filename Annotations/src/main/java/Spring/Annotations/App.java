package Spring.Annotations;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    { 
        // read spring config file
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	// get the bean from spring container
    	Coach coach = context.getBean("tennisCoach",Coach.class);
    	Coach bcoach = context.getBean("badmintonCoach",Coach.class);
    	// call a method on the bean
    	System.out.println(coach.getDailyWorkout());
    	System.out.println(bcoach.getDailyWorkout());
    	// call a method to get Daily fortune
    	System.out.println(coach.getDailyFortune());
    	System.out.println(bcoach.getDailyFortune());
    	// close the container
    	context.close();
    }
}
