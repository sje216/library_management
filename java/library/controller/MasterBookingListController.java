package library.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import library.dao.BookDateDAO;
import library.dao.BookinginfoDAO;
import library.dao.MemberDAO;
import library.vo.BookDate;
import library.vo.Bookinginfo;
import library.vo.Member;

@Controller
public class MasterBookingListController {

	private BookinginfoDAO dao;
	private BookDateDAO bookdatedao;
	private MemberDAO memberdao;

	public void setDao(BookinginfoDAO dao) {
		this.dao = dao;
	}

	public void setBookdatedao(BookDateDAO bookdatedao) {
		this.bookdatedao = bookdatedao;
	}

	public void setMemberdao(MemberDAO memberdao) {
		this.memberdao = memberdao;
	}

	// 예약 도서 게시판 목록 보여주기
	@RequestMapping(value = "/bookinglist", method = RequestMethod.GET)
	public String hopelist(HttpServletRequest request, Model model) {
		String section = request.getParameter("section");
		String pageNum = request.getParameter("pageNum");
		int sec = Integer.parseInt((section == null) ? "1" : section);
		int page = Integer.parseInt((pageNum == null) ? "1" : pageNum);
		List<Bookinginfo> booking = dao.selectforAll(sec, page);
		int fullPage = dao.allCount();
		List<Member> member = memberdao.selectAll();
		List<BookDate> book = bookdatedao.selectAll();
		model.addAttribute("section", sec);
		model.addAttribute("pageNum", page);
		model.addAttribute("fullPage", fullPage);
		model.addAttribute("booking", booking);
		model.addAttribute("member", member);
		model.addAttribute("book", book);
		
		return "/master/bookinglist";
	}
	
	@RequestMapping("/deleteBookingList")
	public String delete(@RequestParam("bookingNum")int bookingNum) {
		dao.cancel(bookingNum);
		return "redirect:/bookinglist";
	}
}
