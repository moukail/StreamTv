package nl.moukail.streamtv.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import nl.moukail.streamtv.entity.Channel;
import nl.moukail.streamtv.entity.Stream;
import nl.moukail.streamtv.service.CountryService;
import nl.moukail.streamtv.service.LanguageService;
import nl.moukail.streamtv.service.StreamService;
import nl.moukail.streamtv.validator.StreamValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StreamController {

	@Autowired
	private StreamService streamService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
    private StreamValidator streamValidator;
	
	private Channel channel;
	
	@RequestMapping("/stream/index/{channelId}")
	public String listStreams(@PathVariable("channelId") Integer channelId, 
			Map<String, Object> map, SitePreference sitePreference) {
		
		this.channel = new Channel();
		this.channel.setId(channelId);
		List<Stream> list= streamService.listStream(channelId);
		
		map.put("stream", new Stream());
		map.put("countryList", countryService.listCountry());
		map.put("languageList", languageService.listLanguage());
		if(!list.isEmpty()){
			map.put("streamfile", list.get(0).getStreamfile());
			//map.put("name", list.get(0).getName());
			map.put("player", list.get(0).getPlayer());
			map.put("streamList", list);
		}
		
        if (sitePreference == SitePreference.MOBILE) {
			return "stream-mobile";
		} else {
			return "stream";
		}
	}
	
	@RequestMapping("/stream/play/{streamId}")
	public String playStream(@PathVariable("streamId") Integer streamId, 
			Map<String, Object> map, SitePreference sitePreference) {
		
		map.put("stream", new Stream());
		map.put("playStream", streamService.get(streamId));
		
		map.put("streamfile", streamService.get(streamId).getStreamfile());
		//map.put("name", streamService.get(streamId).getName());
		
		List<Stream> list= streamService.listStream(this.channel.getId());
		if(!list.isEmpty()){
			map.put("streamList", list);
		}
		
        if (sitePreference == SitePreference.MOBILE) {
			return "play-mobile";
		} else {
			return "play";
		}
	}
	
	@RequestMapping(value = "/stream/add", method = RequestMethod.POST)
	public String addStream(@ModelAttribute("stream") @Valid Stream stream, BindingResult result, 
			Map<String, Object> map, HttpServletRequest request, SitePreference sitePreference) {
		
		map.put("countryList", countryService.listCountry());
		map.put("languageList", languageService.listLanguage());
		map.put("streamList", streamService.listStream(channel.getId()));
		stream.setChannel(channel);
        stream.setCountry(countryService.get(Integer.parseInt(request.getParameter("country"))));
		stream.setLanguage(languageService.get(Integer.parseInt(request.getParameter("language"))));
		streamValidator.validate(stream, result);

        if (result.hasErrors()) {
        	
        	if (sitePreference == SitePreference.MOBILE) {
    			return "stream-mobile";
    		} else {
    			return "stream";
    		}
		}
		
		streamService.addStream(stream);
		
		return "redirect:/stream/index/"+channel.getId();
	}
	
	@RequestMapping("/stream/delete/{streamId}")
    public String deleteStream(@PathVariable("streamId")
    Integer streamId) {
 
		streamService.removeStream(streamId);
 
        return "redirect:/stream/index/"+channel.getId();
    }
}
