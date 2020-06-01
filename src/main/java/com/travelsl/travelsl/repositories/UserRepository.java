package com.travelsl.travelsl.repositories;

import com.travelsl.travelsl.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel , String> {

    UserModel findByEmail(String email);
}
