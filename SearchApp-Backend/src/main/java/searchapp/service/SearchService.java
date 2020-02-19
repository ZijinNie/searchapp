package searchapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import searchapp.dao.GameDataRepository;
import searchapp.model.GameData;

@Service
public class SearchService {
	
	@Autowired
	private GameDataRepository repo;
	
	private GameData gameData;
	
	@Transactional
	public List<GameData> findGameByTitle(String title) {
		if(title == null ||title.trim().length() == 0) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		List<GameData> data = repo.findByTitleLike(title);
		return data;
	}
	
	@Transactional
	public List<GameData> findGameByTitleAndSubgenre(String title, String subgenre){
		if(title == null ||title.trim().length() == 0) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		if(subgenre == null ||subgenre.trim().length() == 0) {
			throw new IllegalArgumentException("Subgenre cannot be null");
		}
		List<GameData> data = repo.findByTitleLikeAndSubgenre(title,subgenre);
		return data;
	}
	
	@Transactional
	public String createRegexTitle(String segment) {
		String[] split = segment.split("\\s");
		for(String s : split) {
			s.replaceAll("[^a-zA-Z0-9]", "");
			s.toLowerCase();
		}
		String regex = "^.*";
		for(String s: split) {
			regex += s;
			regex += ".*";
		}
		regex += "%";
		
		return regex;
	}	
}	
