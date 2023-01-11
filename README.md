# edu-jpa

> Vi lägger till JPA mappning till Album.

> Glöm inte lägga till setters/getters.

## Table

```
desc Album;
+----------+--------------+------+-----+---------+-------+
| Field    | Type         | Null | Key | Default | Extra |
+----------+--------------+------+-----+---------+-------+
| AlbumId  | int          | NO   | PRI | NULL    |       |
| Title    | varchar(160) | NO   |     | NULL    |       |
| ArtistId | int          | NO   | MUL | NULL    |       |
+----------+--------------+------+-----+---------+-------+
```

## se.iths.persistency.model.Album

```java
@Entity
@Table(name = "Album")
public class Album {
    @Id
    @Column(name = "AlbumId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "Title")
    private String title;
}
```

## Tillägg till se.iths.persistency.model.Artist

```java
@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
@JoinColumn(name = "ArtistID")
private Collection<Album> albums = new ArrayList<>();
```

## ./app/src/main/resources/templates/index.html

> Vi lägger tillbaka Album!

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
                <div th:each="album : ${artist.albums}">
                    <span th:text="${album.title}" />
                </div>
                
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
