package library.service;

import library.dao.HopebookinginfoDAO;
import library.exception.AlreadyBookException;
import library.vo.HopeBookCommand;
import library.vo.Hopebookinginfo;

public class HopeBookRegistService {

	private HopebookinginfoDAO dao;

	public HopeBookRegistService(HopebookinginfoDAO dao) {
		this.dao = dao;
	}
	
	public void reigstHopeBook(HopeBookCommand command) {
		Hopebookinginfo hopebook = dao.selectById(command.getTitle());
		
		if(hopebook!=null) {
			throw new AlreadyBookException("이미 존재하는 책입니다  : "+hopebook.getTitle());
		}
		Hopebookinginfo newhopebook = new Hopebookinginfo(command.getId(), command.getTitle(), command.getContent());
		dao.insert(newhopebook);
	}
}
