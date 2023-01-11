package com.example.librarydb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add_book")
    public void addBook(@RequestBody Book book){
        try {
            bookService.createBook(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get_book")
    public Book getBook(@RequestParam("id")Integer id){
        try {
            return bookService.getBook(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update_book")
    public void updateBookPages(@RequestBody() UpdateBookPage updateBookPage){
        bookService.updateBookPages(updateBookPage);
    }

    //list of book names inside db
    @GetMapping("/get_books_names")
    public List<ResponseObj> getBookNames(){
        return bookService.getBookNames();
    }
}
