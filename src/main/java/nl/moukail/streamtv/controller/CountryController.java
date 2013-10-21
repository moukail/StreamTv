package nl.moukail.streamtv.controller;

import java.util.Map;

import javax.validation.Valid;

import nl.moukail.streamtv.entity.Country;
import nl.moukail.streamtv.service.CountryService;
import nl.moukail.streamtv.validator.CountryValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@Autowired
    private CountryValidator countryValidator;
	
	@RequestMapping("/index")
	public String listCountries(SitePreference sitePreference, Map<String, Object> map) {
		
		map.put("country", new Country());
        map.put("countryList", countryService.listCountry());
		
        if (sitePreference == SitePreference.MOBILE) {
			return "country-mobile";
		} else {
			return "country";
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCountry(@ModelAttribute("country") @Valid Country country, 
			BindingResult result, Map<String, Object> map, SitePreference sitePreference) {
		
        map.put("countryList", countryService.listCountry());
        countryValidator.validate(country, result);

        if (result.hasErrors()) {
        	if (sitePreference == SitePreference.MOBILE) {
    			return "country-mobile";
    		} else {
    			return "country";
    		}
		}

		countryService.addCountry(country);

		return "redirect:/country/index";
	}
	
	@RequestMapping("/delete/{countryId}")
    public String deleteCountry(@PathVariable("countryId") Integer countryId) {
 
		countryService.removeCountry(countryId);
 
        return "redirect:/country/index";
    }
	
	@RequestMapping("/channels/{countryId}")
	public String listChannels(SitePreference sitePreference,@PathVariable("countryId") Integer countryId, Map<String, Object> map) {
		
		//map.put("channels", countryService.get(countryId).getChannels().size());
		
        if (sitePreference == SitePreference.MOBILE) {
			return "channels-mobile";
		} else {
			return "channels";
		}
	}
}
