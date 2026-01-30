package com.gl.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import java.util.Date;
import com.gl.model.User;

public class TokenService
{
    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 3600000L;
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create().withAudience(new String[] { user.getUserName() }).withIssuedAt(start).withExpiresAt(end).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
