package com.rashad.bookstore.mapper;

import com.rashad.bookstore.dto.BookDetailAdd;
import com.rashad.bookstore.dto.BookDetailDto;
import com.rashad.bookstore.dto.UpdateBookDto;
import com.rashad.bookstore.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IBookMapper {
    Book bookDetailAddToBook(BookDetailAdd bookDetailAdd);
    List<BookDetailDto> booksToBookDetailsDto(List<Book> books);
    BookDetailDto bookToBookDetailDto(Book book);
    Book updateBookDtoToBook(UpdateBookDto updateBookDto);

}
