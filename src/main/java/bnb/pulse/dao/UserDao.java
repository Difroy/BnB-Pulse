package bnb.pulse.dao;

import org.springframework.data.repository.CrudRepository;

import bnb.pulse.model.User;
public interface UserDao extends CrudRepository<User, Integer> {

	User findByUsernameAndPassword(String username, String password);  
	User findByUsername(String username); 
	User findByEmail(String email);
	User findByPassword (String password);
	
}
