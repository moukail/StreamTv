package nl.moukail.streamtv.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.moukail.streamtv.entity.Country;

@Repository
public class CountryDAOImpl implements CountryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addCountry(Country country) {
		sessionFactory.getCurrentSession().save(country);
	}

	@SuppressWarnings("unchecked")
	public List<Country> listCountry() {
		return sessionFactory.getCurrentSession().createQuery("from Country").list();
	}

	public void removeCountry(Integer id) {
		Country country = (Country) sessionFactory.getCurrentSession().load(Country.class, id);
		if (null != country) {
			sessionFactory.getCurrentSession().delete(country);
		}
	}

	public Country get(int id) {
		return (Country) sessionFactory.getCurrentSession().createQuery("from Country where id = " + id).list().get(0);
	}
}