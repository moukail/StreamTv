package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.User;

public interface UserDAO {

	public void addUser(User user);
	public List<User> listUser();
	public void removeUser(Integer id);
	public User get(int id);
	public User findUserByEmailAndActivationKey(String email, String activationKey);
	public void activate(User user);
	public User sendPassword(String email, String newPassword);
	public boolean usernameExist(User user);
	public boolean emailExist(User user);
}
