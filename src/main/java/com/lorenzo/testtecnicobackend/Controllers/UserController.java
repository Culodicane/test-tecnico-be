package com.lorenzo.testtecnicobackend.Controllers;

import com.lorenzo.testtecnicobackend.Services.UserService;
import com.lorenzo.testtecnicobackend.jwt.JwtRequestFilter;
import com.lorenzo.testtecnicobackend.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/user")
@CrossOrigin(origins = {"http://localhost:4200","http://172.20.0.4:4200"})

public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @PostMapping(value = "/getAll")
    public ResponseEntity getAll(@RequestHeader(value = "Authorization") String token){

        Map<String, String> body = new HashMap<>();

        if (!this.jwtRequestFilter.isUserLogged("Bearer " + token)){
            body.put("errore", "token scaduto / non valido");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }

        try{
            return ResponseEntity.ok(this.userService.getAll());
        }
        catch (Exception e){
            return new ResponseEntity<>("Errore generico", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/getById")
    public ResponseEntity getById(@RequestBody Integer userId, @RequestHeader(value = "Authorization") String token){

        Map<String, String> body = new HashMap<>();

        if (!this.jwtRequestFilter.isUserLogged("Bearer " + token)){
            body.put("errore", "token scaduto / non valido");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }

        try{
            return ResponseEntity.ok(this.userService.getById(userId));
        }
        catch (Exception e){
            return new ResponseEntity<>("Errore generico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
