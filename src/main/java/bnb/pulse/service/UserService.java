package bnb.pulse.service;

import bnb.pulse.model.User;
import jakarta.servlet.http.HttpSession;

public interface UserService {

	boolean loginUtente (String username, String password, HttpSession session);
	void userRegister (User user);
	boolean usernameCheck (String username);
	boolean emailCheck (String email);
	void editUser (User user);
	User getUserById(int id);
}
