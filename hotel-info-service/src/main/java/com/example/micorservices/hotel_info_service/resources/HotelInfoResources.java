package com.example.micorservices.hotel_info_service.resources;

import com.example.micorservices.hotel_info_service.models.Hotel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
public class HotelInfoResources {
    @RequestMapping("/{hotelId}")
    public Hotel getHotelInfo(@PathVariable("hotelId") String hotelId){
        return new Hotel(hotelId, "Shukku Bhai");
    }
}
