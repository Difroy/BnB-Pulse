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
	double getPriceReserve (HttpSession session);
	void saveProperty (Property propertie, int idUser, MultipartFile coverPhoto, String title, String description, String address, String area, String city, String country, String pricePerNight, String maxGuest );
	void deletePropertie (int id);
	List<Property> getPropertyByName(String name);
	List<Property> getPropertyByPhotoId (int idPhoto);
	List<Property> SearchPropertyByCity(String city);
	
}
