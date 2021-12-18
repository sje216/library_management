package library.service;

import library.dao.BookinginfoDAO;
import library.exception.AlreadyBookException;
import library.vo.Bookinginfo;

public class BookingInfoService {

	private BookinginfoDAO dao;

	public BookingInfoService(BookinginfoDAO dao) {
		this.dao = dao;
	}
	

	public void bookingBook(Bookinginfo booking) {
		int cnt = dao.allCountByMemberNum(booking.getMemberNum());
		if(cnt>2) {
			throw new AlreadyBookException("예약은 2회까지만 가능합니다.");
		}
		dao.resorvation(booking);
	}
}
