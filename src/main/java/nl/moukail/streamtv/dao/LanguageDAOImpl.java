package nl.moukail.streamtv.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.moukail.streamtv.entity.Language;

@Repository
public class LanguageDAOImpl implements LanguageDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addLanguage(Language language) {
		sessionFactory.getCurrentSession().save(language);
	}

	@SuppressWarnings("unchecked")
	public List<Language> listLanguage() {
		return sessionFactory.getCurrentSession().createQuery("from Language").list();
	}

	public void removeLanguage(Integer id) {
		Language language = (Language) sessionFactory.getCurrentSession().get(Language.class, id);
		if (null != language) {
			sessionFactory.getCurrentSession().delete(language);
		}
	}

	public Language get(int id) {
		return (Language) sessionFactory.getCurrentSession().createQuery("from Language where id = " + id).list().get(0);
	}
}
