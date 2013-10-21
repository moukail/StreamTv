package nl.moukail.streamtv.validator;

import java.util.List;

import nl.moukail.streamtv.entity.Channel;
import nl.moukail.streamtv.service.ChannelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RadioValidator implements Validator {

	@Autowired
	@Qualifier("RadioServiceImpl")
	private ChannelService channelService;
	
	public boolean supports(Class<?> clazz) {
		return Channel.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {

		Channel channel = (Channel) target;
		List<Channel> list = channelService.listChannel();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "channelName", "field.required");
		
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getChannelName().equals(channel.getChannelName())){
				errors.rejectValue("channelName", "field.duplicate");
				break;
			}
		}
		
	}
}
