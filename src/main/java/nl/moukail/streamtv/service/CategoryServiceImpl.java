package nl.moukail.streamtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.moukail.streamtv.dao.CategoryDAO;
import nl.moukail.streamtv.entity.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDAO categoryDAO;
	
	public void addCategory(Category category) {
		categoryDAO.addCategory(category);
	}

	public List<Category> listCategory() {
		return categoryDAO.listCategory();
	}

	public void removeCategory(Integer id) {
		categoryDAO.removeCategory(id);
	}

	public Category get(int id) {
		return categoryDAO.get(id);
	}

}
