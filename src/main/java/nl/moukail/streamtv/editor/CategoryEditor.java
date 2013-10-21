package nl.moukail.streamtv.editor;

import java.beans.PropertyEditorSupport;

import nl.moukail.streamtv.entity.Category;
import nl.moukail.streamtv.service.CategoryService;

public class CategoryEditor extends PropertyEditorSupport {

	private final CategoryService categoryService;
	 
    public CategoryEditor(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
 
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Category category = categoryService.get(Integer.parseInt(text));
        setValue(category);
	}
	
}
