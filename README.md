# searchapp

This is the backend of a game information search application. Support RESTful api query.
Language: Java  Framework: Spring Boot  Build: Gradle

***To Run It***

In root folder, use command 
```
java -jar SearchApp-Backend/build/libs/SearchApp-Backend-0.0.1-SNAPSHOT.jar
```

Use POSTMAN or whatever RESTful client, send **POST** request to `http://127.0.0.1:8080/search`

Request Path: ` /search ` 

Request Method: POST

Request Body :

```
{
  "title": "TITLE_KEYWORD",
  "subgenre": "SUBGENRE_FULL_NAME",
  "ratingSort":"\[DEFAULT|ASC|DESC\]"
}
```

**title**: search keyword for game title. Can have space and special character(although will be removed when querying database). Ignorant to cases.

**subgenre**: full spelling name of the genre. ex. "Action", "Casino".

**ratingSort**: sort query result by rating. Could be ascending, descending and default(not sorted)

***Usage***: 
|title|subgenre|ratingSort|result|
|-----|--------|----------|------|
|NOT_NULL|""|"DEFAULT"|Search result only by title keywords|
|NOT_NULL|NOT_NULL|"DEFAULT"|Search result by title keywords and subgenre fullspelling|
|""|NOT_NULL|"DEFUALT"|Top30 results within the subgenre|
|""|NOT_NULL|"ASC"|Top20 results within the subgenre sorted by rating in ascending order|
|""|NOT_NULL|"DESC"|Top20 results within the subgenre sorted by rating in descending order|


Another path is used to loading game data in gamedata.json into the database. For saving time, I just insert first 500 rows. It you want to, you could try it. But it will take some time.

Request Path: ` /init ` 

Request Method: GET

