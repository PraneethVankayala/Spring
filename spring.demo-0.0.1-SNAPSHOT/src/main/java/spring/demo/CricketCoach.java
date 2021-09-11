package spring.demo;

public class CricketCoach implements Coach{

	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Practise fast bowling for 15 mins";
	}
	
	private FortuneService fortuneService;
	
	// add emailAddress and team;
	private String emailAddress;
	private String team;
	
	public CricketCoach() {
		System.out.println("CricketCoach: inside no-org constructor");
	}
	public CricketCoach(FortuneService fortuneService) {
		super();
		this.fortuneService = fortuneService;
	}
	

	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("Inside Cricket Setter method -setFortune");
		this.fortuneService = fortuneService;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		System.out.println("Inside Cricket Setter method -setEmail");
		this.emailAddress = emailAddress;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		System.out.println("Inside Cricket Setter method -setTeam");
		this.team = team;
	}
	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return fortuneService.getFortune();
	}
	
}
