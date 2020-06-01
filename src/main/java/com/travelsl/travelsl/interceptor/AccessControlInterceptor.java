package com.travelsl.travelsl.interceptor;

import com.travelsl.travelsl.annotations.AuthorizeScope;
import com.travelsl.travelsl.dtos.TokenDto;
import com.travelsl.travelsl.exceptions.TravelSlException;
import com.travelsl.travelsl.exceptions.exceptionmodels.AuthExceptionModel;
import com.travelsl.travelsl.util.Mapper;
import com.travelsl.travelsl.util.TokenGenerator;
import com.travelsl.travelsl.util.models.ActiveUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AccessControlInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private Mapper mapper;
    @Autowired
    private ActiveUser activeUser;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if(request.getHeader("Authorization") != null && handlerMethod.hasMethodAnnotation(AuthorizeScope.class)) {
                String token = request.getHeader("Authorization");
                TokenDto tokenDto = tokenGenerator.validateToken(token.replace("Bearer ",""));
                if (tokenDto.getRole() <= handlerMethod.getMethodAnnotation(AuthorizeScope.class).value()) {
                    activeUser.setRole(tokenDto.getRole());
                    activeUser.setUserId(tokenDto.getUserId());
                    return true;
                }else  {
                    throw new TravelSlException(AuthExceptionModel.NO_ACCESS);
                }
            }else if(!handlerMethod.hasMethodAnnotation(AuthorizeScope.class)) {
                return true;
            }else if(request.getHeader("Authorization") == null)
                throw new TravelSlException(AuthExceptionModel.EMPTY_TOKEN);
        }
        response.setContentType("application/json");
        //response.setHeader("Content-Type","application/json");
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return false;
    }
}
