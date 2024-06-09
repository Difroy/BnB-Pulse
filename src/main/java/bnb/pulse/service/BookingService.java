package bnb.pulse.service;
import java.time.LocalDate;
	import java.util.List;
	import bnb.pulse.model.Booking;
public interface BookingService {

	

	
	   Booking createBooking(int propertyId, int userId, LocalDate checkinDate, LocalDate checkoutDate);
	    List<Booking> findBookingsByPropertyId(int propertyId);
	    boolean isPropertyAvailable(int propertyId, LocalDate checkinDate, LocalDate checkoutDate);
	
	
	
}