package com.example.junitrestapiapplication.com.rest.api;


import com.example.junitrestapiapplication.Model.BookEntity;
import com.example.junitrestapiapplication.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;


    @GetMapping
    public List<BookEntity> getAllBookRecords(){
        return bookRepository.findAll();
    }

    @GetMapping(value = "{bookId}")
    public BookEntity getBookbyId(@PathVariable (value = "bookId") Long bookId){
        return bookRepository.findById(bookId).get();
    }

    @PostMapping
    public BookEntity createBookRecord(@RequestBody @Validated BookEntity bookRecord){
        return bookRepository.save(bookRecord);
    }

    @PutMapping
    public BookEntity updateBookRecord(@RequestBody @Validated BookEntity bookEntity) throws FileNotFoundException {
        if(bookEntity == null || bookEntity.getBookId() == 0){
            throw new FileNotFoundException("Book record is not avilable");
        }

        Optional<BookEntity> optionalBook = bookRepository.findById(bookEntity.getBookId());
        if(!optionalBook.isPresent()){
            throw new FileNotFoundException("Book with Id" +bookEntity.getBookId() + " doest not exist");
        }

        BookEntity existingBookRecord = optionalBook.get();
        existingBookRecord.setBookName(bookEntity.getBookName());
        existingBookRecord.setSummary(bookEntity.getSummary());
        existingBookRecord.setRating(bookEntity.getRating());
        return  bookRepository.save(existingBookRecord);
    }

    //ToDo Write / delete end point  using TDD method

    @DeleteMapping(value = "{bookId}")
    public void deleteBookbyId(@PathVariable(value = "bookId") Long bookId) throws FileNotFoundException{
        if(!bookRepository.findById(bookId).isPresent()){
            throw new FileNotFoundException("book Id" + bookId +"Notfound");
        }
        bookRepository.deleteById(bookId);
    }


}
