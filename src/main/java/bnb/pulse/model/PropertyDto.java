package bnb.pulse.model;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

public class PropertyDto {
	private MultipartFile coverPhoto;
	public MultipartFile getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(MultipartFile coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	
}