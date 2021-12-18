package library.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import library.dao.BookDateDAO;
import library.dao.BookinginfoDAO;
import library.dao.MasterDAO;
import library.dao.MemberDAO;
import library.service.BookingInfoService;
import library.vo.BookDate;
import library.vo.Bookinginfo;
import library.vo.Member;
import library.vo.MemberInfo;

@Controller
public class BookinginfoController {

	private BookinginfoDAO dao;
	private MemberDAO memberdao;
	private MasterDAO masterdao;
	private BookDateDAO bookdatedao;
	private BookingInfoService bookingSvc;

	public void setMemberdao(MemberDAO memberdao) {
		this.memberdao = memberdao;
	}

	public void setMasterdao(MasterDAO masterdao) {
		this.masterdao = masterdao;
	}

	public void setDao(BookinginfoDAO dao) {
		this.dao = dao;
	}

	public void setBookdatedao(BookDateDAO bookdatedao) {
		this.bookdatedao = bookdatedao;
	}

	public void setBookingSvc(BookingInfoService bookingSvc) {
		this.bookingSvc = bookingSvc;
	}

	// 대출 불가 책 예약하기
	@RequestMapping("/reservation")
	public String booking(HttpSession session, @RequestParam("bookNum") int bookNum, Model model) {
		MemberInfo memberinfo = (MemberInfo) session.getAttribute("memberinfo");
		if (memberinfo != null) {
			Member member = memberdao.selectById(memberinfo.getId());
			// long타입을 int 타입으로 변환
			int memberNum = Math.toIntExact(member.getMemberNum());
			// 예약 정보들을 vo에 입력
			Bookinginfo bookinginfo = new Bookinginfo(memberNum, bookNum);
			// 1명만 예약 가능
			Bookinginfo bookingbook = dao.selectByBookNum(bookNum);
			// 에약이 존재하지 않을 경우만 예약함
			if (bookingbook == null) {
				bookingSvc.bookingBook(bookinginfo);
				model.addAttribute("bookNum", bookNum);
				return "redirect:/book/detail";
			} else {
				model.addAttribute("bookNum", bookNum);
				return "redirect:/book/detail";
			}

		}
		model.addAttribute("bookNum", bookNum);
		return "redirect:/login";
	}
	
	@RequestMapping("/reserve")
	public String reserve(Model model,HttpSession session) {
		//memberNum 찾음
		MemberInfo memberinfo = (MemberInfo) session.getAttribute("memberinfo");
		Member member = memberdao.selectById(memberinfo.getId());
		int memberNum = Math.toIntExact(member.getMemberNum());
		//memberNum으로 예약정보 찾기
		List<Bookinginfo> booking = dao.selectByMemberNum(memberNum);
		List<BookDate> book = bookdatedao.selectAll();
		model.addAttribute("booking", booking);
		model.addAttribute("member", member);
		model.addAttribute("book", book);
		return "/booking/book";
		
	}

	@RequestMapping("/deletebooking")
	public String delete(@RequestParam("bookingNum") int bookingNum) {
		System.out.println(bookingNum);
		dao.cancel(bookingNum);
		return "redirect:/reserve";
	}

}
