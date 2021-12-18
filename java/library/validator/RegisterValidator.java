package library.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import library.vo.Register;

public class RegisterValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Register.class.isAssignableFrom(clazz);
	}
	
	private Pattern pattern;
	
	public RegisterValidator() {
		String regex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";// 규격화된 형식
		pattern = Pattern.compile(regex);
	}

	@Override
	public void validate(Object target, Errors err) {
		Register reg = (Register)target;
		if(reg.getEmail()==null || reg.getEmail().trim().isEmpty()) {
			err.rejectValue("email", "required");
		}else {
			Matcher matcher = pattern.matcher(reg.getEmail());
			if(!matcher.matches()) {
				err.rejectValue("email", "bad");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "pw", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "confirmPw", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "birthday", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "call", "required");
		
		if(!reg.getPw().isEmpty()) {
			if(!reg.isPwEqualsConfirmPw()) {
				err.rejectValue("confirmPw", "nomatch");
			}
		}
	}

}
