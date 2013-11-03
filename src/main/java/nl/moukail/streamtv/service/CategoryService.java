package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Category;

public interface CategoryService {
	void addCategory(Category category);
	List<Category> listCategory();
	void removeCategory(Integer id);
	Category get(int id);
}
