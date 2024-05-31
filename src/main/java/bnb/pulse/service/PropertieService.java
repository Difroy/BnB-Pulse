package bnb.pulse.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import bnb.pulse.model.Propertie;
import jakarta.servlet.http.HttpSession;

public interface PropertieService {

	List<Propertie>getProperties();
	Propertie getPropertieById(int id);
	boolean reserve (int idPropertie, HttpSession session);
	List<Propertie> getReserve (HttpSession session);
	void deleteReserve (int idPropertie,HttpSession session);
	double getPriceReserve (HttpSession session);
	void savePropertie (Propertie propertie, int idUser, MultipartFile coverPhoto, String title, String description, String address, String area, String city, String country, String pricePerNight, String maxGuest );
	void deletePropertie (int id);
	List<Propertie> getPropertieByName(String name);
	List<Propertie> getPropertieByPhotoId (int idPhoto);
	List<Propertie> SearchPropertieByCity(String city);
	
}
