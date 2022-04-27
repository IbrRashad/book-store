package com.rashad.bookstore.mapper;

import com.rashad.bookstore.dto.BookDetailAdd;
import com.rashad.bookstore.dto.BookDetailDto;
import com.rashad.bookstore.dto.UpdateBookDto;
import com.rashad.bookstore.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Primary
@Component("customBookMapperImpl")
@RequiredArgsConstructor
public class BookMapper implements IBookMapper{

    @Override
    public Book bookDetailAddToBook(BookDetailAdd bookDetailAdd) {
        if (bookDetailAdd == null) {
            return null;
        }

        Book book = new Book();
        book.setName(bookDetailAdd.getName());
        book.setAuthor(bookDetailAdd.getAuthor());
        book.setPrice(bookDetailAdd.getPrice());
        book.setDescription(bookDetailAdd.getDescription());
        return book;

    }

    @Override
    public List<BookDetailDto> booksToBookDetailsDto(List<Book> books) {
        if (books==null){
            return null;
        }

        var list = new ArrayList<BookDetailDto>(books.size());

        for (Book book : books){
            list.add(bookToBookDetailDto(book));
        }
        return list;
    }

    @Override
    public BookDetailDto bookToBookDetailDto(Book book) {
        if (book==null){
            return null;
        }

        var publisherName = book.getPublisher().getUserName();

        BookDetailDto bookDetailDto = new BookDetailDto();
        bookDetailDto.setPublisherName(publisherName);
        bookDetailDto.setName(book.getName());
        bookDetailDto.setAuthor(book.getAuthor());
        bookDetailDto.setPrice(book.getPrice());
        bookDetailDto.setImage(book.getImage());
        bookDetailDto.setDescription(book.getDescription());
        return bookDetailDto;
    }

    @Override
    public Book updateBookDtoToBook(UpdateBookDto updateBookDto) {
        return null;
    }
}
