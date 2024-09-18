package com.homeproject.cineprime.logic.service;

import com.homeproject.cineprime.domain.model.Star;
import com.homeproject.cineprime.domain.repository.StarRepository;
import com.homeproject.cineprime.logic.dto.StarDto;
import com.homeproject.cineprime.logic.mapper.StarMapper;
import com.homeproject.cineprime.logic.util.PublicIdGenerator;
import com.homeproject.cineprime.view.request_json.StarRequestJson;
import com.homeproject.cineprime.view.response_json.StarResponseJson;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StarService {

    private StarRepository starRepository;
    public StarService(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    @Transactional(readOnly = true)

    public List<StarResponseJson> getAllStarResponseJson() {
        Pageable config = PageRequest.of(0, 2);
        List<Star> allStar = starRepository.findByDeletedAtIsNull(config);
        List<StarDto> allStarDto = allStar
                .stream()
                .map(StarMapper::starToDto)
                .toList();

        return allStarDto
                .stream()
                .map(StarMapper::dtoToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public StarResponseJson getStarResponseJsonById(String publicId) throws ResponseStatusException {
        if(StringUtils.isEmpty(publicId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    """
                    The given ID is NULL or empty string, 
                    try with another ID! Given ID: 
                    """ + publicId);
        }

        Optional<Star> star = starRepository.findByPublicIdAndDeletedAtIsNull(publicId);

        if(star.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found with ID: " + publicId);
        }

        StarDto starDto = new StarDto();
        starDto = StarMapper.starToDto(star.get());
        StarResponseJson returnValue = new StarResponseJson();
        returnValue = StarMapper.dtoToResponse(starDto);

        return returnValue;
    }

    public StarResponseJson createStar(StarRequestJson starRequestJson) throws ResponseStatusException {
        if(!(starRequestJson instanceof StarRequestJson) || starRequestJson == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given object not compatible type or null.");
        }

        StarDto starDto = new StarDto();
        starDto = StarMapper.requestToDto(starRequestJson);

        Star newEntity = new Star();
        newEntity = StarMapper.dtoToStar(starDto);
        newEntity.setPublicId(PublicIdGenerator.generateId(30));

        Star savedStar = starRepository.save(newEntity);
        StarDto savedEntityDto = StarMapper.starToDto(savedStar);
        StarResponseJson returnValue = new StarResponseJson();
        returnValue = StarMapper.dtoToResponse(savedEntityDto);

        return returnValue;
    }

    public StarResponseJson updateStar(StarRequestJson starRequestJson) throws ResponseStatusException {
        if(!(starRequestJson instanceof StarRequestJson) || starRequestJson == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given object not compatible type or null.");
        }

        Optional<Star> starToUpdate = starRepository.findByPublicIdAndDeletedAtIsNull(starRequestJson.getPublicId());

        if(starToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Star not found with ID: " + starRequestJson.getPublicId());
        }

        Star star = new Star();
        StarDto starDto = new StarDto();
        starDto = StarMapper.requestToDto(starRequestJson);
        star = StarMapper.dtoToStar(starDto);

        star.setId(starToUpdate.get().getId());
        Star updatedStar = starRepository.save(star);

        StarDto savedStarDto = new StarDto() ;
        savedStarDto = StarMapper.starToDto(updatedStar);
        StarResponseJson returnValue = StarMapper.dtoToResponse(savedStarDto);

        return returnValue;
    }

    public String removeStarByPublidId(String publicId) throws ResponseStatusException {
        if(publicId == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The identifier is null. Give a valid identifier number!"
            );
        }

        Optional<Star> star = starRepository.findByPublicIdAndDeletedAtIsNull(publicId);

        if(star.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Star not found with ID: " + publicId);
        }

        starRepository.delete(star.get());

        return "Star with identifier: " + publicId + " successfully deleted!";
    }

    public Optional<Star> findByPublicId(String starPublicId) {
        return starRepository.findByPublicIdAndDeletedAtIsNull(starPublicId);
    }
}
