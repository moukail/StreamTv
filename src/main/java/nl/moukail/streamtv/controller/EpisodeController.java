package nl.moukail.streamtv.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import nl.moukail.streamtv.entity.Episode;
import nl.moukail.streamtv.entity.Vod;
import nl.moukail.streamtv.service.EpisodeService;
import nl.moukail.streamtv.validator.EpisodeValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/episode")
public class EpisodeController {
	
	@Autowired
	private EpisodeService episodeService;
	
	@Autowired
    private EpisodeValidator episodeValidator;
	
	private Vod vod;
	
	@RequestMapping("/index/{vodId}")
	public String listEpisodes(@PathVariable("vodId") Integer vodId, 
			Map<String, Object> map, SitePreference sitePreference) {
		
		this.vod = new Vod();
		this.vod.setId(vodId);
		List<Episode> list= episodeService.listEpisode(vodId);
		
		map.put("episode", new Episode());
		
		if(!list.isEmpty()){
			//map.put("streamfile", list.get(0).getStreamfile());
			//map.put("name", list.get(0).getName());
			//map.put("player", list.get(0).getPlayer());
			map.put("episodeList", list);
		}
		
        if (sitePreference == SitePreference.MOBILE) {
			return "episode-mobile";
		} else {
			return "episode";
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addEpisode(@ModelAttribute("episode") @Valid Episode episode, BindingResult result, 
			Map<String, Object> map, HttpServletRequest request, SitePreference sitePreference) {
		
		map.put("episodeList", episodeService.listEpisode(vod.getId()));
		episode.setVod(vod);
		episodeValidator.validate(episode, result);

        if (result.hasErrors()) {
        	
        	if (sitePreference == SitePreference.MOBILE) {
    			return "episode-mobile";
    		} else {
    			return "episode";
    		}
		}
		
		episodeService.addEpisode(episode);
		
		return "redirect:/episode/index/"+vod.getId();
	}
	
	@RequestMapping("/delete/{episodeId}")
    public String deleteEpisode(@PathVariable("episodeId")
    Integer episodeId) {
 
		episodeService.removeEpisode(episodeId);
 
        return "redirect:/episode/index/"+vod.getId();
    }
}
