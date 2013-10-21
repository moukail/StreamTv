package nl.moukail.streamtv.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.moukail.streamtv.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addCategory(Category category) {
		sessionFactory.getCurrentSession().save(category);
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> listCategory() {
		return sessionFactory.getCurrentSession().createQuery("from Category").list();
	}

	public void removeCategory(Integer id) {
		Category category = (Category) sessionFactory.getCurrentSession().get(Category.class, id);
		if (null != category) {
			sessionFactory.getCurrentSession().delete(category);
		}
	}

	public Category get(int id) {
		return (Category) sessionFactory.getCurrentSession().get(Category.class, id);
	}

}