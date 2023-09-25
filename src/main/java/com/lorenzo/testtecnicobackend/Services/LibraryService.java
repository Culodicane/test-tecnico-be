package com.lorenzo.testtecnicobackend.Services;

import com.lorenzo.testtecnicobackend.Dtos.LibraryDto;
import com.lorenzo.testtecnicobackend.Entities.LibraryEntity;
import com.lorenzo.testtecnicobackend.Mappers.LibraryMapper;
import com.lorenzo.testtecnicobackend.Repositeries.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    LibraryRepository libraryRepository;

    public List<LibraryDto> getAll() {

        List<LibraryDto> libraryDtos = new LinkedList<>();
        this.libraryRepository.findAll().forEach(libraryEntity -> {
            libraryDtos.add(LibraryMapper.toDto(libraryEntity));
        });

        return libraryDtos;
    }

    public List<LibraryDto> getAllByEmail(String email) {

        List<LibraryDto> libraryDtos = new LinkedList<>();
        this.libraryRepository.findAllByUserEmail(email).forEach(libraryEntity -> {
            libraryDtos.add(LibraryMapper.toDto(libraryEntity));
        });

        return libraryDtos;
    }

    public LibraryEntity getById(Integer id) {
        return this.libraryRepository.getReferenceById(id);
    }

    public Integer add(LibraryDto libraryDto) {
        return this.libraryRepository.save(LibraryMapper.toEntity(libraryDto)).getIdLibrary();
    }

    public Integer update(LibraryDto libraryDto) {
        return this.libraryRepository.save(LibraryMapper.toEntity(libraryDto)).getIdLibrary();
    }
    public String delete(Integer id) {
        this.libraryRepository.deleteById(id);
        return "libro eliminato";
    }

}