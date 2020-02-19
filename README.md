# searchapp

This is the backend of a game information search application. Support RESTful api query.
Language: Java  Framework: Spring Boot  Build: Gradle Deploy: Heroku

***To Run It***



Use POSTMAN or whatever RESTful client, send **POST** request to `https://searchapp-backend.herokuapp.com/`

Request Path: ` /search ` 

Request Method: POST

Request Body :

```
{
  "title": "TITLE_KEYWORD",
  "subgenre": "SUBGENRE_FULL_NAME",
  "ratingSort":"[DEFAULT|ASC|DESC]"
}
```
example:

```
{
  "title": "",
  "subgenre": "Action",
  "ratingSort":"DESC"
}
```

**title**: search keyword for game title. Can have space and special character(although will be removed when querying database). **case sensitive**.

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

