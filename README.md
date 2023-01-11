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

## Album

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

## Tillägg till Artist

```java
@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
@JoinColumn(name = "ArtistID")
private Collection<Album> albums = new ArrayList<>();
```

## Repository

```java
public interface ArtistRepository extends CrudRepository<Artist, Long> {

}
```

## ApiController

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
