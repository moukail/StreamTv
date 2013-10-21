package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Vod;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("SerieDAOImpl")
public class SerieDAOImpl implements VodDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addVod(Vod vod) {
		sessionFactory.getCurrentSession().save(vod);
	}

	@SuppressWarnings("unchecked")
	public List<Vod> listVod() {
		return sessionFactory.getCurrentSession().createQuery("from Vod where type = 'Serie'").list();
	}

	public void removeVod(Integer id) {
		Vod vod = (Vod) sessionFactory.getCurrentSession().get(Vod.class, id);
		if (null != vod) {
			sessionFactory.getCurrentSession().delete(vod);
		}
	}

	public Vod get(Integer id) {
		return (Vod) sessionFactory.getCurrentSession().createQuery("from Vod where id = " + id).list().get(0);
	}

}
