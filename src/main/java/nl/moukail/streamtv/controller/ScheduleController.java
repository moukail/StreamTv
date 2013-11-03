package nl.moukail.streamtv.controller;

import java.util.Map;

import nl.moukail.streamtv.entity.Schedule;
import nl.moukail.streamtv.service.CategoryService;
import nl.moukail.streamtv.service.LanguageService;
import nl.moukail.streamtv.service.ScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LanguageService languageService;
	
	@RequestMapping("/index")
	public String listSchedules(SitePreference sitePreference, Map<String, Object> map) {
		
		map.put("schedule", new Schedule());
		map.put("categoryList", categoryService.listCategory());
		map.put("languageList", languageService.listLanguage());
        map.put("scheduleList", scheduleService.listSchedule());
		
        if (sitePreference == SitePreference.MOBILE) {
			return "schedule-mobile";
		} else {
			return "schedule";
		}
	}
	
}