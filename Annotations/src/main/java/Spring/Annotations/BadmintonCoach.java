package Spring.Annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BadmintonCoach implements Coach {

	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	public BadmintonCoach() {
		System.out.println(">>BadmintonCoach: inside default constructor");

	}
	public BadmintonCoach(FortuneService fortuneService) {
		super();
		System.out.println(">>BadmintonCoach: inside constructor");
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Practise in Badminton Coach Daily";
	}
	
//	@Autowired
//	public void doSomeCrazyStuff(FortuneService fs) {
//		System.out.println(">> BadmintonCoach: Inside doSomeCrazyStuff method()");
//		this.fortuneService=fs;
//	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return fortuneService.getFortune();
	}

}
