package nl.moukail.streamtv.controller;

import java.util.Map;

import javax.validation.Valid;

import nl.moukail.streamtv.entity.User;
import nl.moukail.streamtv.service.UserService;
import nl.moukail.streamtv.validator.UserValidator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;

	private static Logger log = LogManager.getRootLogger();
	
	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { 
			"username", "password", "confirmPassword",
			"firstname", "lastname", "email", "marketingOk", "acceptTerms"
		});
		
		// Converts empty string to null, which is nice since most validation rules fire only if the field isn't null.
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}*/
	
	@RequestMapping("/profile")
	public String profile(SitePreference sitePreference, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("user", new User());
		
		model.addAttribute("username", auth.getName());
		
		model.addAttribute("credentials", auth.getCredentials());
		
		model.addAttribute("details", auth.getDetails().toString());
		
		model.addAttribute("principal", auth.getPrincipal().toString());
		
		model.addAttribute("authorities", auth.getAuthorities().toString());
	    
	    if (sitePreference == SitePreference.MOBILE) {
			return "profile-mobile";
		} else {
			return "profile";
		}
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(SitePreference sitePreference, Model model){
		
		//log.debug("Debugging Signup");
	    //log.info("Informational Signup");
	    log.warn("Warning Signup");
		model.addAttribute("user", new User());
		if (sitePreference == SitePreference.MOBILE) {
			return "signup-mobile";
		} else {
			return "signup";
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") @Valid User user, 
			BindingResult result, SitePreference sitePreference, Map<String, Object> map) {
	    
		userValidator.validate(user, result);

        if (result.hasErrors()) {
        	return page(sitePreference, "signup");
		}
        
		userService.addUser(user);
		
		map.put("title", "Activation");
    	map.put("message", "Check your email and activate your account.");
    	return page(sitePreference, "thanks");
	}
	
	@RequestMapping(value="/activation", method = RequestMethod.GET)
	public String activate(SitePreference sitePreference, Model model, @RequestParam(value = "activate", required = true) String activationKey,@RequestParam(value = "email", required = true) String email) {
		User user = userService.findUserByEmailAndActivationKey(email, activationKey);
		if(null!=user){
			userService.activate(user);
			model.addAttribute("message", "Activation Success!");
			return page(sitePreference, "login");
		}else{
			model.addAttribute("title", "Activation Error");
			model.addAttribute("message", "Check your email and activate your account.");
			return page(sitePreference, "thanks");
		}
	}
	
	@RequestMapping("/login")
	public String login(SitePreference sitePreference, Model model, @RequestParam(required=false) String message) {
		model.addAttribute("message", message);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    model.addAttribute("username", name);
	    
	    if (sitePreference == SitePreference.MOBILE) {
			return "login-mobile";
		} else {
			return "login";
		}
	}
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotPassword(SitePreference sitePreference, Model model){

		model.addAttribute("user", new User());
		if (sitePreference == SitePreference.MOBILE) {
			return "forgotpassword-mobile";
		} else {
			return "forgotpassword";
		}
	}
	
	@RequestMapping(value = "/passwordrecovery", method = RequestMethod.POST)
	public String passwordrecovery(@ModelAttribute("user") @Valid User user, 
			BindingResult result, SitePreference sitePreference, Map<String, Object> map) {
		
		//userValidator.validate(user, result);
		
        if (result.hasErrors()) {
        	return page(sitePreference, "forgotpassword");
		}
        
        if(userService.sendPassword(user.getEmail()) != null){
        	map.put("title", "Password recovery");
        	map.put("message", "Your new password is sent to your email address.");
        	return page(sitePreference, "thanks");
        }else{
        	map.put("message", "Your email address is not found.");
        	return page(sitePreference, "forgotpassword");
        }
	}
	
	@RequestMapping("/thanks")
	public String denied(SitePreference sitePreference) {
		
		if (sitePreference == SitePreference.MOBILE) {
			return "thanks-mobile";
		} else {
			return "thanks";
		}
	}

	@RequestMapping("/loginfailed")
	public String loginFailure() {
		String message = "Login Failure!";
		return "redirect:/user/login?message="+message;
	}

	@RequestMapping("/logout")
	public String logoutSuccess() {
		String message = "Logout Success!";
		return "redirect:/user/login?message="+message;
	}
	
	private String page(SitePreference sitePreference, String name){
		if (sitePreference == SitePreference.MOBILE) {
			return name+"-mobile";
		} else {
			return name;
		}
	}
}