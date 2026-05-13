package com.example.micorservices.movie_catelog_service.services;

import com.example.micorservices.movie_catelog_service.models.CatalogItem;
import com.example.micorservices.movie_catelog_service.models.Movie;
import com.example.micorservices.movie_catelog_service.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalog",
    commandProperties={
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")

    })
    public CatalogItem getCatalogItem(Rating rating)
    {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class );
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

    public CatalogItem getFallbackCatalog(Rating rating)
    {
        return new CatalogItem("No Movie found", "", rating.getRating());
    }
}
