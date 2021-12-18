package library.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import library.dao.MasterDAO;
import library.dao.MemberDAO;
import library.exception.IdPwNotMatchingException;
import library.exception.MemberNotFoundException;
import library.service.MasterInfoService;
import library.service.MemberInfoService;
import library.validator.LoginCommandValidator;
import library.vo.LoginCommand;
import library.vo.MasterInfo;
import library.vo.MemberInfo;
import library.vo.Register;

@Controller
public class LoginController {
	
	private MemberInfoService memberInfoSvc;
	private MasterInfoService masterInfoSvc;
	
	public void setMemberInfoSvc(MemberInfoService memberInfoSvc) {
		this.memberInfoSvc = memberInfoSvc;
	}

	public void setMasterInfoSvc(MasterInfoService masterInfoSvc) {
		this.masterInfoSvc = masterInfoSvc;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(LoginCommand loginCommand,@CookieValue(value = "rememberId",required = false)Cookie rememberId) {
		if(rememberId!=null) {
			loginCommand.setId(rememberId.getValue());
			loginCommand.setRememberId(true);
		}
		return "/login/loginForm";
	}

	@RequestMapping(value = "/loginMemberOrMaster", method = RequestMethod.POST)
	public ModelAndView loginSvc(LoginCommand loginCommand,
			HttpServletRequest request,Errors err,
			HttpSession session,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		new LoginCommandValidator().validate(loginCommand, err);
		// 관리자면 0 멤버면 1
		int masterOrMember = Integer.parseInt(request.getParameter("masterOrMember"));
		if (masterOrMember == 1) {
			try {
				MemberInfo mi = memberInfoSvc.login(loginCommand.getId(), loginCommand.getPw());
				session.setAttribute("memberinfo", mi);
				Cookie rememberCookie = new Cookie("rememberId", loginCommand.getId());
				rememberCookie.setPath("/");
				if(loginCommand.isRememberId()) {
					rememberCookie.setMaxAge(60*60*24*100);
				}else {
					rememberCookie.setMaxAge(0);
				}
				response.addCookie(rememberCookie);
				mv.setViewName("redirect:/");
			}catch (IdPwNotMatchingException e) {
				err.reject("idPasswordNotMatching");
				mv.setViewName("/login/loginForm");
			}
		} else if (masterOrMember == 0) {
			try {
				MasterInfo mi = masterInfoSvc.login(loginCommand.getId(), loginCommand.getPw());
				session.setAttribute("masterinfo", mi);
				Cookie rememberCookie = new Cookie("rememberId", loginCommand.getId());
				rememberCookie.setPath("/");
				if(loginCommand.isRememberId()) {
					rememberCookie.setMaxAge(60*60*24*100);
				}else {
					rememberCookie.setMaxAge(0);
				}
				response.addCookie(rememberCookie);
				mv.setViewName("redirect:/");
			}catch (IdPwNotMatchingException e) {
				err.reject("idPasswordNotMatching");
				mv.setViewName("/login/loginForm");
			}
		} else {
			err.reject("nomember");
			mv.setViewName("/login/loginForm");
		}
		return mv;

	}


	@RequestMapping(value = "/loginMemberOrMaster", method = RequestMethod.GET)
	public String preventLoginSvc() {
		return "redirect:/login/login";
	}
}
