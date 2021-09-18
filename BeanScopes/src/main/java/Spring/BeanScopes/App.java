package Spring.BeanScopes;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
        
        Coach coach = context.getBean("myCoach",Coach.class);
        Coach alphaCoach = context.getBean("myCoach",Coach.class);
        
        boolean result = (coach==alphaCoach);
        System.out.println("Pointing to the same object "+result);
        System.out.println("Memory location for coach: "+coach);
        System.out.println("Memory location for alpha coach: "+alphaCoach);
        
        context.close();


        
    }
}
