package com.travelsl.travelsl.util;

import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
@Slf4j
public class PasswordHasher {

    private MessageDigest messageDigest;
    private byte[] salt;

    public PasswordHasher() {
        try {
            this.messageDigest = MessageDigest.getInstance("sha-256");
            this.salt = "gdfgdsgjsfdfgdjdhdfa".getBytes();
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
    }

    public String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword , BCrypt.gensalt(12));
    }

    public boolean checkPassword(String plainPassword , String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public String toSha256(String value){
        this.messageDigest.update(this.salt);
        byte[] hashedValue = this.messageDigest.digest(value.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (byte byteValue : hashedValue){
            stringBuilder.append(Integer.toString((byteValue & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }
}
