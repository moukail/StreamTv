package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Category;

public interface CategoryService {
	public void addCategory(Category category);
	public List<Category> listCategory();
	public void removeCategory(Integer id);
	public Category get(int id);
}
