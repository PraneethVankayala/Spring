package spring.demo;

public class TrackCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}
	
	private FortuneService fortuneService;
	
	public TrackCoach() {
		System.out.println("TrackCoach: inside no-org constructor");
	}

	
	public TrackCoach(FortuneService fortuneService) {
		super();
		this.fortuneService = fortuneService;
	}


	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return "Just do it:"+fortuneService.getFortune();
	}


	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("Inside Track Setter method");
		this.fortuneService = fortuneService;
	}
	
	
}
