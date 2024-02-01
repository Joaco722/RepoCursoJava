package com.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    public String create(String id, String subject) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(key);
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + ttlMillis))
                .withClaim("id", id)
                .sign(algorithm);
    }

    public String getValue(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }

    public String getKey(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Claim claim = jwt.getClaim("id");
        return claim.asString();
    }
}


