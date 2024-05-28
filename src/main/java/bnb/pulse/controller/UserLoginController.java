package bnb.pulse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bnb.pulse.service.UserService;

@Controller
@RequestMapping("/login")
public class UserLoginController {
	@Autowired
	private UserService userService;
	
	
}
