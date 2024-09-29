package com.homeproject.cineprime.logic.mapper;

import com.homeproject.cineprime.domain.model.PersonData;
import com.homeproject.cineprime.domain.model.Writer;
import com.homeproject.cineprime.logic.dto.WriterDto;
import com.homeproject.cineprime.view.request_json.WriterRequestJson;
import com.homeproject.cineprime.view.response_json.WriterResponseJson;

public class WriterMapper {
    public static WriterDto writerToDto(Writer writer) {

        if(writer == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        PersonData personData = writer.getPersonData();

        WriterDto returnValue = new WriterDto();
        returnValue.setId(writer.getId());
        returnValue.setPublicId(writer.getPublicId());
        returnValue.setFirstName(personData.getFirstName());
        returnValue.setLastName(personData.getLastName());
        returnValue.setAge(personData.getAge());
        returnValue.setBithDate(personData.getBirthdate());

        return returnValue;
    }

    public static WriterResponseJson dtoToResponse(WriterDto writerDto) {

        if(writerDto == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        WriterResponseJson returnValue = new WriterResponseJson();
        returnValue.setPublicId(writerDto.getPublicId());
        returnValue.setFirstName(writerDto.getFirstName());
        returnValue.setLastName(writerDto.getLastName());
        returnValue.setAge(writerDto.getAge());
        returnValue.setBirthDate(writerDto.getBithDate());

        return returnValue;

    }

    public static WriterDto requestToDto(WriterRequestJson writerRequestJson) {

        if(writerRequestJson == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        WriterDto returnValue = new WriterDto();
        returnValue.setPublicId(writerRequestJson.getPublicId());
        returnValue.setFirstName(writerRequestJson.getFirstName());
        returnValue.setLastName(writerRequestJson.getLastName());
        returnValue.setAge(writerRequestJson.getAge());
        returnValue.setBithDate(writerRequestJson.getBirthDate());

        return returnValue;

    }

    public static Writer dtoToWriter(WriterDto writerDto) {

        if(writerDto == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        Writer returnValue = new Writer();
        returnValue.setId(writerDto.getId());
        returnValue.setPublicId(writerDto.getPublicId());
        returnValue.setPersonData(new PersonData(
                writerDto.getFirstName(),
                writerDto.getLastName(),
                writerDto.getAge(),
                writerDto.getBithDate()
        ));

        return returnValue;
    }
}
