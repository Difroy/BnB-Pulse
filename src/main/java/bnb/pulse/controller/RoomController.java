package bnb.pulse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import bnb.pulse.model.Photo;
import bnb.pulse.service.PhotoService;

@Controller
@RequestMapping("/room")
public class RoomController {
	@Autowired
	private PhotoService photoService;

	
	@GetMapping
	public String getPage(Model model, @RequestParam(name = "id", required = false) Integer id) {
		Photo photo = photoService.getPhotoById(id);
		model.addAttribute("rooms", photo.getRoom());
		return "room";
	}
	
	
}
