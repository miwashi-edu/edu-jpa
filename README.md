# edu-jpa

> Vi lägger till JPA mappning till Artist.

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

## @Entity

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
