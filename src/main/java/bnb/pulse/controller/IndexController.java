package bnb.pulse.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import bnb.pulse.model.User;

//localhost:8080
@Controller
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String getPage (Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		return "index";
		
	}
}
