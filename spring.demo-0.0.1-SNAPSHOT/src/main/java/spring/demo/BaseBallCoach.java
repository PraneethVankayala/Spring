package spring.demo;

public class BaseBallCoach implements Coach {
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 mins on batting practice";
	}
	
	private FortuneService fortuneService;
	
	public BaseBallCoach() {
		
	}
	public BaseBallCoach(FortuneService fortuneService) {
		super();
		this.fortuneService = fortuneService;
	}


	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return fortuneService.getFortune();
	}
	
}
