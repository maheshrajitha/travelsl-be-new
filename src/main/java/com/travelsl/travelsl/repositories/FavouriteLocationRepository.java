package com.travelsl.travelsl.repositories;

import com.travelsl.travelsl.dtos.FavouriteLocationDto;
import com.travelsl.travelsl.models.FavouriteLocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteLocationRepository extends JpaRepository<FavouriteLocationModel , String> {

    FavouriteLocationModel findByLocationIdAndUserId(String locationId , String userId);

    @Query("select new com.travelsl.travelsl.dtos.FavouriteLocationDto(f.userId , f.locationId , l.image , l.country , l.city)  from FavouriteLocationModel f join LocationModel l on l.id=f.locationId where f.userId=:userId")
    List<FavouriteLocationDto> findAllBy(String userId);
}
