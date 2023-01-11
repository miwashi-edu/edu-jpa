# edu-jpa

> Vi lägger till JPA mappning till Artist.

> Glöm inte lägga till setters/getters.

## Table

```
desc Artist;
+----------+--------------+------+-----+---------+-------+
| Field    | Type         | Null | Key | Default | Extra |
+----------+--------------+------+-----+---------+-------+
| ArtistId | int          | NO   | PRI | NULL    |       |
| Name     | varchar(120) | YES  |     | NULL    |       |
+----------+--------------+------+-----+---------+-------+
```

## se.iths.persistency.model.Artist

```java

@Entity
@Table(name = "Artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ArtistId")
    private Long id;
    
    @Column(name = "Name")
    private String name;
}
```

## se.iths.persistency.ArtistRepository

```java
public interface ArtistRepository extends CrudRepository<Artist, Long> {

}
```

## se.iths.controller.ApiController

```java
@GetMapping("/artist")
public ResponseEntity<Artist[]> findAllArtists() {
    logger.info("findAllArtists");
    Collection<Artist> result = new ArrayList<>();

    Iterable<Artist> artists = artistRepository.findAll();
    for(Artist artist: artists){
        result.add(artist);
    }
    return ResponseEntity.ok().body(result.toArray(new Artist[result.size()]));
}
```

## ./app/src/main/resources/templates/index.html

> Vi kommenterar bort album för nu!

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<html>
    <head>
        <link rel="stylesheet" href="/index.css">
    </head>
    <body>
        <h1 th:text="${title}" />
        <div th:each="artist : ${artists}">
            <h2 th:text="${artist.name}" />
            <blockquote>
                <h3 th:text="${subtitle}" />
                <!--
                <div th:each="album : ${artist.albums}">
                    <span th:text="${album.title}" />
                </div>
                -->
            </blockquote>
            <p></p>
        </div>

        <script src='/index.js'/>
    </body>
</html>
```

## Surfa

```
http://localhost:8080
```

## Testa

```bash
// CREATE
curl --request POST --url http://localhost:8080/api/artist --header 'Content-Type: application/json' --data '{"id": "1", "name": "Nisse"}'

// READ
curl --request GET --url http://localhost:8080/api/artist/1

// UPDATE
curl --request PUT --url http://localhost:8080/api/artist/1 --header 'Content-Type: application/json' --data '{"id": "1","name": "Nisse"}'

// DELETE
curl --request DELETE --url http://localhost:8080/api/artist/1
```
