package bnb.pulse.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bnb.pulse.model.Room;

public interface RoomDao extends CrudRepository<Room, Integer> {
	List<Room> findByProperties_Id(int propertieId);
}
