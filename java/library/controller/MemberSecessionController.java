package library.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import library.dao.MemberDAO;
import library.vo.Member;
import library.vo.MemberInfo;

@Controller
public class MemberSecessionController {
	
	private MemberDAO dao;

	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}

	// 탈퇴
	@RequestMapping("/secession_member")
	public ModelAndView secession(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		MemberInfo mi = (MemberInfo)session.getAttribute("memberinfo");
		Member member = dao.findpw(mi.getName(), mi.getEmail(), mi.getId());
		String memberNum = String.valueOf(member.getMemberNum());
		dao.delete(memberNum);
		session.invalidate();
		mv.setViewName("redirect:/");
		return mv;
	}
	
//		public ModelAndView delete(@RequestParam(value = "memberNum", required = false) String memberNum) {
//			dao.delete(memberNum);
//		}
}
