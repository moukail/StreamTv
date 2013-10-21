package nl.moukail.streamtv.validator;

import java.util.List;

import nl.moukail.streamtv.entity.Language;
import nl.moukail.streamtv.service.LanguageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LanguageValidator implements Validator {
	
	@Autowired
	private LanguageService languageService;

	public boolean supports(Class<?> clazz) {
		return Language.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Language language = (Language)target;
		List<Language> list = languageService.listLanguage();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "languageName", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "languageCode", "field.required");
		
		if(language.getLanguageCode().length() != 3){
			errors.rejectValue("languageCode", "field.length.language.code");
		}
		
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getLanguageName().equals(language.getLanguageName())){
				errors.rejectValue("languageName", "field.duplicate");
				break;
			}
			if(list.get(i).getLanguageCode().equals(language.getLanguageCode())){
				errors.rejectValue("languageCode", "field.duplicate");
				break;
			}
		}
	}

}
