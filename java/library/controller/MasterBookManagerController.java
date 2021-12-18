package library.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import library.dao.BookDateDAO;
import library.exception.AlreadyBookException;
import library.service.BookInsertService;
import library.service.BookUpdateService;
import library.vo.BookDate;
import library.vo.BookRegister;
import library.vo.BookUpdateCommand;

@Controller
@RequestMapping("/admin_book")
public class MasterBookManagerController {
	private BookDateDAO dao;
	private BookUpdateService bookUpdateSvc;
	private BookInsertService bookInsertSvc;
	private static final String UPLOAD_PATH = "C:\\Users\\User\\Desktop\\aaa\\library\\src\\main\\webapp\\resources\\img";
	
	public void setDao(BookDateDAO dao) {
		this.dao = dao;
	}

	public void setBookUpdateSvc(BookUpdateService bookUpdateSvc) {
		this.bookUpdateSvc = bookUpdateSvc;
	}

	public void setBookInsertSvc(BookInsertService bookInsertSvc) {
		this.bookInsertSvc = bookInsertSvc;
	}

	// 梨� �젙蹂� 議고쉶 �럹�씠吏�濡� �씠�룞
	@RequestMapping(value = "/bookinfo", method = RequestMethod.GET)
	public ModelAndView managerBooks(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String section = request.getParameter("section");
		String pageNum = request.getParameter("pageNum");
		int sec = Integer.parseInt((section == null) ? "1" : section);
		int page = Integer.parseInt((pageNum == null) ? "1" : pageNum);
		List<BookDate> books = dao.selectForAllBook(sec, page);
		int fullPage = dao.allBookcount();
		mv.addObject("books", books);
		mv.addObject("fullPage", fullPage);
		mv.addObject("section", sec);
		mv.addObject("pageNum", page);
		mv.setViewName("master/bookManager");
		return mv;
	}

	// 梨� �젙蹂� �닔�젙 �럹�씠吏�濡� �씠�룞
	@RequestMapping(value = "/book_modifyForm", method = RequestMethod.GET)
	public String bookModifyF(@ModelAttribute("updatecommand") BookUpdateCommand updatecommand,
			@RequestParam("bookNum") int bookNum, Model model) {
		BookDate book = dao.bookSelectedByBookNum(bookNum);
		if(book.getCoverImg().substring(0, 4).equals("http")) {
			model.addAttribute("coverImg",book.getCoverImg());
		}else {
			model.addAttribute("coverImg","/ContextPath/"+book.getCoverImg());	
		}
		
		model.addAttribute("book", book);
		return "/master/bookModify";
	}

	// 梨� �젙蹂� �닔�젙�븯湲�
	@RequestMapping(value = "/book_modify", method = RequestMethod.POST)
	public String bookModify(@ModelAttribute("updatecommand") BookUpdateCommand updatecommand,
			@RequestParam(value = "bookNum",required = false) int bookNum) {
		System.out.println(bookNum);
		try {
			bookUpdateSvc.bookupdateBybookNum(updatecommand, bookNum);
			return "redirect:/admin_book/bookinfo";
		} catch (Exception e) {
			return "redirect:/admin_book/book_modifyForm";
		}
	}

	// 梨� �젙蹂� �궘�젣 湲곕뒫
	@RequestMapping(value = "/book_delete")
	public String delete(@RequestParam("bookNum") int bookNum) {
		dao.delete(bookNum);
		return "/master/master";
	}

	// 梨� �젙蹂� 異붽� �럹�씠吏�濡� �씠�룞
	@RequestMapping("/book_insert")
	public String insertBookF() {
		return "/master/addBook";
	}

	// 梨� �젙蹂� 異붽� 湲곕뒫
	@RequestMapping(value = "/addbook")
	public ModelAndView insertBook(@RequestParam("title") String title, @RequestParam("author") String author,
			@RequestParam("company") String company, @RequestParam("condition") int condition,
			@RequestParam("coverImg") MultipartFile coverImg, @RequestParam("content") String content,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		//String contextPath = request.getServletContext().getRealPath("/resources/img/"); //${pageContext.request.contextPath}
		//System.out.println("�떎�젣 寃쎈줈 : "+contextPath);
		String saveName = saveFile(coverImg);  //.meta
		System.out.println("�떎�젣 寃쎈줈 : "+saveName);
		BookRegister book = new BookRegister(title, author, company, content, saveName,
				condition);
		try {
			bookInsertSvc.insertBook(book);
			System.out.println(saveName);
			mv.setViewName("redirect:/admin_book/bookinfo");
		} catch (AlreadyBookException e) {
			mv.setViewName("redirect:/admin_book/book_insert");
		}

		return mv;
	}

	private String saveFile(MultipartFile img) {
		// �뙆�씪 �씠由� 蹂�寃�
		UUID uuid = UUID.randomUUID();
		String saveName = uuid + "_" + img.getOriginalFilename();

		// ���옣�븷 File 媛앹껜瑜� �깮�꽦(猿띾뜲湲� �뙆�씪)�꽩
		File saveFile = new File(UPLOAD_PATH, saveName); // ���옣�븷 �뤃�뜑 �씠由�, ���옣�븷 �뙆�씪 �씠由�  //

		try {
			img.transferTo(saveFile); // �뾽濡쒕뱶 �뙆�씪�뿉 saveFile�씠�씪�뒗 猿띾뜲湲� �엯�옒
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return saveName;
	} // end saveFile(

}
