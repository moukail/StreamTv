package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Category;

public interface CategoryDAO {

	void addCategory(Category category);
	List<Category> listCategory();
	void removeCategory(Integer id);
	Category get(int id);
	
}