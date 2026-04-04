package com.example.micorservices.hotel_catelog_service.resources;

import com.example.micorservices.hotel_catelog_service.models.CatalogItem;
import com.example.micorservices.hotel_catelog_service.models.Hotel;
import com.example.micorservices.hotel_catelog_service.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class HotelCatalogService {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
    {
        RestTemplate restTemplate =new RestTemplate();
        List<Rating> ratings = Arrays.asList(
                new Rating("1", 4),
                new Rating("2",5)
        );
        return ratings.stream().map(rating -> {
            Hotel hotel =restTemplate.getForObject("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class );
            return new CatalogItem(hotel.getName(), "Awesome", rating.getRating());
        }).toList();
    }
}
