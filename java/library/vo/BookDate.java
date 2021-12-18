package library.vo;

public class BookDate {

	private int bookNum;
	private String title;
	private String author;
	private String company;
	private String content;
	private String coverImg;
	private int condition;

	// 북 추가 / 수정
	public BookDate(String title, String author, String company,int condition, String coverImg, String content) {
		this.title = title;
		this.author = author;
		this.company = company;
		this.content = content;
		this.coverImg = coverImg;
		this.condition = condition;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

}
