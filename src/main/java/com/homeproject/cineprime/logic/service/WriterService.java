package com.homeproject.cineprime.logic.service;

import com.homeproject.cineprime.domain.model.Writer;
import com.homeproject.cineprime.domain.repository.WriterRepository;
import com.homeproject.cineprime.logic.dto.WriterDto;
import com.homeproject.cineprime.logic.mapper.WriterMapper;
import com.homeproject.cineprime.logic.service.search.WriterSearchService;
import com.homeproject.cineprime.logic.util.PublicIdGenerator;
import com.homeproject.cineprime.view.request_json.WriterRequestJson;
import com.homeproject.cineprime.view.response_json.WriterResponseJson;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WriterService {

    private final WriterRepository writerRepository;
    private final WriterSearchService writerSearchService;

    public WriterService(WriterRepository writerRepository, WriterSearchService writerSearchService) {
        this.writerRepository = writerRepository;
        this.writerSearchService = writerSearchService;
    }


    @Transactional(readOnly = true)
    public List<WriterResponseJson> getAllWriterResponseJson(String type, String search, Optional<Integer> pageSizeOptional, Optional<Integer> pageNumberOptional) throws ResponseStatusException {
        List<Writer> resultSet = new ArrayList<>();
        resultSet = writerSearchService.searchByTypeAndSearch(type, search, pageSizeOptional, pageNumberOptional);

        if(resultSet.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Directors not found with these conditions, try with another!" );
        }

        List<WriterDto> resultWriterDto = resultSet
                .stream()
                .map(WriterMapper::writerToDto)
                .toList();

        return resultWriterDto
                .stream()
                .map(WriterMapper::dtoToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public WriterResponseJson getWriterResponseJsonById(String publicId) throws ResponseStatusException {
        if(StringUtils.isEmpty(publicId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    """
                    The given ID is NULL or empty string,
                    try with another ID! Given ID:
                    """ + publicId);
        }

        Optional<Writer> writer = writerRepository.findByPublicIdAndDeletedAtIsNull(publicId);

        if(writer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Writer not found with ID: " + publicId);
        }

        WriterDto writerDto = new WriterDto();
        writerDto = WriterMapper.writerToDto(writer.get());
        WriterResponseJson returnValue = new WriterResponseJson();
        returnValue = WriterMapper.dtoToResponse(writerDto);

        return returnValue;
    }

    public WriterResponseJson createWriter(WriterRequestJson writerRequestJson) throws ResponseStatusException {
        if(writerRequestJson == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given object not compatible type or null.");
        }

        WriterDto writerDto = new WriterDto();
        writerDto = WriterMapper.requestToDto(writerRequestJson);

        Writer newEntity = new Writer();
        newEntity = WriterMapper.dtoToWriter(writerDto);
        newEntity.setPublicId(PublicIdGenerator.generateId(30));

        Writer savedEntity = writerRepository.save(newEntity);
        WriterDto savedEntityDto = WriterMapper.writerToDto(savedEntity);
        WriterResponseJson returnValue = new WriterResponseJson();
        returnValue = WriterMapper.dtoToResponse(savedEntityDto);

        return returnValue;
    }

    public WriterResponseJson updateWriter(WriterRequestJson writerRequestJson) throws ResponseStatusException {
        if(writerRequestJson == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given object not compatible type or null.");
        }

        Optional<Writer> writerToUpdate = writerRepository.findByPublicIdAndDeletedAtIsNull(writerRequestJson.getPublicId());

        if(writerToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Writer not found with ID: " + writerRequestJson.getPublicId());
        }

        Writer writer = new Writer();
        WriterDto writerDto = new WriterDto();
        writerDto = WriterMapper.requestToDto(writerRequestJson);
        writer = WriterMapper.dtoToWriter(writerDto);

        writer.setId(writerToUpdate.get().getId());
        Writer updatedWriter = writerRepository.save(writer);

        WriterDto savedWriterDto = new WriterDto() ;
        savedWriterDto = WriterMapper.writerToDto(updatedWriter);
        WriterResponseJson returnValue = WriterMapper.dtoToResponse(savedWriterDto);

        return returnValue;
    }

    public String removeWriterByPublicId(String publicId) throws ResponseStatusException {
        if(publicId == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The identifier is null. Give a valid identifier number!"
            );
        }

        Optional<Writer> writer = writerRepository.findByPublicIdAndDeletedAtIsNull(publicId);

        if(writer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Writer not found with ID: " + publicId);
        }

        writerRepository.delete(writer.get());

        return "Writer with identifier: " + publicId + " successfully deleted!";
    }

    public Optional<Writer> findByPublicId(String writerPublicId) {
        return writerRepository.findByPublicIdAndDeletedAtIsNull(writerPublicId);
    }
}
