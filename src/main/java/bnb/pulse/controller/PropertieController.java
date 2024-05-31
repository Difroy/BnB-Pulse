package bnb.pulse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bnb.pulse.model.Propertie;
import bnb.pulse.model.Room;
import bnb.pulse.service.PropertieService;
import bnb.pulse.service.RoomService;

@Controller
@RequestMapping("/propertie")
public class PropertieController {

	@Autowired
	private PropertieService propertieService;
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping
	public String getPage(Model model, 
				@RequestParam(name = "photoId", required = false) Integer id, 
				@RequestParam(name = "room", required = false) Integer room, @RequestParam(name = "search", required = false) String search) {
		
		
		List<Propertie> properties;
		if(search != null) {
            properties = propertieService.SearchPropertieByCity(search);
            }else {
            	properties = propertieService.getProperties();
           
            }
		
		model.addAttribute("properties ",properties);
		return "propertie";
	}
	
	/*
	 * @GetMapping("/{id}") public String getPropertie(Model
	 * model, @RequestParam(name = "id", required = false) Integer id) { Propertie
	 * propertie = propertieService.getPropertieById(id);
	 * model.addAttribute("propertie", propertie); return "propertie"; }
	 */
	@GetMapping("/{id}")
    public String getPropertieDetails(@PathVariable("id") int id, Model model) {
        Propertie propertie = propertieService.getPropertieById(id);
        List<Room> rooms = roomService.getRoomsByProperties_Id(id);
        model.addAttribute("propertie", propertie);
        model.addAttribute("rooms", rooms);
        return "propertieDetails";
    }
	
	@GetMapping("/room/{roomId}")
    public String getRoomDetails(@PathVariable("roomId") int roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        return "roomDetails";
    }
	
	
}
