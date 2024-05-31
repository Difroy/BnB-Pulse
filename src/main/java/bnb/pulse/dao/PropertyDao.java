package bnb.pulse.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bnb.pulse.model.Property;

public interface PropertyDao extends CrudRepository<Property, Integer>{

	
    List<Property> findByTitle(String title);
    List<Property> findByCity(String city);

}