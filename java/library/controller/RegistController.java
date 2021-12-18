package library.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import library.exception.AlreadyMemberException;
import library.service.MasterRegisterService;
import library.service.MemberRegisterService;
import library.validator.RegisterValidator;
import library.vo.Register;

@Controller
@RequestMapping("/regist")
public class RegistController {

	@Inject
	BCryptPasswordEncoder pwdEncoder;

	
	private MemberRegisterService memberRegSvc;
	private MasterRegisterService masterRegSvc;

	public void setMemberRegSvc(MemberRegisterService memberRegSvc) {
		this.memberRegSvc = memberRegSvc;
	}

	public void setMasterRegSvc(MasterRegisterService masterRegSvc) {
		this.masterRegSvc = masterRegSvc;
	}

	@RequestMapping("/regist")
	public String regist(@ModelAttribute("reg") Register reg) {
		return "register/registerForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerPost(@ModelAttribute("reg") Register reg,HttpServletRequest request,Errors err) {
		ModelAndView mv = new ModelAndView();
		new RegisterValidator().validate(reg, err);
		int masterOrMember = Integer.parseInt(request.getParameter("masterOrMember"));
		if(err.hasErrors()) {
			mv.setViewName("/register/registerForm");
			return mv;
		}
		if (masterOrMember == 1) {
			// 관리자면 0 멤버면 1
			try {
//				String inputpass = reg.getPw();
//				String pw = pwdEncoder.encode(inputpass);
//				reg.setPw(pw);
				memberRegSvc.registMember(reg);
				mv.setViewName("redirect:/login");
			} catch (AlreadyMemberException e) {
				err.rejectValue("email", "duplicate");
				mv.setViewName("/register/registerForm");
				return mv;
			}
		} else if (masterOrMember == 0) {
			try {
				masterRegSvc.registMaster(reg);
				mv.setViewName("redirect:/login");

			} catch (AlreadyMemberException e) {
				err.rejectValue("email", "duplicate");
				mv.setViewName("/register/registerForm");
				return mv;
			}
		}

		return mv;
	}

	// get으로 회원가입 접근
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGet() {
		return "redirect:/login/login";
	}

}
