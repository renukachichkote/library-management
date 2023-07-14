package com.example.librarymanagement.common.client;

import com.example.librarymanagement.common.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "book-service")
public interface BookServiceClient {

    @GetMapping("/book/{bookId}")
    Book getBookById(@PathVariable("bookId") Long bookId);

    @PutMapping("/book/{id}")
    ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book);
}
