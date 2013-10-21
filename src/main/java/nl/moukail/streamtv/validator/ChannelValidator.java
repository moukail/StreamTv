package nl.moukail.streamtv.validator;

import nl.moukail.streamtv.entity.Channel;
import nl.moukail.streamtv.service.ChannelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ChannelValidator implements Validator {

	@Autowired
	@Qualifier("TvServiceImpl")
	private ChannelService tvService;
	
	@Autowired
	@Qualifier("RadioServiceImpl")
	private ChannelService radioService;
	
	public boolean supports(Class<?> clazz) {
		return Channel.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "channelName", "field.required");
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
