package searchapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import searchapp.model.GameData;
public interface GameDataRepository extends CrudRepository<GameData, Long>{
	List<GameData> findByTitle(String title);
	
	List<GameData> findByTitleLike(String regex);
	List<GameData> findByTitleLikeAndSubgenre(String regex, String subgenre);
}
