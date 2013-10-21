package nl.moukail.streamtv.service;

import java.util.ArrayList;
import java.util.List;

import nl.moukail.streamtv.entity.Channel;
import nl.moukail.streamtv.entity.User;

public interface UserService {
	public void addUser(User user);
	public List<User> listUser();
	public void removeUser(Integer id);
	public User findUserByEmailAndActivationKey(String email, String activationKey);
	public boolean usernameExist(User user);
	public boolean emailExist(User user);
	public void activate(User user);
	public User get(int id);
	public ArrayList<Channel> getChannels();
	public void sendChannels();
	public User sendPassword(String string);
}