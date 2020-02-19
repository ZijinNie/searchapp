package searchapp.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import searchapp.dao.GameDataRepository;
import searchapp.model.GameData;

@Service
public class SearchService {
	
	@Autowired
	private GameDataRepository repo;
	
	/**
	 * Find the games by tile keywords regex
	 * @param title The regex form of title
	 * @return The list of games found based on the criteria
	 */
	@Transactional
	public List<GameData> findGameByTitle(String title) {
		//check for empty input string
		if(title == null ||title.trim().length() == 0) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		List<GameData> data = repo.findByTitleLike(createRegexTitle(title));
		return data;
	}
	
	/**
	 * Find the games by tile keywords regex and subgenre
	 * @param title The regex form of title
	 * @param subgenre	The full spelling of a subgenre
	 * @return The list of games found based on the criteria
	 */
	@Transactional
	public List<GameData> findGameByTitleAndSubgenre(String title, String subgenre){
		//check for empty input string
		if(title == null ||title.trim().length() == 0) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		if(subgenre == null ||subgenre.trim().length() == 0) {
			throw new IllegalArgumentException("Subgenre cannot be null");
		}
		List<GameData> data = repo.findByTitleLikeAndSubgenre(createRegexTitle(title),subgenre);
		return data;
	}
	
	/**
	 * Find games by subgenre(full spelling) and rating in descending order
	 * Only shows top 20 rows
	 * @param subgenre subgenre keyword
	 * @return The list of games found based in the criteria
	 */
	@Transactional
	public List<GameData> findGameBySubgenreSortedByRatingsDesc(String subgenre){
		if(subgenre == null ||subgenre.trim().length() == 0) {
			throw new IllegalArgumentException("Subgenre cannot be null");
		}
		List<GameData> data = repo.findBySubgenreSortedByRatingDesc(subgenre);
		return data;
	}
	
	/**
	 * Find games by subgenre(full spelling) and rating in ascending order
	 * Only shows top 20 rows
	 * @param subgenre The subgenre keyword
	 * @return The list of games found based in the criteria
	 */
	@Transactional
	public List<GameData> findGameBySubgenreSortedByRatingsAsc(String subgenre){
		if(subgenre == null ||subgenre.trim().length() == 0) {
			throw new IllegalArgumentException("Subgenre cannot be null");
		}
		List<GameData> data = repo.findBySubgenreSortedByRatingAsc(subgenre);
		return data;
	}
	
	/**
	 * Find games by subgenre(full spelling)
	 * Only shows top 30 rows
	 * @param subgenre The subgenre keyword
	 * @return The list of games found based in the criteria
	 */
	
	public List<GameData> findGameBySubgenre(String subgenre) {
		if(subgenre == null ||subgenre.trim().length() == 0) {
			throw new IllegalArgumentException("Subgenre cannot be null");
		}
		List<GameData> data = repo.findBySubgenre(subgenre);
		return data;
	}
	
	/**
	 * Generate a regex for query this input in SQL. % in SQL represent any string. 
	 * The output format of the regex will be " %seg1%seg2%..%segn%"
	 * @param segment current user input
	 * @return regex which has the input
	 */
	@Transactional
	public String createRegexTitle(String segment) {
		
		String[] split = segment.split("\\s");	//split the input string by space
		String regex = "%";
		for(String s : split) {
			s= s.replaceAll("[^a-zA-Z0-9]", "");	//remove any sepcial character in the string
			regex += s;
			regex += "%";
		}
		return regex;
	}	
	
	@Transactional
	public GameData createGameData(String genre, String imgURL, String subgenre, String title, String pid, double rating, int rCount) {
		GameData data = new GameData();
		data.setGenre(genre);
		data.setImgURL(imgURL);
		data.setPid(pid);
		data.setRating(rating);
		data.setrCount(rCount);
		data.setTitle(title);
		data.setSubgenre(subgenre);
		Random r = new Random();
		data.setId(r.nextLong());
		repo.save(data);
		return data;
	}


	
}	
