package Spring.Annotations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class DatabaseFortuneService implements FortuneService {

	ArrayList<String> ar = new ArrayList<String>();
	Random myRandom=new Random();
	@Override
	public String getFortune() {
		int ind = myRandom.nextInt(ar.size());
		return ar.get(ind);
	}
	@PostConstruct
	private void readFromFile() {
		File file = new File("D:\\eclipse_workspace\\Annotations\\src\\main\\java\\input.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st="";
			while((st=br.readLine())!=null) {
				ar.add(st);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
