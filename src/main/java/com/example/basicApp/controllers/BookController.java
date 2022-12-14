package com.example.basicApp.controllers;

import com.example.basicApp.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BookController {
    @GetMapping(value = "/books")
    public List<Book> getAllBooks(){
        return Arrays.asList(new Book(1l, "shooting stars", "tite kibo"));
    }
}
