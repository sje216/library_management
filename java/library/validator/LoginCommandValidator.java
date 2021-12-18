package library.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import library.vo.LoginCommand;

public class LoginCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors err) {
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "id", "required");
		ValidationUtils.rejectIfEmpty(err, "pw", "required");
	}

}
