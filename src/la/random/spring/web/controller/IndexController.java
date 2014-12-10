package la.random.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
	
	private static final String className = "IndexController";
	
	public IndexController(){
		System.out.println(className + "()");
	}
	
	@RequestMapping("/index")
	public String index(){
		System.out.println(className + ".index()");
		// the following points to the entry in general.xml
		return "index";
	}

}
