package se.iths.persistency.model;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class Artist {

    private Long id;
    private String name;
    private Collection<Album> albums = new ArrayList<>();
    public Artist(String name){
        this.name = name;
        albums = randomAlbums();
    }

    public Artist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Album> getAlbums(){
        return albums;
    }

    public void add(Album album){
        albums.add(album);
    }

    private Collection<Album> randomAlbums() {
        Collection<Album> result = new ArrayList<>();

        Faker randomData = new Faker();

        int numAlbums = ThreadLocalRandom.current().nextInt(10) + 1;
        for(int i = 0; i < numAlbums; i++){
            result.add(new Album(randomData.book().title()));
        }
        return result;
    }

    public String toString(){
        return name;
    }
}
