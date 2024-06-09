package bnb.pulse.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import bnb.pulse.model.Booking;

public interface BookingDao extends CrudRepository<Booking, Integer> {
    List<Booking> findByPropertyId(int propertyId);
}