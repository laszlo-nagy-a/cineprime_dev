package com.homeproject.cineprime.logic.service;

import com.homeproject.cineprime.domain.model.Director;
import com.homeproject.cineprime.domain.repository.DirectorRepository;
import com.homeproject.cineprime.logic.dto.DirectorDto;
import com.homeproject.cineprime.logic.mapper.DirectorMapper;
import com.homeproject.cineprime.logic.util.PublicIdGenerator;
import com.homeproject.cineprime.view.request_json.DirectorRequestJson;
import com.homeproject.cineprime.view.response_json.DirectorResponseJson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {

    private DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Transactional(readOnly = true)
    public List<DirectorResponseJson> getAllDirectorResponseJson() {

        List<Director> allDirector = directorRepository.findAll();
        List<DirectorDto> allDirectorDto = allDirector
                .stream()
                .map(DirectorMapper::directorToDto)
                .toList();

        return allDirectorDto
                .stream()
                .map(DirectorMapper::dtoToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public DirectorResponseJson getDirectorResponseJsonByPublicId(String publicId) {

        if(publicId == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                """
                The given ID is NULL or empty string, 
                try with another ID! Given ID: 
                """ + publicId);
    }


        Optional<Director> director = directorRepository.findByPublicId(publicId);

        if(director.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with ID: " + publicId);
        }

        DirectorDto directorDto = new DirectorDto();
        directorDto = DirectorMapper.directorToDto(director.get());
        DirectorResponseJson returnValue = DirectorMapper.dtoToResponse(directorDto);

        return returnValue;
    }

    public DirectorResponseJson createDirector(DirectorRequestJson directorRequestJson) {

        if(!(directorRequestJson instanceof DirectorRequestJson) || directorRequestJson == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given object not compatible type or null.");
        }

        DirectorDto directorDto = new DirectorDto();
        directorDto = DirectorMapper.requestToDto(directorRequestJson);

        Director director = new Director();
        director = DirectorMapper.dtoToDirector(directorDto);
        director.setPublicId(PublicIdGenerator.generateId(30));

        Director savedDirector = directorRepository.save(director);

        DirectorDto savedDirectorDto = DirectorMapper.directorToDto(savedDirector);
        DirectorResponseJson returnValue = DirectorMapper.dtoToResponse(savedDirectorDto);

        return returnValue;
    }

    public DirectorResponseJson updateDirector(DirectorRequestJson directorRequestJson) {

        if(!(directorRequestJson instanceof  DirectorRequestJson) || directorRequestJson == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given object not compatible type or null.");
        }

        Optional<Director> directorToUpdate = directorRepository.findByPublicId(directorRequestJson.getPublicId());

        if(directorToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with ID: " + directorRequestJson.getPublicId());
        }

        Director director = new Director();
        DirectorDto directorDto = new DirectorDto();
        directorDto = DirectorMapper.requestToDto(directorRequestJson);
        director = DirectorMapper.dtoToDirector(directorDto);

        director.setId(directorToUpdate.get().getId());
        Director savedDirector = directorRepository.save(director);

        DirectorDto savedDirectorDto = new DirectorDto();
        savedDirectorDto = DirectorMapper.directorToDto(savedDirector);
        DirectorResponseJson returnValue = new DirectorResponseJson();
        returnValue = DirectorMapper.dtoToResponse(savedDirectorDto);


        return returnValue;
    }

    public String removeDirectorByPublidId(String publicId) {

        if(publicId == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The identifier is null. Give a valid identifier number!"
            );
        }

        Optional<Director> director = directorRepository.findByPublicId(publicId);

        if(director.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with ID: " + publicId);
        }

        directorRepository.delete(director.get());

        return "Director with identifier: " + publicId + " successfully deleted!";
    }
}
