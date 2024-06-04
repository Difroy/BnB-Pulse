package bnb.pulse.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import bnb.pulse.model.Property;
import bnb.pulse.model.User;
import bnb.pulse.service.PropertyService;

//localhost:8080
@Controller
@RequestMapping("/")
public class IndexController {
	
	
	@Autowired
	private PropertyService propertyService;



	@GetMapping
	public String getPage (Model model, HttpSession session ) {
		
		
		List<Property> properties = propertyService.getProperties();
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("properties", properties);
		
		return "index";
		
		
		
		
	}
	@GetMapping("/logout")
	public String logoutUtente (HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
		
	}
}
