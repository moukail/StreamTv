package nl.moukail.streamtv.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import nl.moukail.streamtv.annotations.Weekly;
import nl.moukail.streamtv.dao.UserDAO;
import nl.moukail.streamtv.entity.Channel;
import nl.moukail.streamtv.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	private static ArrayList<Channel> channels = new ArrayList<Channel>();
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	public void addUser(User user) {
		logger.warn("Warning USER Service");
		
		Random random = new Random(System.currentTimeMillis());
        String activationKey = "activationKey:" + random.nextInt();
		
        String encodedPassword = passwordEncoder.encode(user.getPassword());
		
        user.setPassword(encodedPassword);
		user.setActivationKey(activationKey);
		
		user.setRegistrationDate(new Date());
		user.setEnabled(false);
        user.setLocked(false);
		userDAO.addUser(user);
		sendConfirmationEmail(user);
	}
	
	private void sendConfirmationEmail(final User user) {
	      MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setTo(user.getEmail());
	            message.setFrom("webmaster@moukail.nl");
	            message.setSubject("User Activaton");
	            Map model = new HashMap<String, User>();
	            model.put("user", user);
	            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "nl/moukail/streamtv/velocity/registration-confirmation.vm", model);
	            message.setText(text, true);
	         }
	      };
	      mailSender.send(preparator);
	}
	
	public ArrayList<Channel> getChannels() {
		return channels;
	}
	
	@Weekly
	public void sendChannels() {
		Date date= new Date();
		logger.info("test mail:" + new Timestamp(date.getTime()));
		if(!getChannels().isEmpty()){
			
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
		         public void prepare(MimeMessage mimeMessage) throws Exception {
		        	 
		        	 MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		        	 message.setTo(getEmails());
		        	 message.setFrom("webmaster@moukail.nl");
		        	 message.setSubject("New Channels");
		        	 Map model = new HashMap<String, ArrayList<Channel>>();
		        	 //model.put("user", getUser());
		        	 model.put("channels", getChannels());
		        	 String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "nl/moukail/streamtv/velocity/channels.vm", model);
		        	 message.setText(text, true);
		        	 
		         }
			};
			mailSender.send(preparator);
			
			getChannels().clear();
			logger.info("msg sent");
		}
	}
	
	protected String[] getEmails() {
		List<User> list = listUser();
		String[] emails = new String[list.size()];
		for(int i=0;i<emails.length;i++){
			emails[i]= list.get(i).getEmail();
			logger.info(emails[i]);
		}
		return emails;
	}

	public List<User> listUser() {
		return userDAO.listUser();
	}

	public void removeUser(Integer id) {
		userDAO.removeUser(id);
	}

	public User get(int id) {
		return userDAO.get(id);
	}

	public User findUserByEmailAndActivationKey(String email, String activationKey) {
		return userDAO.findUserByEmailAndActivationKey(email, activationKey);
	}
	
	public boolean usernameExist(User user){
		return userDAO.usernameExist(user);
	}
	
	public boolean emailExist(User user){
		return userDAO.emailExist(user);
	}
	
	public void activate(User user) {
		userDAO.activate(user);
	}

	public User sendPassword(String email) {
		
		Random random = new Random(System.currentTimeMillis());
    	String newPassword = "pass"+random.nextLong();
    	String encodedPassword = passwordEncoder.encode(newPassword);
    	
    	User user = userDAO.sendPassword(email,encodedPassword);
		if(user != null){
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom("i.moukafih@gmail.com");
    		mail.setTo(email);
    		mail.setSubject("Password Recover");
    		mail.setText("Hi "+user.getFirstname()+",\n. You had requested for password recovery. Your password is "+newPassword+". \n Thanks Tyical Security Admin");
    		mailSender.send(mail);
		}
		return user;
		
	}

}