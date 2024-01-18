package com.fptu.estate.security.jwt;

import com.fptu.estate.entities.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtService {
  @Value("${token.key}")
  private String strKey;

  private int expiredTime = 8 * 60 * 60 * 1000;

  public String generateToken(String role, UserEntity userDetails) {
    return generateToken(Map.of("role", role), userDetails);
  }

  public String generateToken(String data) {
    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
    Date date = new Date();
    long futureMilis = date.getTime() + expiredTime;

    Date futureDate = new Date(futureMilis);

    return Jwts.builder().subject(data).expiration(futureDate).signWith(key).compact();
  }


  public String generateToken(Map<String, Object> extraClaims, UserEntity userDetails) {
    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
    Date date = new Date();
    long futureMilis = date.getTime() + expiredTime;

    Date futureDate = new Date(futureMilis);

    return Jwts.builder().setClaims(extraClaims).setSubject(String.valueOf(userDetails.getId()))
        .expiration(futureDate)
        .signWith(key)
        .compact();
  }

  public String decodeToken(String token) {
    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));

    String data = null;
    try {
      data = Jwts.parser().verifyWith(key)
          .build()
          .parseSignedClaims(token)
          .getPayload()
          .getSubject();
    } catch (Exception e) {
      System.out.println("Lỗi parse token: " + e.getMessage());
    }

    return data;
  }
}
