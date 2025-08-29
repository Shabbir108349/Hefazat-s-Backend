package com.shabbir.hefazat.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private final SecretKey key = Jwts.SIG.HS256.key().build();

    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
       return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*5))
                .and()
                .header()
                .add("type","jwt")
                .empty()
                .and()
                .signWith(key)
                .compact();
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isExpiredToken(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean isValidToken(String token){
        return !isExpiredToken(token);
    }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }
}
