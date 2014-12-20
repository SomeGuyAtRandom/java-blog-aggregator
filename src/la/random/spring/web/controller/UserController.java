package la.random.spring.web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import la.random.spring.web.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	private static final String className = "UserController";

	public UserController() {
		System.out.println(className + "()");
	}
	
	
	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	public String users(Model model) {

		System.out.println(className + ".users(model)");
		System.out.println("model -> " + model.toString());
		model.addAttribute("users", userService.findAll());
		System.out.println("model -> " + model.toString());
		return "users";
	}

}
