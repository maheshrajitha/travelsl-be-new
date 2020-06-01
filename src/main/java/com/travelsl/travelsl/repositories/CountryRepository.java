package com.travelsl.travelsl.repositories;

import com.travelsl.travelsl.models.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryModel , String> {
}
