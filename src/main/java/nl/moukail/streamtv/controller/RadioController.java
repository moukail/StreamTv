package nl.moukail.streamtv.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import nl.moukail.streamtv.editor.CategoryEditor;
import nl.moukail.streamtv.editor.CountryEditor;
import nl.moukail.streamtv.editor.LanguageEditor;
import nl.moukail.streamtv.entity.Category;
import nl.moukail.streamtv.entity.Channel;
import nl.moukail.streamtv.entity.Country;
import nl.moukail.streamtv.entity.Language;
import nl.moukail.streamtv.entity.Radio;
import nl.moukail.streamtv.service.CategoryService;
import nl.moukail.streamtv.service.ChannelService;
import nl.moukail.streamtv.service.CountryService;
import nl.moukail.streamtv.service.LanguageService;
import nl.moukail.streamtv.validator.ChannelValidator;

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
@RequestMapping("/radio")
public class RadioController {
	@Autowired
	@Qualifier("RadioServiceImpl")
	private ChannelService channelService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CategoryService categoryService;
	
	/*@Autowired
	private LanguageService languageService;*/
	
	@Autowired
	private ChannelValidator channelValidator;
	
	//private static final Logger logger = LoggerFactory.getLogger(RadioController.class);
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Country.class, new CountryEditor(this.countryService));
		binder.registerCustomEditor(Category.class, new CategoryEditor(this.categoryService));
		//binder.registerCustomEditor(Language.class, new LanguageEditor(this.languageService));
    }
	
	@RequestMapping("/index")
	public String listRadios(SitePreference sitePreference, Map<String, Object> map) {
		
		map.put("channel", new Radio());
		
		map.put("countryList", countryService.listCountry());
		map.put("categoryList", categoryService.listCategory());
		//map.put("languageList", languageService.listLanguage());
        map.put("channelList", channelService.listChannel());
        
        if (sitePreference == SitePreference.MOBILE) {
			return "channel-mobile";
		} else {
			return "channel";
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addRadio(@Valid @ModelAttribute("channel") Radio radio, BindingResult result, 
			Map<String, Object> map, HttpServletRequest request, SitePreference sitePreference) {
		
		radio.setCategory(categoryService.get(Integer.parseInt(request.getParameter("category"))));
        radio.setCountry(countryService.get(Integer.parseInt(request.getParameter("country"))));
        //radio.setLanguage(languageService.get(Integer.parseInt(request.getParameter("language"))));
        
        
        channelValidator.validate(radio, result);

        if (result.hasErrors()) {
        	
        	map.put("countryList", countryService.listCountry());
        	map.put("categoryList", categoryService.listCategory());
        	//map.put("languageList", languageService.listLanguage());
        	map.put("channelList", channelService.listChannel());
        	
        	if (sitePreference == SitePreference.MOBILE) {
    			return "channel-mobile";
    		} else {
    			return "channel";
    		}
		}
		
		channelService.addChannel(radio);

		return "redirect:/radio/index";
	}
	
	@RequestMapping("/streams/{radioId}")
	public String getStreams(@PathVariable("radioId") Integer radioId, Map<String, Object> map){
		
		Channel channel = channelService.get(radioId);
		map.put("streams", channel.getStreams());
		return "streams";
	}
	
	@RequestMapping("/delete/{radioId}")
    public String deleteRadio(@PathVariable("radioId") Integer radioId) {
 
		channelService.removeChannel(radioId);
 
        return "redirect:/radio/index";
    }
}
