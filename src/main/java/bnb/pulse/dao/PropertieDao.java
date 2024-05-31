package bnb.pulse.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bnb.pulse.model.Propertie;

public interface PropertieDao extends CrudRepository<Propertie, Integer>{

	List<Propertie> findByRoomId (int idRoom);
	List<Propertie> findByRoomPhotoIdAndRoomId(Integer photoId, Integer roomId);
	/* List<Propertie> findByRoomPhotoId(int idPhoto); */
	List<Propertie> findByNomeContainingIgnoreCase (String name);
	

	
	
}
