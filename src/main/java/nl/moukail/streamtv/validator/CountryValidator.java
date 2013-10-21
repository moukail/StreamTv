package nl.moukail.streamtv.validator;

import java.util.List;

import nl.moukail.streamtv.entity.Country;
import nl.moukail.streamtv.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CountryValidator implements Validator {

	@Autowired
	private CountryService countryService;
	
	public boolean supports(Class<?> c) {
		return Country.class.isAssignableFrom(c);
	}
	
	public void validate(Object command, Errors errors) {
		Country country = (Country)command;
		List<Country> list = countryService.listCountry();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryName", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "field.required");
		
		if(country.getCountryCode().length() != 2){
			errors.rejectValue("countryCode", "field.length.country.code");
		}
		
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getCountryName().equals(country.getCountryName())){
				System.out.println("found: "+i);
				errors.rejectValue("countryName", "field.duplicate");
				break;
			}
			if(list.get(i).getCountryCode().equals(country.getCountryCode())){
				System.out.println("found: "+i);
				errors.rejectValue("countryCode", "field.duplicate");
				break;
			}
		}
	}
}
