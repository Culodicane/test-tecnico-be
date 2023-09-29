package com.lorenzo.testtecnicobackend.Controllers;

import com.lorenzo.testtecnicobackend.Dtos.LibraryDto;
import com.lorenzo.testtecnicobackend.Services.LibraryService;
import com.lorenzo.testtecnicobackend.jwt.JwtRequestFilter;
import com.lorenzo.testtecnicobackend.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/library")
@CrossOrigin(origins = {"http://localhost:4200","http://172.20.0.4:4200"})
public class LibraryController {

    @Autowired
    LibraryService libraryService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @PostMapping(value = "/getAll")
    private ResponseEntity getAll(@RequestHeader(value = "Authorization") String token){

        Map<String, String> body = new HashMap<>();

        if (!this.jwtRequestFilter.isUserLogged("Bearer " + token)){
            body.put("errore", "token scaduto / non valido");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }

        try{
            return ResponseEntity.ok(this.libraryService.getAll());
        }catch (Exception e){
            return new ResponseEntity<>("Errore generico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = {"http://localhost:4200"})
    @PostMapping(value = "/getAllByUser")
    private ResponseEntity getAllByUser(@RequestHeader(value = "Authorization") String token){

        Map<String, String> body = new HashMap<>();

        if (!this.jwtRequestFilter.isUserLogged("Bearer " + token)){
            body.put("errore", "token scaduto / non valido");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }

        try{
            String loggedUser = this.jwtTokenUtil.getEmailFromToken(token);
            for (LibraryDto libraryDto : this.libraryService.getAllByEmail(loggedUser)){
                System.out.println(libraryDto);
            }
            return ResponseEntity.ok(this.libraryService.getAllByEmail(loggedUser));
        }catch (Exception e){
            return new ResponseEntity<>("Errore generico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = {"http://localhost:4200"})
    @PostMapping(value = "/add")
    private ResponseEntity add(@RequestBody LibraryDto libraryDto, @RequestHeader(value = "Authorization") String token){

        Map<String, String> body = new HashMap<>();
        System.out.println(libraryDto);

        if (!this.jwtRequestFilter.isUserLogged("Bearer " + token)){
            body.put("errore", "token scaduto / non valido");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }

        try{
            return ResponseEntity.ok(this.libraryService.add(libraryDto));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Errore generico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = {"http://localhost:4200"})
    @PostMapping(value = "/update")
    private ResponseEntity update(@RequestBody LibraryDto libraryDto, @RequestHeader(value = "Authorization") String token){

        Map<String, String> body = new HashMap<>();

        if (!this.jwtRequestFilter.isUserLogged("Bearer " + token)){
            body.put("errore", "token scaduto / non valido");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }

        try{
            return ResponseEntity.ok(this.libraryService.update(libraryDto));
        }catch (Exception e){
            return new ResponseEntity<>("Errore generico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = {"http://localhost:4200"})
    @PostMapping(value = "/delete")
    private ResponseEntity delete(@RequestBody Integer libraryId, @RequestHeader(value = "Authorization") String token){

        Map<String, String> body = new HashMap<>();

        if (!this.jwtRequestFilter.isUserLogged("Bearer " + token)){
            body.put("errore", "token scaduto / non valido");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }

        try{
            return ResponseEntity.ok(this.libraryService.delete(libraryId));
        }catch (Exception e){
            return new ResponseEntity<>("Errore generico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
