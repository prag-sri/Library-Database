package com.example.librarydb;

import com.example.librarydb.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService{
    @Autowired
    BookRepository bookRepository;
    public void createBook(Book book) throws Exception{
        if(bookRepository.findById(book.getId()).get()!=null)
            throw new Exception("Book is already present");
        bookRepository.save(book);
    }

    public Book getBook(Integer id) throws Exception{
        return bookRepository.findById(id).get();
    }

    public void updateBookPages(UpdateBookPage updateBookPage){
        //we are converting our object(entry dto) into entity object
        int id= updateBookPage.getId();
        Book bookToBeUpdated= bookRepository.findById(id).get();

        int newPages= updateBookPage.getPages();
        bookToBeUpdated.setPages(newPages);

        bookRepository.save(bookToBeUpdated);
    }

    public List<ResponseObj> getBookNamesAndAuthors(){
        List<ResponseObj> result= new ArrayList<>();
        for(Book book: bookRepository.findAll())
        {
            ResponseObj obj= new ResponseObj(book.getName(),book.getAuthor());
            result.add(obj);
        }
        return result;
    }
}
