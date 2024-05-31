package bnb.pulse.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bnb.pulse.dao.PropertyDao;
import bnb.pulse.model.Property;
import bnb.pulse.model.User;
import jakarta.servlet.http.HttpSession;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyDao propertieDao;
	


	@Override
	public List<Property> getProperties() {
		return (List<Property>)propertieDao.findAll();
	}
	@Override
	public Property getPropertyById(int id) {
		
		
		Optional<Property> optionalPropertie = propertieDao.findById(id);
        return optionalPropertie.orElse(null);
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean reserve(int idPropertie, HttpSession session) {
		Property propertie = getPropertyById (idPropertie);
		List <Property> booking;
		
		if (session.getAttribute("booking")!=null) {
			booking = (List <Property>) session.getAttribute("reserve");
			for (Property p : booking)
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
	public List<Property> getReserve(HttpSession session) {
		if (session.getAttribute("booking") != null) {
			return (List<Property>) session.getAttribute("booking");
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void deleteReserve(int idPropertie, HttpSession session) {

		List <Property> reserve = (List<Property>) session.getAttribute("reserve");
		for (Property p: reserve)
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
	/*@SuppressWarnings("unchecked")
	
	 * @Override public double getPriceReserve(HttpSession session) { List
	 * <Property> reserve = (List<Property>) session.getAttribute("reserve"); if
	 * (reserve == null) {
	 * 
	 * @SuppressWarnings("null") double total =
	 * reserve.stream().mapToDouble(Property::getPricePerNight).reduce(0,
	 * Double::sum); return total; } return 0; }
	 */
	
	public double calculateTotalPriceForProperty(int propertyId, int numberOfNights) {
	    Property property = getPropertyById(propertyId);
	    return property.calculateTotalPrice(numberOfNights);
	}
	
	@Override
	public void saveProperty(Property property, HttpSession session, MultipartFile coverPhoto, String title,
			String description, String address, String area, String city, String country, String pricePerNight,
			String maxGuest) {
		
		User currentUser = (User) session.getAttribute("user");
		  property.setUser(currentUser);
		  property.setTitle(title);
		  property.setDescription(description);
		  property.setAddress(address);
		  property.setArea(area);
		  property.setCity(city);
		  property.setCountry(country);
		  property.setPricePerNight(Double.parseDouble(pricePerNight));
		  property.setMaxGuest(Integer.parseInt(maxGuest));
		  property.setCoverPhoto(coverPhoto.getOriginalFilename());
			if (coverPhoto != null && ! coverPhoto.isEmpty()) {
				
				try {
					String format = coverPhoto.getContentType();
					String cover = "data:"+format+";base64,"+Base64.getEncoder().encodeToString(coverPhoto.getBytes());
					
					property.setCoverPhoto(cover);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	
}
	@Override
	public void deletePropertie(int id) {
		 propertieDao.deleteById(id);
	}
	@Override
	public List<Property> getPropertyByName(String title) {
		return propertieDao.findByTitle(title);
	}
	
	@Override
	public List<Property> SearchPropertyByCity(String city) {
		return propertieDao.findByCity(city);
	}
}
