package com.rashad.bookstore.service;

import com.rashad.bookstore.dto.BookDetailAdd;
import com.rashad.bookstore.dto.BookDetailDto;
import com.rashad.bookstore.dto.UpdateBookDto;
import com.rashad.bookstore.entity.Book;
import com.rashad.bookstore.entity.ERole;
import com.rashad.bookstore.entity.Role;
import com.rashad.bookstore.entity.User;
import com.rashad.bookstore.exception.BookNotFoundException;
import com.rashad.bookstore.exception.PublisherNotFoundException;
import com.rashad.bookstore.exception.UserNotFoundException;
import com.rashad.bookstore.mapper.BookMapper;
import com.rashad.bookstore.repository.BookRepository;
import com.rashad.bookstore.repository.RoleRepository;
import com.rashad.bookstore.repository.UserRepository;
import com.rashad.bookstore.responses.Message;
import com.rashad.bookstore.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImp implements BookService{

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final RoleRepository roleRepository;
    private final BookMapper mapper;
    @Override
    public Book add(Principal principal, BookDetailAdd bookDetailAdd, MultipartFile file) throws Exception {
        User publisher = userRepository.findByUserName(principal.getName()).orElseThrow(() ->
                new UsernameNotFoundException("Username not found with name " + principal.getName()));
        Book book = mapper.bookDetailAddToBook(bookDetailAdd);
        book.setImage(FileUploader.upload(file));
        book.setPublisher(publisher);
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book update(Principal principal, UpdateBookDto updateBookDto, MultipartFile file) throws Exception {
        User publisher = userRepository.findByUserName(principal.getName()).orElseThrow(() ->
                new UsernameNotFoundException("Username not found with name " + principal.getName()));

        Book book = bookRepository.findById(updateBookDto.getId())
                .orElseThrow(() -> new BookNotFoundException(Message.BOOK_NOT_FOUND));
        if (!publisher.getBooks().contains(book)){
            throw new BookNotFoundException(Message.BOOK_NOT_FOUND);
        }
        book = mapper.updateBookDtoToBook(updateBookDto);
        book.setImage(FileUploader.upload(file));
        book.setPublisher(publisher);
        bookRepository.save(book);
        return book;
    }

    @Override
    public List<BookDetailDto> getAll() {
        var books = bookRepository.findAll();
        return mapper.booksToBookDetailsDto(books);
    }

    @Override
    public List<BookDetailDto> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        var books = bookRepository.findAll(pageable).getContent();
        return mapper.booksToBookDetailsDto(books);
    }

    @Override
    public List<BookDetailDto> getAllByName(String name, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        var books = bookRepository.getByNameContainingIgnoreCase(name,pageable).getContent();
        return mapper.booksToBookDetailsDto(books);
    }


    @Override
    public BookDetailDto getById(int id) {
        var book = bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException(Message.ROLE_NOT_FOUND));
        return mapper.bookToBookDetailDto(book);
    }

    @Override
    public List<BookDetailDto> getAllByPublisherId(int id) {
        var publisher = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(Message.USER_NOT_FOUND));

        Role rolePublisher = roleRepository.findByName(ERole.ROLE_PUBLISHER)
                .orElse(Role.builder().name(ERole.ROLE_PUBLISHER).build());

        var roles = publisher.getRoles();
        if (!roles.contains(rolePublisher)){
            throw new PublisherNotFoundException(Message.PUBLISHER_NOT_FOUND);
        }

        var books = bookRepository.findAllByPublisher(publisher);
        return mapper.booksToBookDetailsDto(books);
    }


}
