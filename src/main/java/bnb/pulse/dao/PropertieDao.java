package bnb.pulse.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bnb.pulse.model.Propertie;

public interface PropertieDao extends CrudRepository<Propertie, Integer>{

	
    List<Propertie> findByTitle(String title);
    List<Propertie> findByCity(String city);

}
