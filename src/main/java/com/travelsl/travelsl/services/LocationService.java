package com.travelsl.travelsl.services;

import com.travelsl.travelsl.dtos.CountryDto;
import com.travelsl.travelsl.dtos.FavouriteLocationDto;
import com.travelsl.travelsl.dtos.LocationDto;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public interface LocationService {

    LocationDto saveLocation(LocationDto locationDto);
    List<LocationDto> getAllLocations();
    Map<String, String> getCityWeather(String city);
    FavouriteLocationDto addLocationToFavouriteLocations(@NotNull FavouriteLocationDto favouriteLocationDto);
    List<FavouriteLocationDto> getFavouriteLocationDtoList();
    CountryDto saveCountry(CountryDto countryDto);
    List<CountryDto> getAllCountries();
    Map<String , String> getLatAndlong(String address);
}
