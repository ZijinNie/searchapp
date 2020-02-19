package searchapp.dto;

public class GameDataDto {
	private String genre;
	private String imgURL;
	private String subgenre;
	private String title;
	private String pid;
	private double rating;
	private int rCount;
	public GameDataDto(String genre, String imgURL, String subgenre, String title, String pid, double rating,
			int rCount) {
		super();
		this.genre = genre;
		this.imgURL = imgURL;
		this.subgenre = subgenre;
		this.title = title;
		this.pid = pid;
		this.rating = rating;
		this.rCount = rCount;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public String getSubgenre() {
		return subgenre;
	}
	public void setSubgenre(String subgenre) {
		this.subgenre = subgenre;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getrCount() {
		return rCount;
	}
	public void setrCount(int rCount) {
		this.rCount = rCount;
	}
	
	
}
