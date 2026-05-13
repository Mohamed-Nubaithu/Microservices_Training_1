package com.example.micorservices.movie_catelog_service.resources;

import com.example.micorservices.movie_catelog_service.models.CatalogItem;
import com.example.micorservices.movie_catelog_service.models.Movie;
import com.example.micorservices.movie_catelog_service.models.Rating;
import com.example.micorservices.movie_catelog_service.models.UserRating;
import com.example.micorservices.movie_catelog_service.services.MovieInfo;
import com.example.micorservices.movie_catelog_service.services.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private MovieInfo movieInfo;

    @Autowired
    private UserRatingInfo userRatingInfo;


    @RequestMapping("/{userId}")
//    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
    {
        UserRating userRating = userRatingInfo.getUserRating(userId);
        return userRating.getUserRating().stream().map(movieInfo::getCatalogItem).collect(Collectors.toList());
    }

//    public List<CatalogItem> getFallbackCatalog(String userId, Throwable ex)
//    {
//        System.out.println("Reason for --->"+ ex);
//        ex.printStackTrace();
//        return List.of(new CatalogItem("No Movie", "", 0));
//    }
}

// using web client to make external api call
//            Movie Movie = webClientBuilder.build().
//                          get().
//                          uri("http://localhost:8082/Movies/"+rating.getMovieId()).
//                          retrieve().
//                          bodyToMono(Movie.class).
//                          block();
