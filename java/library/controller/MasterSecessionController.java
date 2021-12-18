package library.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import library.dao.MasterDAO;
import library.dao.MemberDAO;
import library.vo.Master;
import library.vo.MasterInfo;
import library.vo.Member;
import library.vo.MemberInfo;

@Controller
public class MasterSecessionController {
	
	private MasterDAO dao;

	public void setDao(MasterDAO dao) {
		this.dao = dao;
	}

	// 탈퇴
	@RequestMapping("/secession_master")
	public ModelAndView secession(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		MasterInfo mi = (MasterInfo)session.getAttribute("masterinfo");
		Master master = dao.findpw(mi.getName(), mi.getEmail(), mi.getId());
		String masterNum = String.valueOf(master.getMasterNum());
		dao.delete(masterNum);
		session.invalidate();
		mv.setViewName("redirect:/");
		return mv;
	}
	
//		public ModelAndView delete(@RequestParam(value = "memberNum", required = false) String memberNum) {
//			dao.delete(memberNum);
//		}
}
