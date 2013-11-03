package nl.moukail.streamtv.service;

import java.util.ArrayList;
import java.util.List;

import nl.moukail.streamtv.entity.Channel;
import nl.moukail.streamtv.entity.User;

public interface UserService {
	void addUser(User user);
	List<User> listUser();
	void removeUser(Integer id);
	User findUserByEmailAndActivationKey(String email, String activationKey);
	boolean usernameExist(User user);
	boolean emailExist(User user);
	void activate(User user);
	User get(int id);
	ArrayList<Channel> getChannels();
	void sendChannels();
	User sendPassword(String string);
}