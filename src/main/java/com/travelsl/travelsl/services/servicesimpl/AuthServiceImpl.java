package com.travelsl.travelsl.services.servicesimpl;

import com.travelsl.travelsl.dtos.UserDto;
import com.travelsl.travelsl.exceptions.TravelSlException;
import com.travelsl.travelsl.exceptions.exceptionmodels.AuthExceptionModel;
import com.travelsl.travelsl.models.UserModel;
import com.travelsl.travelsl.repositories.UserRepository;
import com.travelsl.travelsl.services.AuthService;
import com.travelsl.travelsl.util.Mapper;
import com.travelsl.travelsl.util.PasswordHasher;
import com.travelsl.travelsl.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordHasher passwordHasher;
    @Autowired
    private Mapper mapper;
    @Autowired
    private TokenGenerator tokenGenerator;
    @Override
    public Map<String , String> login(UserDto userDto , HttpServletResponse httpServletResponse) {
        UserModel userModel = userRepository.findByEmail(userDto.getEmail());
        if (userModel != null)
            if (passwordHasher.checkPassword(userDto.getPassword(),userModel.getPassword())){
                httpServletResponse.addCookie(new Cookie("at",tokenGenerator.generateAccessToken(mapper.mapUserModelToTokenDto(userModel))));
                Map<String,String> dtoMap = new HashMap<>();
                dtoMap.put("token",tokenGenerator.generateAccessToken(mapper.mapUserModelToTokenDto(userModel)));
                dtoMap.put("userName",userModel.getName());
                dtoMap.put("email",userModel.getEmail());
                dtoMap.put("id",userModel.getId());
                return dtoMap;
                //return mapper.userModelToUserDto(userModel , userDto);
            }else
                throw new TravelSlException(AuthExceptionModel.PASSWORD_NOT_MATCHED);
        else
            throw new TravelSlException(AuthExceptionModel.USER_NOT_FOUND);
    }
}
