package springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
	
	//need a controller method to show initial form
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
	//need another method to process the form
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}
	
	//need new controller method to read form data and add data to the model
	@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		
		// read the request param from HTML form
		String name = request.getParameter("studentName");
		// covert the data to upper class
		name = name.toUpperCase();
		// create the message
		String res = "Yo! "+name;
		// add message to the model
		model.addAttribute("message", res);
		
		return "helloworld";
	}
	
	@RequestMapping("/processFormVersionThree")
	public String processFormVersionThree(@RequestParam("studentName") String name, Model model) {
		// covert the data to upper class
		name = name.toUpperCase();
		// create the message
		String res = "Hey bro! "+name;
		// add message to the model
		model.addAttribute("message", res);
		
		return "helloworld";
	}
}
