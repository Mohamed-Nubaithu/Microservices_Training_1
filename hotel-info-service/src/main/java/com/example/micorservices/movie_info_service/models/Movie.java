package com.example.micorservices.movie_info_service.models;

public class Movie {

    private String id;
    private String name;

    public Movie(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNam(String nam) {
        this.name = name;
    }
}
