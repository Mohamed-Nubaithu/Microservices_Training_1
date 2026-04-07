package com.example.micorservices.ratings_data_service.resources;

import com.example.micorservices.ratings_data_service.models.Rating;
import com.example.micorservices.ratings_data_service.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping("/{hotelId}")
    public Rating getRating(@PathVariable("hotelId") String hotelId)
    {
        return new Rating(hotelId, 5);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("1", 4),
                new Rating("2",5)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;

    }
}
