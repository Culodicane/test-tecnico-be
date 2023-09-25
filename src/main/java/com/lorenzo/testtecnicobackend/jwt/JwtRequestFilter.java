package com.lorenzo.testtecnicobackend.jwt;

import com.lorenzo.testtecnicobackend.Entities.UserEntity;
import com.lorenzo.testtecnicobackend.Repositeries.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtRequestFilter {

  @Autowired
  JwtTokenUtil jwtTokenUtil;
  @Autowired
  UserRepository userRepository;

  public boolean isUserLogged(String token) {

    String email = null;
    String jwtToken = null;
// JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
    if (token != null && token.startsWith("Bearer ")) {
      jwtToken = token.substring(7);
      try {
        email = jwtTokenUtil.getEmailFromToken(jwtToken);
      } catch (IllegalArgumentException e) {
        System.out.println("Unable to get JWT Token");
      } catch (ExpiredJwtException e) {
        System.out.println("JWT Token has expired");
      }
    } else {
      System.out.println("JWT Token does not begin with Bearer String");
    }

//Once we get the token validate it.
    if (email != null) {
      UserEntity user = userRepository.findByEmail(email);
      return jwtTokenUtil.validateToken(jwtToken,user);
    }
    return false;
  }
}
