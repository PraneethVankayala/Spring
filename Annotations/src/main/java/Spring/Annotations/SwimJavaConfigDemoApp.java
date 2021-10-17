package Spring.Annotations;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
 
/**
 * Hello world!
 *
 */
public class SwimJavaConfigDemoApp 
{
    public static void main( String[] args )
    { 
        // read spring config java class
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
    	// get the bean from spring container
    	SwimCoach coach = context.getBean("swimCoach",SwimCoach.class);
    	// call a method on the bean
    	System.out.println(coach.getDailyWorkout());
    	// call a method to get Daily fortune
    	System.out.println(coach.getDailyFortune());
    	//call our new swim methods
    	System.out.println("email: "+coach.getEmail());
    	System.out.println("team: "+coach.getTeam());
    	// close the container
    	context.close();
    }
}
