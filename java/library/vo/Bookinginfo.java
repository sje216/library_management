package library.vo;

import java.util.Date;

public class Bookinginfo {

	private int bookingNum;
	private int memberNum;
	private int bookNum;
	private Date bookingDate;
	//1이면 예약 가능 0이면 불가
	private int reservation; 
	

	public Bookinginfo(int memberNum, int bookNum) {
		this.memberNum = memberNum;
		this.bookNum = bookNum;
	}

	public Bookinginfo(int memberNum, int bookNum, Date bookingDate,int reservation) {
		this.memberNum = memberNum;
		this.bookNum = bookNum;
		this.bookingDate = bookingDate;
		this.reservation = reservation;
	}

	public int getBookingNum() {
		return bookingNum;
	}

	public void setBookingNum(int bookingNum) {
		this.bookingNum = bookingNum;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getReservation() {
		return reservation;
	}

	public void setReservation(int reservation) {
		this.reservation = reservation;
	}

}
