package bnb.pulse.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bnb.pulse.dao.BookingDao;
import bnb.pulse.dao.PropertyDao;
import bnb.pulse.dao.UserDao;
import bnb.pulse.model.Booking;
import bnb.pulse.model.Property;
import bnb.pulse.model.User;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private PropertyDao propertyDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Booking createBooking(int propertyId, int userId, LocalDate checkinDate, LocalDate checkoutDate) {
        Property property = propertyDao.findById(propertyId).orElse(null);
        User user = userDao.findById(userId).orElse(null);
        if (property == null || user == null || !isPropertyAvailable(propertyId, checkinDate, checkoutDate)) {
            return null;
        }

        Booking booking = new Booking();
        booking.setProperty(property);
        booking.setUser(user);
        booking.setCheckIn(checkinDate);
        booking.setCheckOut(checkoutDate);
        return bookingDao.save(booking);
    }

    @Override
    public List<Booking> findBookingsByPropertyId(int propertyId) {
        return bookingDao.findByPropertyId(propertyId);
    }

    @Override
    public boolean isPropertyAvailable(int propertyId, LocalDate checkinDate, LocalDate checkoutDate) {
        List<Booking> bookings = bookingDao.findByPropertyId(propertyId);
        for (Booking booking : bookings) {
            if (!(checkoutDate.isBefore(booking.getCheckIn()) || checkinDate.isAfter(booking.getCheckOut()))) {
                return false;
            }
        }
        return true;
    }
}
