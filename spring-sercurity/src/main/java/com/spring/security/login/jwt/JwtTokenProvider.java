package com.spring.security.login.jwt;


import com.spring.security.login.model.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    /**
     * ma hoa thong tin nguoi dung thanh ma jwt
     */

    private final String JWT_SECRET = "testJwt";

    // thoi gian su dung cua ma jwt
    private final long JWT_EXPIRATION = 6000000L;


    //tao chuoi jwt tu thong tin cua user
    public String generateToken(CustomUserDetails userDetails){

        Date now = new Date();

        Date expiryDay = new Date(now.getTime() + JWT_EXPIRATION);

        //tao chuoi jwt tu id cua user
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getUser().getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDay)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }


    //lay thong tin user tu jwt
    public Integer getUserIdFromJWT(String token){

        Claims  claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject());
    }


    public boolean validateToken(String authToken){

        try{
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex){
            log.error("Invalid JWT token.");
        } catch (ExpiredJwtException ex){
            log.error("Expired JWT token.");
        } catch (UnsupportedJwtException ex){
            log.error("Unsuppported JWT token.");
        } catch (IllegalArgumentException ex){
            log.error("JWT claims string is empty.");
        }

        return false;

    }

}
