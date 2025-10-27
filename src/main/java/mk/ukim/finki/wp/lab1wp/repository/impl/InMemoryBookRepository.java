package mk.ukim.finki.wp.lab1wp.repository.impl;

import mk.ukim.finki.wp.lab1wp.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab1wp.model.Book;
import mk.ukim.finki.wp.lab1wp.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository implements BookRepository {

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream().filter(book -> (text == null ||
                book.getTitle().toLowerCase().contains(text.toLowerCase())) &&
                (rating == null || book.getAverageRating() >= rating)).collect(Collectors.toList());
    }
}
