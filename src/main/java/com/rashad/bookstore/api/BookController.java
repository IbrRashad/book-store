package com.rashad.bookstore.api;


import com.rashad.bookstore.dto.BookDetailAdd;
import com.rashad.bookstore.dto.BookDetailDto;
import com.rashad.bookstore.dto.UpdateBookDto;
import com.rashad.bookstore.entity.Book;
import com.rashad.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping(value = "/add",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('PUBLISHER')")
    public ResponseEntity<Book> add(Principal principal, @RequestPart MultipartFile file, BookDetailAdd addBookDto) throws Exception {
        var result = bookService.add(principal, addBookDto, file);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('USER') or hasRole('PUBLISHER')")
    public ResponseEntity<List<BookDetailDto>> getAll(){
        var result = bookService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllByPage")
    @PreAuthorize("hasRole('USER') or hasRole('PUBLISHER')")
    public ResponseEntity<List<BookDetailDto>> getAll(@RequestParam int pageNo, @RequestParam int pageSize){
        var result = bookService.getAll(pageNo-1,pageSize);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllByNameAndPage")
    @PreAuthorize("hasRole('USER') or hasRole('PUBLISHER')")
    public ResponseEntity<List<BookDetailDto>> getAllByNameAndPage(@RequestParam String name, @RequestParam Integer pageNo, @RequestParam Integer pageSize){
        var result = bookService.getAllByName(name,pageNo-1,pageSize);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllPublisherId")
    @PreAuthorize("hasRole('USER') or hasRole('PUBLISHER')")
    public ResponseEntity<List<BookDetailDto>> getAllByPublisherId(@RequestParam int id){
        var result = bookService.getAllByPublisherId(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/update",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('PUBLISHER')")
    public ResponseEntity<Book> update(Principal principal, @RequestPart MultipartFile file, UpdateBookDto updateBookDto) throws Exception {
        var result = bookService.update(principal,updateBookDto,file);
        return ResponseEntity.ok(result);
    }
}
