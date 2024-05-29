package bnb.pulse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bnb.pulse.model.User;
import bnb.pulse.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class UserLoginController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String getPage (Model model, HttpSession session, @RequestParam(name = "error", required = false) String error) {
		User user = (User) session.getAttribute("utente");
		model.addAttribute("user", user);
		
		if(session.getAttribute("user") != null) {
			return "redirect:/";
		}
			
			model.addAttribute("error", error);
			return "login";
	}
	@PostMapping
	public String formAccess(@RequestParam("username")String username,
							 @RequestParam("password")String password,
							 HttpSession session) {
		if (!userService.loginUtente(username, password, session))
			return "redirect:/login?error=true";
		return "redirect:/";
		
	}
	
}
