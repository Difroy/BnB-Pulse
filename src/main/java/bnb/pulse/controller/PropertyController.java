package bnb.pulse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bnb.pulse.model.Property;
import bnb.pulse.model.Room;
import bnb.pulse.service.PropertyService;
import bnb.pulse.service.RoomService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping
	public String getPage(Model model, 
				@RequestParam(name = "photoId", required = false) Integer id, 
				@RequestParam(name = "room", required = false) Integer room, @RequestParam(name = "search", required = false) String search) {
		
		
		List<Property> properties;
		if(search != null) {
            properties = propertyService.SearchPropertyByCity(search);
            }else {
            	properties = propertyService.getProperties();
           
            }
		model.addAttribute("properties ",properties);
		model.addAttribute("property", new Property());
		
		return "property";
	}
	
	/*
	 * @GetMapping("/{id}") public String getProperty(Model
	 * model, @RequestParam(name = "id", required = false) Integer id) { Property
	 * property = propertieService.getPropertieById(id);
	 * model.addAttribute("property", property); return "property"; }
	 */
	@GetMapping("/{id}")
    public String getPropertyDetails(@PathVariable("id") int id, Model model) {
        Property property = propertyService.getPropertyById(id);
        List<Room> rooms = roomService.getRoomsByProperties_Id(id);
        model.addAttribute("property", property);
        model.addAttribute("rooms", rooms);
        return "propertyDetails";
    }
	
	@GetMapping("/room/{roomId}")
    public String getRoomDetails(@PathVariable("roomId") int roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        return "roomDetails";
    }
	
	@GetMapping("register")
	public String showRegisterPropertyForm(Model model) {
	    model.addAttribute("property", new Property());
	    return "registerProperty";
	}
	
	@PostMapping("register")
	public String registerProperty(@ModelAttribute Property property, HttpSession session, @RequestParam("coverPhoto") MultipartFile coverPhoto) {
	    propertyService.saveProperty(property, session, coverPhoto, property.getTitle(), property.getDescription(), property.getAddress(), property.getArea(), property.getCity(), property.getCountry(),  String.valueOf(property.getPricePerNight()), String.valueOf(property.getMaxGuest()));
	    return "redirect:/property";
	}
	
}
