package nl.moukail.streamtv.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.moukail.streamtv.entity.Stream;

@Repository
public class StreamDAOImpl implements StreamDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addStream(Stream stream) {
		sessionFactory.getCurrentSession().save(stream);
	}

	@SuppressWarnings("unchecked")
	public List<Stream> listStream(int channelId) {
		return sessionFactory.getCurrentSession().createQuery("from Stream where channel_id = "+ channelId).list();
	}

	public void updateStream(Stream stream) {
		sessionFactory.getCurrentSession().update(stream);
	}
	
	public void removeStream(Integer id) {
		Stream stream = (Stream) sessionFactory.getCurrentSession().load(Stream.class, id);
		if (null != stream) {
			sessionFactory.getCurrentSession().delete(stream);
		}
	}

	public Stream get(Integer id) {
		return (Stream) sessionFactory.getCurrentSession().load(Stream.class, id);
	}
}