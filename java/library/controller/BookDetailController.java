package library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import library.dao.BookDateDAO;
import library.dao.BookinginfoDAO;
import library.vo.BookDate;
import library.vo.Bookinginfo;

@Controller
@RequestMapping("/book")
public class BookDetailController {

	private BookDateDAO dao;
	private BookinginfoDAO bookinginfodao;

	// 책 정보 조회 페이지로 이동
	public void setDao(BookDateDAO dao) {
		this.dao = dao;
	}

	public void setBookinginfodao(BookinginfoDAO bookinginfodao) {
		this.bookinginfodao = bookinginfodao;
	}

	@RequestMapping("/detail")
	public ModelAndView bookdetail(@RequestParam("bookNum") int bookNum, Model model) {
		ModelAndView mv = new ModelAndView();
		BookDate book = dao.bookSelectedByBookNum(bookNum);
		if (book.getCoverImg().substring(0, 4).equals("http")) {
			model.addAttribute("coverImg", book.getCoverImg());
		} else {
			// String[] str = book.getCoverImg().split("/");
			model.addAttribute("coverImg", "/ContextPath/" + book.getCoverImg());
		}
		Bookinginfo bookingbook = bookinginfodao.selectByBookNum(bookNum);
		mv.addObject("book", book);
		mv.addObject("bookingbook", bookingbook);
		mv.setViewName("book/bdp");
		return mv;
	}

}
