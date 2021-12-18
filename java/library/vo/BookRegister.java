package library.vo;

public class BookRegister {

	private String title;
	private String author;
	private String company;
	private String content;
	private String coverImg;
	private int condition;

	public BookRegister(String title, String author, String company, String content, String coverImg, int condition) {
		this.title = title;
		this.author = author;
		this.company = company;
		this.content = content;
		this.coverImg = coverImg;
		this.condition = condition;
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
