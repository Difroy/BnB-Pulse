package bnb.pulse.dao;

import org.springframework.data.repository.CrudRepository;

import bnb.pulse.model.Photo;

public interface PhotoDao extends CrudRepository <Photo, Integer> {

	Photo findByUrl (String url);
}
