package nl.moukail.streamtv.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import nl.moukail.streamtv.entity.Channel;

@Repository
@Qualifier("RadioDAOImpl")
public class RadioDAOImpl implements ChannelDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Channel> listChannel() {
		return sessionFactory.getCurrentSession().createQuery("from Channel where type = 'RADIO'").list();
	}
	
	public void addChannel(Channel channel) {
		sessionFactory.getCurrentSession().save(channel);
	}

	public void removeChannel(Integer id) {
		Channel channel = get(id);
		if (null != channel) {
			sessionFactory.getCurrentSession().delete(channel);
		}
	}

	public Channel get(Integer id) {
		return (Channel) sessionFactory.getCurrentSession().get(Channel.class, id);
	}
}
