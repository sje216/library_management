package library.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import library.dao.HopebookanswerDAO;
import library.dao.HopebookinginfoDAO;
import library.service.AnswerRegistService;
import library.service.HopeBookRegistService;
import library.vo.HopeBookCommand;
import library.vo.Hopebookanswer;
import library.vo.Hopebookinginfo;
import library.vo.MasterInfo;
import library.vo.MemberInfo;

@Controller
public class HopeBookController {

	private HopebookinginfoDAO dao;
	private HopeBookRegistService hopeRegSvc;
	private HopebookanswerDAO answerdao;

	public void setDao(HopebookinginfoDAO dao) {
		this.dao = dao;
	}

	public void setHopeRegSvc(HopeBookRegistService hopeRegSvc) {
		this.hopeRegSvc = hopeRegSvc;
	}

	public void setAnswerdao(HopebookanswerDAO answerdao) {
		this.answerdao = answerdao;
	}

	// 희망 도서 신청 페이지로 이동
	@RequestMapping("/hopebook")
	public ModelAndView hopeForm(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		MasterInfo masterinfo = (MasterInfo) session.getAttribute("masterinfo");
		MemberInfo memberinfo = (MemberInfo) session.getAttribute("memberinfo");

		if (memberinfo != null || masterinfo != null) {
			mv.addObject("hopeBook", "logined");
			List<Hopebookinginfo> books = dao.booksSelectById(memberinfo.getId());
			List<Hopebookanswer> answer = answerdao.selectAll();
			if (answer != null) {
				mv.addObject("ans", answer);
			}

			mv.addObject("hopebooks", books);

			mv.setViewName("/hope/bap");
			return mv;
		}

		mv.setViewName("redirect:/login");
		return mv;
	}

	// 희망 도서 신청하기
	@RequestMapping(value = "/regist_book")
	public ModelAndView reigstBook(HttpSession session, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		MasterInfo masterinfo = (MasterInfo) session.getAttribute("masterinfo");
		MemberInfo memberinfo = (MemberInfo) session.getAttribute("memberinfo");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		HopeBookCommand command = new HopeBookCommand(memberinfo.getId(), title, content);
		try {
			hopeRegSvc.reigstHopeBook(command);
			mv.setViewName("redirect:/hopebook");
		} catch (Exception e) {
			mv.setViewName("redirect:/regist_book");
		}

		return mv;
	}

	// 희망도서 수정 페이지로 넘기기
	@RequestMapping("updateHopeBookF")
	public String updateHopeBook(@RequestParam("num") int num, Model model) {
		Hopebookinginfo book = dao.booksSelectByNum(num);
		model.addAttribute("book", book);
		return "/hope/bup";
	}

	// 희망 도서 수정 기능
	@RequestMapping("updateHopeBook")
	public String updateHopeBook(@RequestParam("num") int num, @RequestParam("title") String title,
			@RequestParam("content") String content) {
		dao.updateByNum(title, content, num);
		return "redirect:/hopebook";
	}

	// 희망 도서 삭제하기
	@RequestMapping("/deleteHopeBook")
	public String deleteHopeBook(@RequestParam("num") int num) {
			dao.delete(num);
		System.out.println("삭제 완료");
		return "redirect:/hopebook";
	}

}
