package com.example.springjparest.onetoone.sharedpk.mapper;

import com.example.springjparest.onetoone.sharedpk.model.Person;
import com.example.springjparest.onetoone.sharedpk.model.PersonDetail;
import com.example.springjparest.onetoone.sharedpk.rest.dto.CreatePersonAllFieldRequest;
import com.example.springjparest.onetoone.sharedpk.rest.dto.CreatePersonDetailRequest;
import com.example.springjparest.onetoone.sharedpk.rest.dto.CreatePersonRequest;
import com.example.springjparest.onetoone.sharedpk.rest.dto.PersonResponse;
import com.example.springjparest.onetoone.sharedpk.rest.dto.UpdatePersonAllFieldRequest;
import com.example.springjparest.onetoone.sharedpk.rest.dto.UpdatePersonDetailRequest;
import com.example.springjparest.onetoone.sharedpk.rest.dto.UpdatePersonRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "personDetail", ignore = true)
    Person toPerson(CreatePersonRequest createPersonRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "personDetail.description", source = "description")
    @Mapping(target = "name", source = "name")
    Person createPersonFromRequest(CreatePersonAllFieldRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", source = "description")
    @Mapping(target = "person.name", source = "name")
    PersonDetail createPersonDetailFromRequest(CreatePersonAllFieldRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "personDetail.description", source = "description")
    @Mapping(target = "name", source = "name")
    Person updatePersonFromRequest(UpdatePersonAllFieldRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", source = "description")
    @Mapping(target = "person.name", source = "name")
    PersonDetail updatePersonDetailFromRequest(UpdatePersonAllFieldRequest request);

    PersonResponse toPersonResponse(Person person);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "personDetail", ignore = true)
    void updatePersonFromRequest(UpdatePersonRequest updatePersonRequest, @MappingTarget Person person);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    PersonDetail toPersonDetail(CreatePersonDetailRequest createPersonDetailRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    void updatePersonDetailFromRequest(UpdatePersonDetailRequest updatePersonDetailRequest,
                                       @MappingTarget PersonDetail personDetail);
}
