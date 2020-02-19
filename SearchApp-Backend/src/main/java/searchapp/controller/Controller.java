package searchapp.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import searchapp.dao.GameDataRepository;
import searchapp.dto.GameDataDto;
import searchapp.dto.SearchInput;
import searchapp.model.GameData;
import searchapp.searchfilter.RCountSort;
import searchapp.searchfilter.RatingSort;
import searchapp.service.SearchService;

@RestController
public class Controller {

	@Autowired
	SearchService service;
	
	@Autowired
	GameDataRepository repo;
	
	@PostMapping(value = {"/search"}, headers="Accept=application/json")
	public List<GameDataDto> search(@RequestBody SearchInput input) throws IllegalArgumentException{
		String title = input.getTitle();
		String subgenre = input.getSubgenre();
		String rs = input.getRatingSort();
		List<GameData> list;
		
		//Search by title if subgenre is empty
		if(subgenre == "") {
			list = service.findGameByTitle(title);
		}else if(subgenre != "" && title != ""){
			//Search use both title and subgenre
			System.out.println("Do type 2");
			list = service.findGameByTitleAndSubgenre(title, subgenre);
		}else {
			if(rs.contentEquals("ASC")) {
				list =service.findGameBySubgenreSortedByRatingsAsc(subgenre);
			}else if(rs.contentEquals("DESC")){
				list = service.findGameBySubgenreSortedByRatingsDesc(subgenre);
			}else 
				list = service.findGameBySubgenre(subgenre);
		}
		List<GameDataDto> dtoList= new ArrayList<GameDataDto>();
		
		//add the result to data transfer object list
		for(GameData g: list) {
			dtoList.add(convertToDto(g));
		}
		return dtoList;
	}
	
	/**
	 * Adding first 500 lines of data in games.json into the database
	 */
	@GetMapping(value = {"/init"})
	public void addData() {
	    System.out.println("Starting adding data to the database");

		JSONParser jsonParser = new JSONParser();
	    
	    try (FileReader reader = new FileReader("src/main/resources/games.json"))
	    {
	        //Read JSON file
	        Object obj = jsonParser.parse(reader);
	
	        JSONArray gameList = (JSONArray) obj;
	        //Iterate over game array
	        for(int i =0; i< 500; i++) {
	        	parseGameObject((JSONObject)gameList.get(i));
	        }
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    System.out.println("Done adding data to the database");
	}
	
	private GameData parseGameObject(JSONObject game) {
		String genre = (String)game.get("genre");
		String imgURL = (String)game.get("imgURL");
		String subgenre = (String)game.get("subgenre");
		String title = (String)game.get("title");
		String pid = (String)game.get("pid");
		double ratint = Double.parseDouble(((String) game.get("rating")));
		int rCount = Integer.parseInt((String)game.get("rCount"));
	    System.out.println("Converting data to the database");

		return service.createGameData(genre, imgURL, subgenre, title, pid, ratint, rCount);
	}
	
	private GameDataDto convertToDto(GameData data) {
		if(data == null) {
			throw new IllegalArgumentException("Null game data");
		}
		GameDataDto dto = new GameDataDto(data.getGenre(),data.getImgURL()
				,data.getSubgenre(),data.getTitle(),data.getPid(),data.getRating(),data.getrCount());
		return dto;
	}
}
