package com.lorenzo.testtecnicobackend.Repositeries;

import com.lorenzo.testtecnicobackend.Entities.LibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface LibraryRepository extends JpaRepository<LibraryEntity, Integer> {
    List<LibraryEntity> findAllByUserEmail(String email);
}
