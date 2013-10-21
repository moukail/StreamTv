package nl.moukail.streamtv.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.moukail.streamtv.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger log = LogManager.getRootLogger();
	
	private Session getSession(){
        return sessionFactory.getCurrentSession();
    }
	
	public void addUser(User user) {
		log.warn("Warning USER DAO");
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> listUser() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public void removeUser(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);
		}
	}

	public User get(int id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	public User findUserByEmailAndActivationKey(String email, String activationKey) {
		return (User) getSession().getNamedQuery("User.byEmailAndActivationKey")
				.setString("email", email)
				.setString("activationKey", activationKey)
				.uniqueResult();
	}
	
	public boolean usernameExist(User user) {
		User result = (User) getSession().getNamedQuery("User.byUsername")
				.setString("username", user.getUsername())
				.uniqueResult();
		if(result != null){
			return true;
		}else{
			return false;
		}
	}

	public boolean emailExist(User user) {
		User result = (User) getSession().getNamedQuery("User.byEmail")
				.setString("email", user.getEmail())
				.uniqueResult();
		if(result != null){
			return true;
		}else{
			return false;
		}
	}
	
	public void activate(User user) {
		user.setEnabled(true);
		user.setRole("user");
		//user.setActivationDate(new Date());
		sessionFactory.getCurrentSession().merge(user);
	}

	public User sendPassword(String email, String newPassword) {
		
		User user = (User) getSession().getNamedQuery("User.byEmail")
				.setString("email", email)
				.uniqueResult();
		if(user != null){
			user.setPassword(newPassword);
			sessionFactory.getCurrentSession().merge(user);
		}
		return user;
	}
}