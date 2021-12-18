package library.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import library.exception.IdPwNotMatchingException;

public class Member {
	private Long memberNum;
	private String name;
	private String id;
	private String pw;
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date birthday;
	private String email;
	private String call;

	public Member(String name, String id, String pw, Date birthday, String email, String call) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.birthday = birthday;
		this.email = email;
		this.call = call;
	}
	
	public void change(String oldPw,String newPw) {
		if(!this.pw.equals(oldPw)) {
			throw new IdPwNotMatchingException();
		}else {
			this.pw = newPw;
		}
	}

	public Long getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Long memberNum) {
		this.memberNum = memberNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

}
