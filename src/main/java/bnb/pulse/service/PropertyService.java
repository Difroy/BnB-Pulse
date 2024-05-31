package bnb.pulse.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import bnb.pulse.model.Property;
import jakarta.servlet.http.HttpSession;

public interface PropertyService {

	List<Property>getProperties();
	Property getPropertyById(int id);
	boolean reserve (int idPropertie, HttpSession session);
	List<Property> getReserve (HttpSession session);
	void deleteReserve (int idPropertie,HttpSession session);
	double calculateTotalPriceForProperty(int propertyId, int numberOfNights);
	/* double getPriceReserve (HttpSession session); */
	void saveProperty (Property property, HttpSession session, MultipartFile coverPhoto, String title, String description, String address, String area, String city, String country, String pricePerNight, String maxGuest );
	void deletePropertie (int id);
	List<Property> getPropertyByName(String name);
	
	List<Property> SearchPropertyByCity(String city);
	
	
}
