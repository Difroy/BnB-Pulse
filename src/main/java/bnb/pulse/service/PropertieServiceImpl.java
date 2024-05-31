package bnb.pulse.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	@SuppressWarnings("unchecked")
	@Override
	public boolean reserve(int idPropertie, HttpSession session) {
		Propertie propertie = getPropertieById (idPropertie);
		List <Propertie> booking;
		
		if (session.getAttribute("booking")!=null) {
			booking = (List <Propertie>) session.getAttribute("reserve");
			for (Propertie p : booking)
				if (p.getId() == propertie.getId())
					return false;
		
		booking.add(propertie);
		session.setAttribute("booking", booking);
		return true;
		}else {
			booking = new ArrayList <>();
			booking.add(propertie);
			session.setAttribute("booking", booking);
			return true;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Propertie> getReserve(HttpSession session) {
		if (session.getAttribute("booking") != null) {
			return (List<Propertie>) session.getAttribute("booking");
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void deleteReserve(int idPropertie, HttpSession session) {

		List <Propertie> reserve = (List<Propertie>) session.getAttribute("reserve");
		for (Propertie p: reserve)
			if (p.getId()==idPropertie) {
				reserve.remove(p);
				break;
			}
		
		reserve = reserve.stream().filter(p -> p.getId() != idPropertie).collect(Collectors.toList());
		if (reserve.size()>0)
			session.setAttribute("reserve", reserve);
		else
			session.removeAttribute("booking");
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public double getPriceReserve(HttpSession session) {
		List <Propertie> reserve = (List<Propertie>) session.getAttribute("reserve");
		if (reserve == null) {
			
			@SuppressWarnings("null")
			double total = reserve.stream().mapToDouble(Propertie::getPricePerNight).reduce(0, Double::sum);
					return total;
		}
		return 0;
	}
	@Override
	public void savePropertie(Propertie propertie, int idUser, MultipartFile coverPhoto, String title,
			String description, String address, String area, String city, String country, String pricePerNight,
			String maxGuest) {
		
		
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
