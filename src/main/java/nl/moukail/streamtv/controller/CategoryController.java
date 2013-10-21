package nl.moukail.streamtv.controller;

import java.util.Map;

import javax.validation.Valid;

import nl.moukail.streamtv.entity.Category;
import nl.moukail.streamtv.service.CategoryService;
import nl.moukail.streamtv.validator.CategoryValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
    private CategoryValidator categoryValidator;
	
	@RequestMapping("/index")
	public String listCategories(SitePreference sitePreference, Map<String, Object> map) {
		
		map.put("category", new Category());
        map.put("categoryList", categoryService.listCategory());
        
        if (sitePreference == SitePreference.MOBILE) {
			return "category-mobile";
		} else {
			return "category";
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute("category") @Valid Category category, 
			BindingResult result, Map<String, Object> map, SitePreference sitePreference) {
		
		map.put("categoryList", categoryService.listCategory());
		categoryValidator.validate(category, result);

        if (result.hasErrors()) {
        	if (sitePreference == SitePreference.MOBILE) {
    			return "category-mobile";
    		} else {
    			return "category";
    		}
		}
		
		categoryService.addCategory(category);
		
		return "redirect:/category/index";
	}
	
	@RequestMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId")
    Integer categoryId) {
 
		categoryService.removeCategory(categoryId);
 
        return "redirect:/category/index";
    }
}
