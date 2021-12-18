package library.vo;

public class BookUpdateCommand {

	private String title;
	private String author;
	private String company;
	private int condition;
	private String coverImg;
	private String content;

	public BookUpdateCommand() {}
	
	public BookUpdateCommand(String title, String author, String company, int condition, String coverImg,
			String content) {
		this.title = title;
		this.author = author;
		this.company = company;
		this.condition = condition;
		this.coverImg = coverImg;
		this.content = content;
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

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
