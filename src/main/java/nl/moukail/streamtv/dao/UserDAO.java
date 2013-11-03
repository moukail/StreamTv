package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.User;

public interface UserDAO {

	void addUser(User user);
	List<User> listUser();
	void removeUser(Integer id);
	User get(int id);
	User findUserByEmailAndActivationKey(String email, String activationKey);
	void activate(User user);
	User sendPassword(String email, String newPassword);
	boolean usernameExist(User user);
	boolean emailExist(User user);
}
