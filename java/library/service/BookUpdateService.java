package library.service;

import org.springframework.transaction.annotation.Transactional;

import library.dao.BookDateDAO;
import library.exception.BookNotFoundException;
import library.vo.BookDate;
import library.vo.BookUpdateCommand;

public class BookUpdateService {

	private BookDateDAO dao;

	public BookUpdateService(BookDateDAO dao) {
		this.dao = dao;
	}
	
	@Transactional
	public void bookupdateBybookNum(BookUpdateCommand updateCommand, int bookNum) {
		BookDate book = dao.bookSelectedByBookNum(bookNum);
		
		if(book==null) {
			throw new BookNotFoundException();
		}
		
		int result = dao.updateBybookNum(updateCommand.getTitle(), updateCommand.getAuthor(), updateCommand.getCompany(), updateCommand.getCondition(), updateCommand.getCoverImg(), updateCommand.getContent(), bookNum);
	}
}
