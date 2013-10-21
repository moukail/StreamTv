package nl.moukail.streamtv.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import nl.moukail.streamtv.entity.Channel;

@Repository
@Qualifier("TvDAOImpl")
public class TvDAOImpl implements ChannelDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(TvDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<Channel> listChannel() {
		return sessionFactory.getCurrentSession().createQuery("from Channel where type = 'TV'").list();
	}
	
	public void addChannel(Channel channel) {
		sessionFactory.getCurrentSession().save(channel);
		logger.info("county:"+channel.getCountry().getCountryName());
	}

	public void removeChannel(Integer id) {
		
		Channel channel = (Channel) sessionFactory.getCurrentSession().get(Channel.class, id);
		if (null != channel) {
			sessionFactory.getCurrentSession().delete(channel);
		}
	}

	public Channel get(Integer id) {
		return (Channel) sessionFactory.getCurrentSession().createQuery("from Channel where id = " + id).list().get(0);
	}
}
