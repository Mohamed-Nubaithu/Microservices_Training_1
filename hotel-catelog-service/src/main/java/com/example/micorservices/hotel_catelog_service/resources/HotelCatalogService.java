package com.example.micorservices.hotel_catelog_service.resources;

import com.example.micorservices.hotel_catelog_service.models.CatalogItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class HotelCatalogService {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
    {
        return Collections.singletonList(
                new CatalogItem("Shukku Bhai", "Awesome", 10)
        );
    }
}
