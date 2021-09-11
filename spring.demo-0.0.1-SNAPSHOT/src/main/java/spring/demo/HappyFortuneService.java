package spring.demo;

import java.util.Random;

public class HappyFortuneService implements FortuneService {
	String fortune[]=new String[3];
	private Random myRandom = new Random();
	public HappyFortuneService(String[] fortune) {
		super();
		this.fortune = fortune;
	}

	
	public void setFortune(String[] fortune) {
		this.fortune = fortune;
	}


	@Override
	public String getFortune() {
		// TODO Auto-generated method stub
		int val = myRandom.nextInt(fortune.length);
		return fortune[val];
	}

}
