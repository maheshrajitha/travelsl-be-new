package com.travelsl.travelsl.services.servicesimpl;

import com.travelsl.travelsl.dtos.UserDto;
import com.travelsl.travelsl.exceptions.TravelSlException;
import com.travelsl.travelsl.exceptions.exceptionmodels.UserExceptionModel;
import com.travelsl.travelsl.repositories.UserRepository;
import com.travelsl.travelsl.util.Mapper;
import com.travelsl.travelsl.util.PasswordHasher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements com.travelsl.travelsl.services.UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordHasher passwordHasher;
    @Autowired
    private Mapper mapper;


    @Override
    public UserDto saveNewUser(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()) != null)
            throw new TravelSlException(UserExceptionModel.USER_ALREADY_EXISTS);
        else {
            userRepository.save(new Mapper().userDtoToUserModel(userDto));
            return userDto;
        }
    }

}
