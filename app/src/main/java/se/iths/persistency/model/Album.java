package se.iths.persistency.model;

public class Album {
    private Long id;
    private String title;

    public Album(){

    }

    public Album(String title){
        this.title = title;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString(){
        return title;
    }
}
