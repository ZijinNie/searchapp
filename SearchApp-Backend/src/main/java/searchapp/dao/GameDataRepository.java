package searchapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import searchapp.model.GameData;
public interface GameDataRepository extends CrudRepository<GameData, Long>{
	List<GameData> findByTitle(String title);
	
	@Query(nativeQuery=true, value="SELECT * FROM game_data WHERE title SIMILAR TO ?1")
	List<GameData> findByTitleLike(String regex);	//SQL query of finding all games meet the regex use only title
	
	@Query(nativeQuery=true, value="SELECT * FROM game_data WHERE (title SIMILAR TO ?1) AND subgenre = ?2 ")
	List<GameData> findByTitleLikeAndSubgenre(String regex, String subgenre); //SQL query of finding game use title and subgenre

	@Query(nativeQuery=true, value="SELECT * FROM game_data WHERE subgenre = ?1 FETCH FIRST 30 ROWS ONLY ")
	List<GameData> findBySubgenre(String subgenre); //SQL query of finding game use title and subgenre
	
	@Query(nativeQuery=true, value="SELECT * FROM game_data WHERE subgenre = ?1 ORDER BY rating DESC FETCH FIRST 20 ROWS ONLY")
	List<GameData> findBySubgenreSortedByRatingDesc(String subgenre);
	
	@Query(nativeQuery=true, value="SELECT * FROM game_data WHERE subgenre = ?1 ORDER BY rating ASC FETCH FIRST 20 ROWS ONLY")
	List<GameData> findBySubgenreSortedByRatingAsc(String subgenre);
	
	@Query(nativeQuery=true, value="SELECT * FROM game_data  ORDER BY rating ASC FETCH FIRST 20 ROWS ONLY")
	List<GameData> findSortedByRatingAsc(String subgenre);
	
	@Query(nativeQuery=true, value="SELECT * FROM game_data  ORDER BY rating DESC FETCH FIRST 20 ROWS ONLY")
	List<GameData> findSortedByRatingDesc(String subgenre);
}
