package searchapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import searchapp.dao.GameDataRepository;
import searchapp.dto.GameDataDto;
import searchapp.dto.SearchInput;
import searchapp.model.GameData;
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
		List<GameData> list;
		if(subgenre == "") {
			list = service.findGameByTitle(title);
		}else {
			list = service.findGameByTitleAndSubgenre(title, subgenre);
		}
		List<GameDataDto> dtoList= new ArrayList<GameDataDto>();
		for(GameData g: list) {
			dtoList.add(convertToDto(g));
		}
		return dtoList;
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
