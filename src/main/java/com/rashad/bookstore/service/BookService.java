package com.rashad.bookstore.service;

import com.rashad.bookstore.dto.BookDetailAdd;
import com.rashad.bookstore.dto.BookDetailDto;
import com.rashad.bookstore.dto.UpdateBookDto;
import com.rashad.bookstore.entity.Book;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface BookService {
    Book add(Principal principal, BookDetailAdd addBookDto, MultipartFile file) throws Exception;
    Book update(Principal principal, UpdateBookDto updateBookDto, MultipartFile file) throws Exception;
    List<BookDetailDto> getAll();
    List<BookDetailDto> getAll(int pageNo,int pageSize);
    List<BookDetailDto> getAllByName(String name, int pageNo,int pageSize);
    BookDetailDto getById(int id);
    List<BookDetailDto> getAllByPublisherId(int id);
}
