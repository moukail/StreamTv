package nl.moukail.streamtv.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.moukail.streamtv.entity.Video;

@Repository
public class VideoDAOImpl implements VideoDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger log = LogManager.getRootLogger();
	
	private Session getSession(){
        return sessionFactory.getCurrentSession();
    }
	
	public void addVideo(Video video) {
		sessionFactory.getCurrentSession().save(video);
	}

	public List<Video> listVideo() {
		return sessionFactory.getCurrentSession().createQuery("from Video").list();
	}

	public void removeVideo(Integer id) {
		Video video = (Video) sessionFactory.getCurrentSession().get(Video.class, id);
		if (null != video) {
			sessionFactory.getCurrentSession().delete(video);
		}
	}

	public Video get(Integer id) {
		return (Video) sessionFactory.getCurrentSession().get(Video.class, id);
	}

	public void updateVideo(Video video) {
		sessionFactory.getCurrentSession().update(video);
	}
}
