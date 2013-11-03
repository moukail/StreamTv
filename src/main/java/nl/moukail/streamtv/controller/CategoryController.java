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

/**
 * @author Ismail
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    /**
     *
     */
    @Autowired
    private CategoryService categoryService;

    /**
     *
     */
    @Autowired
    private CategoryValidator categoryValidator;

    /**
     * @param sitePreference sitePreference
     * @param map map
     * @return string
     */
    @RequestMapping("/index")
    public final String listCategories(
        final SitePreference sitePreference,
        final Map<String, Object> map
    ) {

        map.put("category", new Category());
        map.put("categoryList", categoryService.listCategory());

        if (sitePreference == SitePreference.MOBILE) {
            return "category-mobile";
        } else {
            return "category";
        }
    }

    /**
     * @param category category
     * @param result result
     * @param map map
     * @param sitePreference sitePreference
     * @return string
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public final String addCategory(
        @ModelAttribute("category") @Valid final Category category,
        final BindingResult result,
        final Map<String, Object> map,
        final SitePreference sitePreference
    ) {
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

    /**
     * @param categoryId Category id
     * @return string
     */
    @RequestMapping("/delete/{categoryId}")
    public final String deleteCategory(
        @PathVariable("categoryId") final Integer categoryId
    ) {
        categoryService.removeCategory(categoryId);
        return "redirect:/category/index";
    }
}
