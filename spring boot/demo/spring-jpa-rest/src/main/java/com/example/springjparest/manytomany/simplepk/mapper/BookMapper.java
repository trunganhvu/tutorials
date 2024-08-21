package com.example.springjparest.manytomany.simplepk.mapper;

import com.example.springjparest.manytomany.simplepk.rest.dto.BookResponse;
import com.example.springjparest.manytomany.simplepk.rest.dto.CreateBookWritersRequest;
import com.example.springjparest.manytomany.simplepk.rest.dto.UpdateBookRequest;
import com.example.springjparest.manytomany.simplepk.model.Book;
import com.example.springjparest.manytomany.simplepk.rest.dto.CreateBookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "writers", ignore = true)
    Book toBook(CreateBookRequest createBookRequest);

    BookResponse toBookResponse(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "writers", ignore = true)
    void updateBookFromRequest(UpdateBookRequest updateBookRequest, @MappingTarget Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "writers", ignore = true)
    @Mapping(target = "name", source = "bookName")
    Book createBookFromBookWriters(CreateBookWritersRequest request);
}
