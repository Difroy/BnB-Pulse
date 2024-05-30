package bnb.pulse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bnb.pulse.dao.PhotoDao;
import bnb.pulse.model.Photo;
@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoDao photoDao;
	
	
	@Override
	public Photo getPhotoById(int idPhoto) {
		return photoDao.findById(idPhoto).get();
	}

	@Override
	public List<Photo> getPhotos() {
		return (List<Photo>)photoDao.findAll();
	}

	@Override
	public void savePhoto(Photo photo) {
		photoDao.save(photo);
	}

	@Override
	public void deletePhoto(int idPhoto) {
		photoDao.deleteById(idPhoto);
	}

	@Override
	public Photo getPhotoByName(String name) {
		return photoDao.findByName(name);
	}

}
