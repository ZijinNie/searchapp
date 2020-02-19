package searchapp.dto;

import searchapp.searchfilter.RCountSort;
import searchapp.searchfilter.RatingSort;

public class SearchInput {
	private String title;
	private String subgenre;
	private String ratingSort;
	
	public SearchInput() {
	}
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
	public String getRatingSort() {
		return ratingSort;
	}
	public void setRatingSort(String ratingSort) {
		this.ratingSort = ratingSort;
	}


	
}
