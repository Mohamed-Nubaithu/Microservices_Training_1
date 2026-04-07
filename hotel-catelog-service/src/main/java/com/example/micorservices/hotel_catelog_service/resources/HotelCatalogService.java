package com.example.micorservices.hotel_catelog_service.resources;

import com.example.micorservices.hotel_catelog_service.models.CatalogItem;
import com.example.micorservices.hotel_catelog_service.models.Hotel;
import com.example.micorservices.hotel_catelog_service.models.Rating;
import com.example.micorservices.hotel_catelog_service.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class HotelCatalogService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
    {
        UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/user/"+userId, UserRating.class);
        return userRating.getUserRating().stream().map(rating -> {
            Hotel hotel = restTemplate.getForObject("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class );
            return new CatalogItem(hotel.getName(), "Awesome", rating.getRating());
        }).toList();
    }
}

// using web client to make external api call
//            Hotel hotel = webClientBuilder.build().
//                          get().
//                          uri("http://localhost:8082/hotels/"+rating.getHotelId()).
//                          retrieve().
//                          bodyToMono(Hotel.class).
//                          block();
