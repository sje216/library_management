package library.vo;

import java.util.Date;

public class Hopebookinginfo {

	private int num;
	private String id;
	private String title;
	private String content;
	private Date datetime;
	private int viewCnt;

	public Hopebookinginfo(String id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public Hopebookinginfo(String id, String title, String content, Date datetime, int viewCnt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.datetime = datetime;
		this.viewCnt = viewCnt;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

}
