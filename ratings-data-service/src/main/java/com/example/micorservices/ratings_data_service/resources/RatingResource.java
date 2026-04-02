package com.example.micorservices.ratings_data_service.resources;

import com.example.micorservices.ratings_data_service.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping("/{hotelId}")
    public Rating getRating(@PathVariable("hotelId") String hotelId)
    {
        return new Rating(hotelId, 5);
    }
}
