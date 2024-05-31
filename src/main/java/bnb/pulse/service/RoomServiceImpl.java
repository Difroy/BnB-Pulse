package bnb.pulse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bnb.pulse.dao.RoomDao;
import bnb.pulse.model.Room;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;
	
	@Override
	public List<Room> getRooms() {
		return (List<Room>)roomDao.findAll();
	}

	@Override
	public Room getRoomById(int id) {
		return roomDao.findById(id).get();
	}

}
