package com.travelsl.travelsl.services.servicesimpl;

import com.travelsl.travelsl.dtos.CountryDto;
import com.travelsl.travelsl.dtos.FavouriteLocationDto;
import com.travelsl.travelsl.dtos.LocationDto;
import com.travelsl.travelsl.exceptions.TravelSlException;
import com.travelsl.travelsl.exceptions.exceptionmodels.LocationExceptionModel;
import com.travelsl.travelsl.exceptions.exceptionmodels.TravelSlInternalServerError;
import com.travelsl.travelsl.models.LocationModel;
import com.travelsl.travelsl.repositories.CountryRepository;
import com.travelsl.travelsl.repositories.FavouriteLocationRepository;
import com.travelsl.travelsl.repositories.LocationRepository;
import com.travelsl.travelsl.services.LocationService;
import com.travelsl.travelsl.util.ImageUploadClient;
import com.travelsl.travelsl.util.Mapper;
import com.travelsl.travelsl.util.models.ActiveUser;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private ImageUploadClient imageUploadClient;
    @Autowired
    private FavouriteLocationRepository favouriteLocationRepository;
    @Autowired
    private ActiveUser activeUser;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    HttpClient httpClient;

    @Override
    public LocationDto saveLocation(LocationDto locationDto) {
        LocationModel locationModel = mapper.locationDtoToLocationModel(locationDto);
        locationRepository.save(locationModel);
        try {
            imageUploadClient.uploadImage(locationDto.getImage() , locationDto.getId());
            return locationDto;
        } catch (NoSuchAlgorithmException e) {
            throw new TravelSlException(TravelSlInternalServerError.NO_SUCH_ALGORITH_EXCEPTION);
        }
    }

    @Override
    public List<LocationDto> getAllLocations() {
        return locationRepository.findAll().stream().map(locationModel ->mapper.locationModelToLocationDto(locationModel,new LocationDto())).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> getCityWeather(String city) {
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q=^,LK&units=metric&appid=6432bcdf6dc50b554d8b96cc9833e991".replace("^",city))).build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(httpResponse.body());
            Map<String , String> res = new HashMap<>();
            JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
            res.put("description",weather.get("description").toString());
            res.put("icon",weather.get("icon").toString());
            res.put("temprature",jsonObject.getJSONObject("main").get("temp").toString());
            weather = null;
            jsonObject = null;
            return res;
        } catch (IOException | InterruptedException | JSONException e) {
            log.error("locationServiceError {}",e.getMessage());
            throw new TravelSlException(LocationExceptionModel.WEATHER_FETCHING_ERROR);
        }
    }

    @Override
    public FavouriteLocationDto addLocationToFavouriteLocations(@NotNull FavouriteLocationDto favouriteLocationDto){
        if(favouriteLocationRepository.findByLocationIdAndUserId(favouriteLocationDto.getLocationId() , activeUser.getUserId()) == null) {
            favouriteLocationDto.setUserId(activeUser.getUserId());
            favouriteLocationRepository.save(mapper.getFavouriteLocationModel(favouriteLocationDto));
            return favouriteLocationDto;
        }
        throw new TravelSlException(LocationExceptionModel.LOCATION_EXISTS_INFAVOURITES);
    }

    @Override
    public List<FavouriteLocationDto> getFavouriteLocationDtoList(){
        List<FavouriteLocationDto> favouriteLocationDtos = favouriteLocationRepository.findAllBy(activeUser.getUserId());
        if(favouriteLocationDtos.size() == 0)
            throw new TravelSlException(LocationExceptionModel.NO_FAVOURITE_LOCATIONS);
        else
            return favouriteLocationDtos;
    }

    @Override
    public CountryDto saveCountry(CountryDto countryDto) {
        try {
            countryDto.setId(UUID.randomUUID().toString());
            imageUploadClient.uploadImage(countryDto.getImage() , countryDto.getId());
            countryDto.setImage("https://travelsl.blob.core.windows.net/locationimages/"+countryDto.getId());
            countryRepository.save(mapper.getCountryModel(countryDto));
            return countryDto;
        }catch (TravelSlException | NoSuchAlgorithmException e){
            log.error(e.getLocalizedMessage());
            throw new TravelSlException(TravelSlInternalServerError.NO_SUCH_ALGORITH_EXCEPTION);
        }
    }

    public List<CountryDto> getAllCountries(){
        return countryRepository.findAll().stream().map(countryModel -> mapper.getCountryDto(countryModel , new CountryDto())).collect(Collectors.toList());
    }

    public Map<String , String> getLatAndlong(String address){
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create("https://api.mapbox.com/geocoding/v5/mapbox.places/"+address.replace("%","")+".json?access_token=pk.eyJ1IjoibWFoZXNoLXJhaml0aGEiLCJhIjoiY2s3Y2EyNG0zMGt2azNrbXI3MGhobGsyYiJ9.L5blu4mfiCDmlpcRsR9MVw")).build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(httpResponse.body());
            log.info(jsonObject.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates").toString());
            JSONArray coordinates = jsonObject.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates");
            Map<String , String> coordinatesResponse = new HashMap<>();
            coordinatesResponse.put("long",coordinates.get(0).toString());
            coordinatesResponse.put("lat",coordinates.get(1).toString());
            coordinates = null;
            jsonObject = null;
            return coordinatesResponse;
        }catch (IOException | InterruptedException | JSONException e){
            log.error(e.getLocalizedMessage());
            throw new TravelSlException(TravelSlInternalServerError.MAP_BOX_ERROR);
        }
    }
}
