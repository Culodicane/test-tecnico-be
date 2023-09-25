package com.lorenzo.testtecnicobackend.Mappers;

import com.lorenzo.testtecnicobackend.Dtos.LibraryDto;
import com.lorenzo.testtecnicobackend.Entities.LibraryEntity;
import com.lorenzo.testtecnicobackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryMapper {

    private static UserService userService;

    @Autowired
    public LibraryMapper(UserService userService){
        LibraryMapper.userService = userService;
    }

    public static LibraryEntity toEntity(LibraryDto libraryDto){

        LibraryEntity libraryEntity = new LibraryEntity();

        if (null != libraryDto.getId()){
            libraryEntity.setIdLibrary(libraryDto.getId());
        }
        libraryEntity.setDataAdded(libraryDto.getDateAdded());
        libraryEntity.setDateDeleted(libraryDto.getDateDeleted());
        libraryEntity.setTimesRead(libraryDto.getTimesRead());
        libraryEntity.setUser(userService.getById(libraryDto.getIdUser()));
        libraryEntity.setTitle(libraryDto.getTitle());
        libraryEntity.setIsbn(libraryDto.getIsbn());
        libraryEntity.setAuthor(libraryDto.getAuthor());
        libraryEntity.setPlot(libraryDto.getPlot());

        return libraryEntity;

    }

    public static LibraryDto toDto(LibraryEntity libraryEntity){

        LibraryDto libraryDto = new LibraryDto();

        libraryDto.setId(libraryEntity.getIdLibrary());
        libraryDto.setDateAdded(libraryEntity.getDataAdded());
        libraryDto.setDateDeleted(libraryEntity.getDateDeleted());
        libraryDto.setTimesRead(libraryEntity.getTimesRead());
        libraryDto.setIdUser(libraryEntity.getUser().getIdUser());
        libraryDto.setTitle(libraryEntity.getTitle());
        libraryDto.setIsbn(libraryEntity.getIsbn());
        libraryDto.setAuthor(libraryEntity.getAuthor());
        libraryDto.setPlot(libraryEntity.getPlot());

        return libraryDto;

    }

}
