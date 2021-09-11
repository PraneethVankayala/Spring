 package spring.demo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //create the  object
    	Coach coach = new TrackCoach(null);
    	
    	//use the object
    	System.out.println(coach.getDailyWorkout());
    }
}
