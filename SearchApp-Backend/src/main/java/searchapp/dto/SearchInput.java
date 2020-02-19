package searchapp.dto;

public class SearchInput {
	private String title;
	private String subgenre;
	
	public SearchInput(String title, String subgenre) {
		this.setTitle(title);
		this.setSubgenre(subgenre);
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
	
}
