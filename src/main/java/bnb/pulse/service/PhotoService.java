package bnb.pulse.service;

import java.util.List;

import bnb.pulse.model.Photo;

public interface PhotoService {

	Photo getPhotoById (int idPhoto);
	List<Photo>getPhotos();
	void savePhoto (Photo photo);
	void deletePhoto (int idPhoto);
	Photo getPhotoByName(String name);
}
