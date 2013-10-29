package nl.moukail.streamtv.controller;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(SitePreference sitePreference, Model model) {
		if (sitePreference == SitePreference.MOBILE) {
			return "home-mobile";
			} else {
			return "home";
			}
	}
}
