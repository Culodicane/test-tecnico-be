package com.lorenzo.testtecnicobackend.Repositeries;

import com.lorenzo.testtecnicobackend.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);

}
