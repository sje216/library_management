package library.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MemberUpdateCommand {
	private String name;
	private String pw;
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date birthday;
	private String email;
	private String call;
	
	public MemberUpdateCommand(){}

	public MemberUpdateCommand(String name, String pw, Date birthday, String email, String call) {
		this.name = name;
		this.pw = pw;
		this.birthday = birthday;
		this.email = email;
		this.call = call;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
