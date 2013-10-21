package nl.moukail.streamtv.controller;

import java.util.Map;

import javax.validation.Valid;

import nl.moukail.streamtv.entity.Language;
import nl.moukail.streamtv.service.LanguageService;
import nl.moukail.streamtv.validator.LanguageValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/language")
public class LanguageController {

	@Autowired
	private LanguageService languageService;
	
	@Autowired
    private LanguageValidator languageValidator;
	
	@RequestMapping("/index")
	public String listLanguages(SitePreference sitePreference, Map<String, Object> map) {
		
		map.put("language", new Language());
        map.put("languageList", languageService.listLanguage());
		
        if (sitePreference == SitePreference.MOBILE) {
			return "language-mobile";
		} else {
			return "language";
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addLanguage(@ModelAttribute("language") @Valid Language language, 
			BindingResult result, Map<String, Object> map, SitePreference sitePreference) {
		
		map.put("languageList", languageService.listLanguage());
		languageValidator.validate(language, result);

        if (result.hasErrors()) {
        	
        	if (sitePreference == SitePreference.MOBILE) {
    			return "language-mobile";
    		} else {
    			return "language";
    		}
		}

        languageService.addLanguage(language);

		return "redirect:/language/index";
	}
	
	@RequestMapping("/delete/{languageId}")
    public String deleteLanguage(@PathVariable("languageId") Integer languageId) {
 
		languageService.removeLanguage(languageId);
 
        return "redirect:/language/index";
    }
}
