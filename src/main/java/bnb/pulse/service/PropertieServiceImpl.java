package bnb.pulse.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import bnb.pulse.dao.PropertieDao;
import bnb.pulse.model.Propertie;
import jakarta.servlet.http.HttpSession;

@Service
public class PropertieServiceImpl implements PropertieService {

	@Autowired
	private PropertieDao propertieDao;
	@Autowired
	private RoomService roomService;

	@Override
	public List<Propertie> getProperties() {
		return (List<Propertie>)propertieDao.findAll();
	}
	@Override
	public Propertie getPropertieById(int id) {
		
		Optional<Propertie> optionalPropertie = propertieDao.findById(id);
		if (optionalPropertie.isPresent())
			return optionalPropertie.get();
		return null;
	}
	@Override
	public boolean reserve(int idPropertie, HttpSession session) {
		Propertie propertie = getPropertieById (idPropertie);
		List <Propertie> booking;
		if (session.getAttribute("Booking")!=null) {
			booking = (List <Propertie>)
		}
		return false;
	}
	@Override
	public List<Propertie> getReserve(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteReserve(int idPropertie, HttpSession session) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public double getPriceReserve(HttpSession session) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void savePropertie(Propertie propertie, int idUser, MultipartFile coverPhoto, String title,
			String description, String address, String area, String city, String country, String pricePerNight,
			String maxGuest) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deletePropertie(int id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Propertie> getPropertieByPhotoAndRoom(Integer photoId, Integer roomId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Propertie> SearchPropertie(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Propertie> getPropertiesByRoomId(int idRoom) {
		return (List<Propertie>) propertieDao.findByRoomId(idRoom);
	}
	@Override
	public List<Propertie> getPropertieByName(String name) {
		return (List<Propertie>) propertieDao.findByNomeContainingIgnoreCase(name);
	}
	@Override
	public List<Propertie> getPropertieByPhotoId(int idPhoto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
