package library.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import library.dao.MemberDAO;
import library.vo.Email;
import library.vo.EmailSender;
import library.vo.Member;

@Controller
public class FindIdPwController {
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private Email Email;
	private MemberDAO dao;

	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}

	@RequestMapping("/findIdPw")
	public String findForm() {
		return "/login/findId";
	}

	// get방식으로 id 찾기
	@RequestMapping(value = "/findId", method = RequestMethod.GET)
	public String fingId(HttpServletRequest request) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		Member member = dao.findId(name, email);
		if (member != null) {
			request.setAttribute("member", member);
			return "/login/findId";
		}
		return "redirect:/login/findId";
	}

	@RequestMapping("/findPwForm")
	public String findPwForm() {
		return "/login/findPw";
	}

	// get방식으로 비밀번호 찾기
	@RequestMapping(value = "/findPw", method = RequestMethod.GET)
	public String findPw(HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		Member m = dao.findpw(name, email, id);
		String subject = "[그린도서관] 비밀번호변경 인증 이메일 입니다";
		String content = System.getProperty("line.separator") + "안녕하세요 회원님" + System.getProperty("line.separator")
				+ "그린도서관 비밀번호찾기 비밀번호는 " + m.getPw() + " 입니다." + System.getProperty("line.separator"); //
		if (m != null) {
			request.setAttribute("m", m);
			Email.setReceiver(email);
			Email.setSubject(subject);
			Email.setContent(content);
			emailSender.SendEmail(Email);
			return "/login/findPw";
		}
		return "redirect:/login";
	}
}
