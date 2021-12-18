package library.vo;

import java.util.Date;

public class Hopebookanswer {

	private int ano;
	private int num;
	private String answer;
	private Date datetime;

	public Hopebookanswer(int num, String answer) {
		this.num = num;
		this.answer = answer;
	}

	public Hopebookanswer(int num, String answer, Date datetime) {
		this.num = num;
		this.answer = answer;
		this.datetime = datetime;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

}
