package library.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import library.dao.BookDateDAO;
import library.vo.BookDate;

@Controller
@RequestMapping("/search")
public class BookSearchController {
	private BookDateDAO dao;

	// 책 정보 조회 페이지로 이동
	public void setDao(BookDateDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value = "/book",method = RequestMethod.GET)
	public ModelAndView searchForBookOrAuthor(@RequestParam(value = "searchFor")String searchFor,@RequestParam(value = "search")String search,HttpServletRequest request,
			Model model) {
		ModelAndView mv = new ModelAndView();
		String section = request.getParameter("section");
		String pageNum = request.getParameter("pageNum");
		int sec = Integer.parseInt((section==null)?"1":section);
		int page = Integer.parseInt((pageNum==null)?"1":pageNum);
		if(searchFor.equals("book")) {
			List<BookDate> books = dao.searchForBook(search,sec,page);
			int fullPage = dao.bookcount(search);
			mv.addObject("books",books);
			mv.addObject("fullPage",fullPage);
			mv.addObject("searchFor",searchFor);
			mv.addObject("search",search);
			mv.setViewName("/book/bisp");
		}else if(searchFor.equals("author")) {
			List<BookDate> books = dao.searchForAuthor(search,sec,page);
			int fullPage = dao.authorcount(search);
			mv.addObject("books",books);
			mv.addObject("fullPage",fullPage);
			mv.addObject("searchFor",searchFor);
			mv.addObject("search",search);
			mv.setViewName("/book/bisp");
		}else {
			mv.setViewName("/mainPage");
			
			
		}
		request.setAttribute("section",sec );
		request.setAttribute("pageNum",page );
		return mv;
	}
}
