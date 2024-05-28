package bnb.pulse.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import bnb.pulse.model.User;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public boolean loginUtente(String username, String Password, HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registraUtente(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean usernameCheck(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean emailCheck(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void editUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
