package com.lorenzo.testtecnicobackend.Services;

import com.lorenzo.testtecnicobackend.Entities.UserEntity;
import com.lorenzo.testtecnicobackend.Repositeries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserEntity> getAll(){
        return this.userRepository.findAll();
    }

    public UserEntity getById(Integer id){
        return this.userRepository.getReferenceById(id);
    }

    public UserEntity findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

}
