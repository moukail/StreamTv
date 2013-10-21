package nl.moukail.streamtv.validator;

import nl.moukail.streamtv.entity.Stream;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StreamValidator implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return Stream.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "streamfile", "field.required");
	}
}