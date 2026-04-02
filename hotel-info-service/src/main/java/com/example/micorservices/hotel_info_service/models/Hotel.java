package com.example.micorservices.hotel_info_service.models;

public class Hotel {
    private String id;
    private String name;

    public Hotel(String id, String name) {
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
        this.nam = nam;
    }
}
