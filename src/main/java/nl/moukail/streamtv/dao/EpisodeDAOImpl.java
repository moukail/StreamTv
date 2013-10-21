package nl.moukail.streamtv.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.moukail.streamtv.entity.Episode;

@Repository
public class EpisodeDAOImpl implements EpisodeDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public Episode get(Integer id) {
		return (Episode) sessionFactory.getCurrentSession().load(Episode.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Episode> listEpisode(int vodId) {
		return sessionFactory.getCurrentSession().createQuery("from Episode where vod_id = "+ vodId).list();
	}

	public void addEpisode(Episode episode) {
		sessionFactory.getCurrentSession().save(episode);
	}

	public void updateEpisode(Episode episode) {
		sessionFactory.getCurrentSession().update(episode);
	}

	public void removeEpisode(Integer id) {
		Episode episode = (Episode) sessionFactory.getCurrentSession().load(Episode.class, id);
		if (null != episode) {
			sessionFactory.getCurrentSession().delete(episode);
		}
	}

}
