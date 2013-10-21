package nl.moukail.streamtv.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import nl.moukail.streamtv.editor.CategoryEditor;
import nl.moukail.streamtv.editor.LanguageEditor;
import nl.moukail.streamtv.entity.Category;
import nl.moukail.streamtv.entity.Film;
import nl.moukail.streamtv.entity.Language;
import nl.moukail.streamtv.service.CategoryService;
import nl.moukail.streamtv.service.LanguageService;
import nl.moukail.streamtv.service.VodService;
import nl.moukail.streamtv.validator.VodValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/film")
public class FilmController {

	@Autowired
	@Qualifier("FilmServiceImpl")
	private VodService vodService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
    private VodValidator vodValidator;
	
	//private static final Logger logger = LoggerFactory.getLogger(TvController.class);
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Category.class, new CategoryEditor(this.categoryService));
		binder.registerCustomEditor(Language.class, new LanguageEditor(this.languageService));
    }
	
	@RequestMapping("/index")
	public String listFilms(SitePreference sitePreference, Map<String, Object> map) {
		
		map.put("vod", new Film());
		map.put("categoryList", categoryService.listCategory());
		map.put("languageList", languageService.listLanguage());
        map.put("vodList", vodService.listVod());
		
        if (sitePreference == SitePreference.MOBILE) {
			return "vod-mobile";
		} else {
			return "vod";
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addFilm(@Valid @ModelAttribute("vod") Film film, BindingResult result, 
			Map<String, Object> map, HttpServletRequest request, SitePreference sitePreference) {
		
		film.setCategory(categoryService.get(Integer.parseInt(request.getParameter("category"))));
		film.setLanguage(languageService.get(Integer.parseInt(request.getParameter("language"))));
        
        vodValidator.validate(film, result);
        
        if (result.hasErrors()) {
        	
        	map.put("categoryList", categoryService.listCategory());
        	map.put("languageList", languageService.listLanguage());
        	map.put("vodList", vodService.listVod());
        	
        	if (sitePreference == SitePreference.MOBILE) {
        		return "vod-mobile";
    		} else {
    			return "vod";
    		}
		}
        
		vodService.addVod(film);

		return "redirect:/film/index";
	}
	
	@RequestMapping("/delete/{vodId}")
    public String deleteFilm(@PathVariable("vodId") Integer vodId) {
 
		vodService.removeVod(vodId);
 
        return "redirect:/film/index";
    }
}
