package Spring.BeanScopes;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		Coach coach = context.getBean("myCoach1", Coach.class);
		System.out.println(coach.getDailyWorkout());
		context.close();
	}

}
