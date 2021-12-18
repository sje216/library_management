package library.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import library.dao.BookDateDAO;
import library.vo.BookDate;
import library.vo.Member;

@Controller
public class MainPageController {
	private BookDateDAO dao;

	public void setDao(BookDateDAO dao) {
		this.dao = dao;
	}

	//메인으로 이동
	@RequestMapping("/")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		List<BookDate> books = dao.selectBestSeller();
		mav.addObject("books", books);
		mav.setViewName("/mainPage");
		return mav;
	}
	
	//관리자 홈으로 이동
	@RequestMapping("/masterHome")
	public String masterHome() {
		return "/master/master";
	}

}
