package searchapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@RestController
@SpringBootApplication 
public class SearchAppApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SearchAppApplication.class, args);
	}

	@RequestMapping("/")
	public String greeting() {
		return "hello";
	}
}
