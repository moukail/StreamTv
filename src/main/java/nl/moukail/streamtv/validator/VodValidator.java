package nl.moukail.streamtv.validator;

import nl.moukail.streamtv.entity.Vod;
import nl.moukail.streamtv.service.VodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class VodValidator implements Validator {

	@Autowired
	@Qualifier("FilmServiceImpl")
	private VodService filmService;
	
	@Autowired
	@Qualifier("SerieServiceImpl")
	private VodService serieService;
	
	public boolean supports(Class<?> clazz) {
		return Vod.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required");
		/*
		Channel channel = (Channel) target;
		
		List<Channel> tvList = tvService.listChannel();
		for(int i = 0; i<tvList.size(); i++){
			if(tvList.get(i).getChannelName().equals(channel.getChannelName())){
				errors.rejectValue("channelName", "field.duplicate");
				break;
			}
		}
		
		List<Channel> radioList = radioService.listChannel();
		for(int i = 0; i<radioList.size(); i++){
			if(radioList.get(i).getChannelName().equals(channel.getChannelName())){
				errors.rejectValue("channelName", "field.duplicate");
				break;
			}
		}
		*/
	}
}