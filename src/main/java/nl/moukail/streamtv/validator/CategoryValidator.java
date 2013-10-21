package nl.moukail.streamtv.validator;

import java.util.List;

import nl.moukail.streamtv.entity.Category;
import nl.moukail.streamtv.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {
	
	@Autowired
	private CategoryService categoryService;

	public boolean supports(Class<?> clazz) {
		return Category.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Category category = (Category) target;
		List<Category> list = categoryService.listCategory();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryName", "field.required");
		
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getCategoryName().equals(category.getCategoryName())){
				errors.rejectValue("categoryName", "field.duplicate");
				break;
			}
		}
	}
}