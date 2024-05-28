package bnb.pulse.service;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bnb.pulse.dao.UserDao;
import bnb.pulse.model.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Override
	public boolean loginUtente(String username, String password, HttpSession session) {
			User user = userDao.findByUsernameAndPassword(username, password);
			if (user!=null) 
				session.setAttribute("user", user);
			return true;
	}
	@Override
	public void registraUtente(User user) {
		if(usernameCheck(user.getUsername())) {
			userDao.save(user);
		} else {
			throw new IllegalArgumentException ("Username già in uso");
		}
	}
	@Override
	public boolean usernameCheck(String username) {
		return userDao.findByUsername(username) == null;
	}
	@Override
	public boolean emailCheck(String email) {
		return userDao.findByEmail(email) == null;
	}
	@Override
	public void editUser(User user) {
		userDao.save(user);
	}
}
