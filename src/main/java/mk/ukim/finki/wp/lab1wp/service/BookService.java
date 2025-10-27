package mk.ukim.finki.wp.lab1wp.service;

import mk.ukim.finki.wp.lab1wp.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
}
