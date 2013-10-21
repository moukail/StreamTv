package nl.moukail.streamtv.validator;

import nl.moukail.streamtv.entity.User;
import nl.moukail.streamtv.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator{
	
	@Autowired
	private UserService userService;
	
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmpassword", "field.required");
		
		User user = (User) target;
		
		if(!user.getPassword().equals(user.getConfirmpassword())){
			errors.rejectValue("password", "user.field.password.incorrect");
		}
		
		if(userService.usernameExist(user)){
			errors.rejectValue("username", "user.field.username.exist");
		}
		
		if(userService.emailExist(user)){
			errors.rejectValue("email", "user.field.email.exist");
		}
	}

}
