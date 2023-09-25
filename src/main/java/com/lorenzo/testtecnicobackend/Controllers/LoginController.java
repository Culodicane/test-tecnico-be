package com.lorenzo.testtecnicobackend.Controllers;

import com.lorenzo.testtecnicobackend.Entities.UserEntity;
import com.lorenzo.testtecnicobackend.Services.UserService;
import com.lorenzo.testtecnicobackend.jwt.JwtRequestFilter;
import com.lorenzo.testtecnicobackend.jwt.JwtTokenUtil;
import jakarta.servlet.annotation.HttpConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity login(@RequestBody Map<String, String> credentials){

        Map<String,String> body = new HashMap<>();

        try{
            UserEntity user = this.userService.findByEmail(credentials.get("email"));
            if (!credentials.get("password").equals(user.getPassword())){
                throw new IllegalAccessException();
            }
            body.put("token",new JwtTokenUtil().generateToken(user));
            return ResponseEntity.ok(body);
        }catch (NullPointerException e){
            body.put("errore", "email errata");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        } catch (IllegalAccessException e) {
            body.put("errore", "password errata");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @PostMapping(value = "/getUserDetails")
    public ResponseEntity getUserDetails(@RequestHeader (value = "Authorization") String token){

        Map<String, String> body = new HashMap<>();

        if (!this.jwtRequestFilter.isUserLogged("Bearer " + token)){
            body.put("errore", "token scaduto / non valido");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }

        try{
            String loggedUser = this.jwtTokenUtil.getEmailFromToken(token);
            return ResponseEntity.ok(this.userService.findByEmail(loggedUser));
        }catch (Exception e){
            return new ResponseEntity<>("Errore generico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @PostMapping(value = "/checkToken")
    public ResponseEntity checkToken(@RequestHeader (value = "Authorization") String token){

        Map<String, String> body = new HashMap<>();

        try{
            if (!this.jwtRequestFilter.isUserLogged("Bearer " + token)){
                body.put("errore", "token scaduto / non valido");
                return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            body.put("errore", "token scaduto / non valido");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }

        body.put("success", "autorizzato");
        return ResponseEntity.ok(body);
    }


}
