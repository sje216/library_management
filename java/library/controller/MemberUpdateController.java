package library.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import library.dao.MemberDAO;
import library.exception.IdPwNotMatchingException;
import library.service.MemberUpdateService;
import library.validator.MemberUpdateCommandValidator;
import library.vo.Member;
import library.vo.MemberInfo;
import library.vo.MemberUpdateCommand;

@Controller
public class MemberUpdateController {

	private MemberDAO dao;
	private MemberUpdateService memberUpdateSvc;
	
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}

	public void setMemberUpdateSvc(MemberUpdateService memberUpdateSvc) {
		this.memberUpdateSvc = memberUpdateSvc;
	}

	@RequestMapping("/memberinfo")
	public ModelAndView memberInfoForm(@ModelAttribute("updateCommand")MemberUpdateCommand updateCommand,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		MemberInfo memberinfo  = (MemberInfo) session.getAttribute("memberinfo");
		Member member = dao.selectById(memberinfo.getId());
		mv.addObject("member",member);
		mv.setViewName("/member/memberUpdate");
		return mv;
	}
	
	@RequestMapping(value = "/changeMember",method = RequestMethod.POST)
	public ModelAndView MemberUpdate(@ModelAttribute("updateCommand") MemberUpdateCommand updateCommand,HttpServletRequest request,
			Errors err) {
		ModelAndView mv = new ModelAndView();
		new MemberUpdateCommandValidator().validate(updateCommand, err);
		String id = request.getParameter("id");
		if(err.hasErrors()) {
			mv.setViewName("/member/memberUpdate");
		}
		
		try {
			memberUpdateSvc.memberupdateById(updateCommand, id);
			mv.setViewName("redirect:/");
		}catch (IdPwNotMatchingException e) {
			err.rejectValue("pw", "required");
			mv.setViewName("/member/memberUpdate");
		}
		
		return mv;
	}
	
	
}
