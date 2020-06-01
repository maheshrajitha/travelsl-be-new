package com.travelsl.travelsl.util;

import com.travelsl.travelsl.dtos.*;
import com.travelsl.travelsl.models.CountryModel;
import com.travelsl.travelsl.models.FavouriteLocationModel;
import com.travelsl.travelsl.models.LocationModel;
import com.travelsl.travelsl.models.UserModel;
import com.travelsl.travelsl.util.models.ActiveUser;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class Mapper {

    public UserModel userDtoToUserModel(UserDto userDto){
        UserModel userModel = new UserModel();
        userModel.setEmail(userDto.getEmail());
        //userModel.setId(UUID.randomUUID().toString());
        userModel.setName(userDto.getName());
        userModel.setPassword(new PasswordHasher().hashPassword(userDto.getPassword()));
        userModel.setRole(2);
        return userModel;
    }

    public UserDto userModelToUserDto(UserModel userModel , UserDto userDto){
        userDto.setEmail(userModel.getEmail());
        userDto.setPassword(userModel.getPassword());
        userDto.setId(userModel.getId());
        userDto.setName(userModel.getName());
        userDto.setRole(userModel.getRole());
        return userDto;
    }

    public TokenDto mapUserModelToTokenDto(UserModel userModel){
        TokenDto tokenDto = new TokenDto();
        tokenDto.setId(UUID.randomUUID().toString());
        tokenDto.setIssuedDate(new Date().getTime());
        tokenDto.setIssuedBy("Travelsl");
        tokenDto.setRole(userModel.getRole());
        tokenDto.setUserId(userModel.getId());
        return tokenDto;
    }
    public LocationModel locationDtoToLocationModel(LocationDto locationDto){
        LocationModel locationModel = new LocationModel();
        locationModel.setCity(locationDto.getCity());
        locationModel.setCountry(locationDto.getCountry());
        locationDto.setId(UUID.randomUUID().toString());
        locationModel.setId(locationDto.getId());
        locationModel.setImage("https://travelsl.blob.core.windows.net/locationimages/"+locationDto.getId());
        locationModel.setNearestAirport(locationDto.getNearestAirport());
        return locationModel;
    }

    public LocationDto locationModelToLocationDto(LocationModel locationModel , LocationDto locationDto){
        locationDto.setId(locationModel.getId());
        locationDto.setCity(locationModel.getCity());
        locationDto.setCountry(locationModel.getCountry());
        locationDto.setImage(locationModel.getImage());
        locationDto.setNearestAirport(locationModel.getNearestAirport());
        return locationDto;
    }

    public ActiveUser tokenDtoToActiveUserModel(TokenDto tokenDto){
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserId(tokenDto.getUserId());
        activeUser.setRole(tokenDto.getRole());
        return activeUser;
    }

    public FavouriteLocationModel getFavouriteLocationModel(FavouriteLocationDto favouriteLocationDto){
        FavouriteLocationModel favouriteLocationModel = new FavouriteLocationModel();
        favouriteLocationModel.setId(favouriteLocationDto.getId());
        favouriteLocationModel.setLocationId(favouriteLocationDto.getLocationId());
        favouriteLocationModel.setUserId(favouriteLocationDto.getUserId());

        return favouriteLocationModel;
    }

    public FavouriteLocationDto getFavouriteLocationDto(FavouriteLocationDto favouriteLocationDto , FavouriteLocationModel favouriteLocationModel){
        favouriteLocationDto.setId(favouriteLocationModel.getId());
        favouriteLocationDto.setLocationId(favouriteLocationModel.getLocationId());
        favouriteLocationDto.setUserId(favouriteLocationModel.getUserId());
        return favouriteLocationDto;
    }

    public CountryModel getCountryModel(CountryDto countryDto){
        CountryModel countryModel = new CountryModel();
        countryModel.setCountry(countryDto.getCountry());
        countryModel.setId(countryDto.getId());
        countryModel.setCurrency(countryDto.getCurrency());
        countryModel.setDescription(countryDto.getDescription());
        countryModel.setImage(countryDto.getImage());
        countryModel.setPopulation(countryDto.getPopulation());
        return countryModel;
    }

    public CountryDto getCountryDto(CountryModel countryModel , CountryDto countryDto){
        countryDto.setCountry(countryModel.getCountry());
        countryDto.setCurrency(countryModel.getCurrency());
        countryDto.setDescription(countryModel.getDescription());
        countryDto.setId(countryModel.getId());
        countryDto.setImage(countryModel.getImage());
        countryDto.setPopulation(countryModel.getPopulation());
        return countryDto;
    }
}
