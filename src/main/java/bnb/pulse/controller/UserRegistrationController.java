package bnb.pulse.controller;

/*import bnb.pulse.model.UserType;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bnb.pulse.model.User;
import bnb.pulse.service.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	public UserService userService;
	
	@GetMapping
	public String getPage (Model model) {
		User user = new User ();
		model.addAttribute("user", user);
		return "registration";
		
	}
	
	@PostMapping
	public String formRegistration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registration";
		}
		
		/*
		 * if (user.getUserType() == null) { user.setUserType(UserType.GUEST); }
		 */
		
		userService.userRegister(user);
		
		
		return "redirect:/";
	}
	
}
