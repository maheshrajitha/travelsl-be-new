package com.travelsl.travelsl.controllers;

import com.travelsl.travelsl.annotations.AuthorizeScope;
import com.travelsl.travelsl.dtos.CountryDto;
import com.travelsl.travelsl.dtos.FavouriteLocationDto;
import com.travelsl.travelsl.dtos.LocationDto;
import com.travelsl.travelsl.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController implements Controller {
    @Autowired
    private LocationService locationService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadImage(@RequestBody LocationDto locationDto) {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.saveLocation(locationDto));
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @AuthorizeScope(3)
    public ResponseEntity<?> getAllLocations(){
        return ResponseEntity.ok(locationService.getAllLocations());
    }
    @AuthorizeScope(2)
    @GetMapping(path = "/weather/{location}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLocationWeatherInfo(@PathVariable("location") String location){
        return ResponseEntity.ok(locationService.getCityWeather(location));
    }

    @PostMapping(path = "/add-to-favourites" , produces = MediaType.APPLICATION_JSON_VALUE)
    @AuthorizeScope(3)
    public ResponseEntity<?> addToFavourites(@RequestBody FavouriteLocationDto  favouriteLocationDto){
        return ResponseEntity.ok(locationService.addLocationToFavouriteLocations(favouriteLocationDto));
    }

    @GetMapping(path = "/get-favourites", produces = MediaType.APPLICATION_JSON_VALUE)
    @AuthorizeScope(3)
    public ResponseEntity<?> getFavouriteLocations(){
        return ResponseEntity.ok(locationService.getFavouriteLocationDtoList());
    }

    @PostMapping(path = "/save-country" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @AuthorizeScope(3)
    public ResponseEntity<?> saveCountry(@RequestBody CountryDto countryDto){
        return ResponseEntity.ok(locationService.saveCountry(countryDto));
    }

    @GetMapping(path = "/get-locations" , produces = MediaType.APPLICATION_JSON_VALUE)
    @AuthorizeScope(3)
    public ResponseEntity<?> getAllCountries(){
        return ResponseEntity.ok(locationService.getAllCountries());
    }

    @AuthorizeScope(3)
    @GetMapping(path = "/get-coords/{address}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCoords(@PathVariable("address") String address){
        return ResponseEntity.ok(locationService.getLatAndlong(address));
    }
}
