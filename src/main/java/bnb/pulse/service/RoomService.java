package bnb.pulse.service;

import java.util.List;

import bnb.pulse.model.Room;

public interface RoomService {
	List<Room> getRooms();

	Room getRoomById(int id);
}
