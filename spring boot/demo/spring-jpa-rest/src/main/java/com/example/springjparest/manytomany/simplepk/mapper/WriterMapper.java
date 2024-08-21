package com.example.springjparest.manytomany.simplepk.mapper;

import com.example.springjparest.manytomany.simplepk.model.Writer;
import com.example.springjparest.manytomany.simplepk.rest.dto.CreateBookWritersRequest;
import com.example.springjparest.manytomany.simplepk.rest.dto.CreateWriterBooksRequest;
import com.example.springjparest.manytomany.simplepk.rest.dto.WriterResponse;
import com.example.springjparest.manytomany.simplepk.rest.dto.CreateWriterRequest;
import com.example.springjparest.manytomany.simplepk.rest.dto.UpdateWriterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface WriterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Writer toWriter(CreateWriterRequest createWriterRequest);

    WriterResponse toWriterResponse(Writer writer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateWriterFromRequest(UpdateWriterRequest updateWriterRequest, @MappingTarget Writer writer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    @Mapping(target = "name", source = "writerName")
    Writer createWriteFromWriteBooks(CreateWriterBooksRequest request);
}
