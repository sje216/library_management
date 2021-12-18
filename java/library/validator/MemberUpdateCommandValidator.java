package library.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import library.vo.MemberUpdateCommand;

public class MemberUpdateCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberUpdateCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors err) {
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "pw", "required");
	}

}
