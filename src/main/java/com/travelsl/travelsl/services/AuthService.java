package com.travelsl.travelsl.services;

import com.travelsl.travelsl.dtos.UserDto;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AuthService {
    Map<String , String> login(UserDto userDto , HttpServletResponse httpServletResponse);
}
