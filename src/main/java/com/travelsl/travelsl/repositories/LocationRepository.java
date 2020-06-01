package com.travelsl.travelsl.repositories;

import com.travelsl.travelsl.models.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationModel , String> {


}
