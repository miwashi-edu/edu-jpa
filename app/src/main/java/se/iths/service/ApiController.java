package se.iths.service;

import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.persistency.model.Album;
import se.iths.persistency.model.Artist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api")
public class ApiController {

    Logger logger = LoggerFactory.getLogger(ApiController.class);
    @GetMapping("/artist")
    public ResponseEntity<Artist[]> findAllArtists() {
        logger.info("findAllArtists");
        Collection<Artist> result = randomArtists();
        return ResponseEntity.ok().body(result.toArray(new Artist[result.size()]));
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<Artist> findArtistById(@PathVariable Long id) {
        logger.info("findArtistById: {}", id);

        return ResponseEntity.ok().body(new Artist("n/a"));
    }

    @PostMapping("/artist")
    public ResponseEntity<Artist> addArtist(@RequestBody Artist artist) {
        logger.info("addArtist: {}", artist);

        return ResponseEntity.ok().body(new Artist("n/a"));
    }

    @PostMapping("/artist/{id}/album")
    public ResponseEntity<Artist> addAlbumToArtist(@PathVariable Long id, @RequestBody Album album) {
        logger.info("addAlbumToArtist: {} {}", id, album);

        return ResponseEntity.ok().body(new Artist("n/a"));
    }

    @PutMapping("/artist/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @RequestBody Artist artist) {
        logger.info("updateArtist: {} {}", id, artist);

        return ResponseEntity.ok().body(new Artist("n/a"));
    }

    @DeleteMapping("/artist/{id}")
    public ResponseEntity<Artist> deleteArtist(@PathVariable Long id) {
        logger.info("deleteArtist: {} {}", id);

        return ResponseEntity.ok().body(new Artist("n/a"));
    }
    @DeleteMapping("/artist/{id}/{albumId}")
    public ResponseEntity<Artist> deleteAlbumFromArtist(@PathVariable Long id, @PathVariable Long albumId) {
        logger.info("addAlbumToArtist: {} {}", id, albumId);

        return ResponseEntity.ok().body(new Artist("n/a"));
    }

    private Collection<Artist> randomArtists() {
        Collection<Artist> result = new ArrayList<>();

        Faker randomData = new Faker();
        int numAlbums = ThreadLocalRandom.current().nextInt(10);
        for(int i = 0; i < numAlbums; i++){
            result.add(new Artist(randomData.rockBand().name()));
        }
        return result;
    }
}
