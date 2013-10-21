package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Category;

public interface CategoryDAO {

	public void addCategory(Category category);
	public List<Category> listCategory();
	public void removeCategory(Integer id);
	public Category get(int id);
	
}