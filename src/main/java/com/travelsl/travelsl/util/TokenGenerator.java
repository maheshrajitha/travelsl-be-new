package com.travelsl.travelsl.util;

import com.google.gson.Gson;
import com.travelsl.travelsl.dtos.TokenDto;
import com.travelsl.travelsl.exceptions.TravelSlException;
import com.travelsl.travelsl.exceptions.exceptionmodels.AuthExceptionModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@Slf4j
public class TokenGenerator {

    @Autowired
    private Gson gson;

    private final String secret = "ndkfhkdshfksdhfhwehfiuwbvkjbvkd";

    public String generateAccessToken(TokenDto tokenDto){
        String payLoad = Base64.getEncoder().encodeToString(gson.toJson(tokenDto).getBytes());
        String signature = new PasswordHasher().toSha256(payLoad.concat(".").concat(secret));
        return payLoad.concat(".").concat(signature);
    }

    public TokenDto validateToken(String token){
        if (token != null){
            log.info(token);
            String [] tokenCookie = token.split("\\.");
            if(new PasswordHasher().toSha256(tokenCookie[0].concat(".").concat(secret)).equals(tokenCookie[1])){
                String decodedPayload = new String(Base64.getDecoder().decode(tokenCookie[0]));
                return gson.fromJson(decodedPayload , TokenDto.class);
            }else
                throw new TravelSlException(AuthExceptionModel.TOKEN_NOT_VALIED);
        }else
            throw new TravelSlException(AuthExceptionModel.EMPTY_TOKEN);
    }
}
