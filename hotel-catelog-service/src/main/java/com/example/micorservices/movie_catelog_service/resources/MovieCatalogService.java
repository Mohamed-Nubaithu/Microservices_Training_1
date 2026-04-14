package com.example.micorservices.movie_catelog_service.resources;

import com.example.micorservices.movie_catelog_service.models.CatalogItem;
import com.example.micorservices.movie_catelog_service.models.Movie;
import com.example.micorservices.movie_catelog_service.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
    {
        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/"+userId, UserRating.class);
        return userRating.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class );
            return new CatalogItem(movie.getName(), "Awesome", rating.getRating());
        }).toList();
    }
}

// using web client to make external api call
//            Movie Movie = webClientBuilder.build().
//                          get().
//                          uri("http://localhost:8082/Movies/"+rating.getMovieId()).
//                          retrieve().
//                          bodyToMono(Movie.class).
//                          block();
