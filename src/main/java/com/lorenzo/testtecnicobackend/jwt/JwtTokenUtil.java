package com.lorenzo.testtecnicobackend.jwt;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.lorenzo.testtecnicobackend.Entities.UserEntity;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

@Component
public class JwtTokenUtil implements Serializable {

  public static final long JWT_TOKEN_VALIDITY = 3*60*60;
  //TODO secret value should be defined in application.properties
  private final String secret = "garqayasfijifjijgijfijgidjgidjfigjdfigjisjosidfjogijosijdfoigjosdifjogisdjofigjosdifjogisdjfgss";

  public String getEmailFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public Date getIssuedAtDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getIssuedAt);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public String getNameFromToken(String token) { return (String)getAllClaimsFromToken(token).get("name");}


  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    SecretKey sharedSecret = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    return Jwts.parserBuilder().setSigningKey(sharedSecret).build().parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  private Boolean ignoreTokenExpiration(String token) {
    // here you specify tokens, for that the expiration is ignored
    return false;
  }

  public String generateToken(UserEntity userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("name",userDetails.getName());
    claims.put("email",userDetails.getEmail());
    claims.put("id", userDetails.getIdUser());
    claims.put("type", userDetails.getType());
    return doGenerateToken(claims, userDetails.getEmail());
  }

  private String doGenerateToken(Map<String, Object> claims, String subject) {

    SecretKey sharedSecret = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(sharedSecret).compact();
  }

  public Boolean canTokenBeRefreshed(String token) {
    return (!isTokenExpired(token) || ignoreTokenExpiration(token));
  }

  public Boolean validateToken(String token, UserEntity userDetails) {
    final String email = getEmailFromToken(token);
    return (email.equals(userDetails.getEmail()) && !isTokenExpired(token));
  }
}
