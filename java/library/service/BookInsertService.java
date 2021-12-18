package library.service;

import library.dao.BookDateDAO;
import library.exception.AlreadyBookException;
import library.vo.BookDate;
import library.vo.BookRegister;

public class BookInsertService {
	
	private BookDateDAO dao;

	public BookInsertService(BookDateDAO dao) {
		this.dao = dao;
	}
	
	public void insertBook(BookRegister reg) {
		BookDate book = dao.bookSelectedByTitle(reg.getTitle());
		if(book!=null) {
			throw new AlreadyBookException("이미 존재하는 정보입니다 : "+reg.getTitle());
		}
		
		BookDate newBook = new BookDate(reg.getTitle(), reg.getAuthor(), reg.getCompany(), reg.getCondition(), reg.getCoverImg(), reg.getContent());
		dao.insert(newBook);
	}

}
