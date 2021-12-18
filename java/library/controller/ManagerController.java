package library.controller;

import java.text.ParseException;
import java.util.List;

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
import library.vo.MemberUpdateCommand;

@Controller
@RequestMapping("/admin")
public class ManagerController {

	private MemberDAO dao;
	private MemberUpdateService memberUpdateSvc;

	public void setMemberUpdateSvc(MemberUpdateService memberUpdateSvc) {
		this.memberUpdateSvc = memberUpdateSvc;
	}

	// 회원 정보 조회 페이지로 이동
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public ModelAndView managerMember() {
		ModelAndView mav = new ModelAndView();

		List<Member> members = dao.selectAll();

		mav.addObject("members", members);
		mav.setViewName("admin/memberinfo");
		return mav;
	}

	// get방식 update는 회원정보 수정 페이지 보여주기 위해서
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateMemberForm(@ModelAttribute("updatemember")MemberUpdateCommand updatemember,@RequestParam(value = "memberNum", required = false) Long memberNum
			) {
		ModelAndView mav = new ModelAndView();
		Member member = dao.selectByMemberNum(memberNum);
		mav.addObject("memberNum", memberNum);
		mav.addObject("member", member);
		if (memberNum == null) {
			mav.setViewName("redirect:/admin/member");
			return mav;
		}

		mav.setViewName("admin/memberinfoModify");
		return mav;
	}

	// post방식으로 update는 회원정보 수정완료
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateMember(@ModelAttribute("updatemember") MemberUpdateCommand updatemember,
			@RequestParam(value = "memberNum", required = false) Long memberNum,
			Errors err) throws Exception {
		ModelAndView mav = new ModelAndView();
		new MemberUpdateCommandValidator().validate(updatemember, err);
		
		if(err.hasErrors()) {
			mav.setViewName("redirect:/admin/update");
			
		}
		
		try {
		memberUpdateSvc.memberupdateBymemberNum(updatemember, memberNum);
		mav.setViewName("redirect:/admin/member");
		}catch (IdPwNotMatchingException e) {
			err.rejectValue("pw", "required");
			mav.setViewName("redirect:/admin/update");
		}
		return mav;
	}

	// 삭제
	@RequestMapping(value = "/delete")
	public ModelAndView delete(@RequestParam(value = "memberNum", required = false) String memberNum) {
		ModelAndView mav = new ModelAndView();
		dao.delete(memberNum);
		mav.setViewName("/master/master");
		return mav;
	}
}
