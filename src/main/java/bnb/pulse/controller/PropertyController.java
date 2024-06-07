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
import bnb.pulse.model.User;
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
	    public String getPage(Model model, HttpSession session) {
	        User currentUser = (User) session.getAttribute("user");
	        if (currentUser == null) {
	            return "redirect:/login";
	        }
		
	        List<Property> properties = propertyService.getPropertyByName(currentUser.getUsername());
	        model.addAttribute("properties", properties);
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
	public String registerProperty( @RequestParam("coverPhoto") MultipartFile coverPhoto,@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("address") String address, @RequestParam("area") String area, @RequestParam("city") String city, @RequestParam("country") String country, @RequestParam("pricePerNight") String pricePerNight, @RequestParam("maxGuest") String maxGuest, HttpSession session, Model model) {
		
		 Property property = new Property();
		    property.setTitle(title);
		    property.setDescription(description);
		    property.setAddress(address);
		    property.setArea(area);
		    property.setCity(city);
		    property.setCountry(country);
		    property.setPricePerNight(Double.parseDouble(pricePerNight));
		    property.setMaxGuest(Integer.parseInt(maxGuest));
		    
		    User currentUser = (User) session.getAttribute("user");
		    if (currentUser != null) {
		        property.setUser(currentUser);
		    } else {
		        // Gestisci il caso in cui l'utente non è loggato
		        return "redirect:/login"; // o una pagina di errore appropriata
		    }
		
		propertyService.saveProperty(property, session,coverPhoto, title, description, address, area, city, country, pricePerNight, maxGuest);
	    return "redirect:/property";
	}
	
	@GetMapping("/edit/{id}")
    public String showEditPropertyForm(@PathVariable("id") int id, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        Property property = propertyService.getPropertyById(id);
        if (property == null || property.getUser().getId() != currentUser.getId()) {
            return "redirect:/property";
        }
       
        model.addAttribute("property", property);
        return "editProperty";
    }
	
	@PostMapping("/edit/{id}")
	public String editProperty(
			@PathVariable("id") int id,
			 @RequestParam(value = "coverPhoto", required = false) MultipartFile coverPhoto,
			@ModelAttribute("property") Property property,
            HttpSession session) {
	
		 User currentUser = (User) session.getAttribute("user");
	        if (currentUser == null) {
	            return "redirect:/login";
	
	}
	        
	        Property existingProperty = propertyService.getPropertyById(id);
	        if (existingProperty == null || existingProperty.getUser().getId() != currentUser.getId()) {
	            return "redirect:/property";
	        }
	        
	        property.setId(id);
	        property.setUser(currentUser);
	        
	        propertyService.saveProperty(property, session, coverPhoto, property.getTitle(), property.getDescription(),
	                property.getAddress(), property.getArea(), property.getCity(), property.getCountry(),
	                String.valueOf(property.getPricePerNight()), String.valueOf(property.getMaxGuest()));
	        return "redirect:/property";
	}
	
	@GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable("id") int id, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        Property property = propertyService.getPropertyById(id);
        if (property == null || property.getUser().getId() != currentUser.getId()) {
            return "redirect:/property";
        }

        propertyService.deleteProperty(id);
        return "redirect:/property";
    }
	
	@GetMapping("/userarea")
	public String getUserArea(Model model, HttpSession session) {
	    User currentUser = (User) session.getAttribute("user");
	    if (currentUser == null) {
	        return "redirect:/login";
	    }
	    
	    List<Property> userProperties = propertyService.getPropertiesByUser(currentUser);
	    model.addAttribute("properties", userProperties);
	    
	    return "userarea";
	}
	

}
