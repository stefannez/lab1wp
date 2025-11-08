package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;
    public static List<Author> authors = null;

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        authors = new ArrayList<>();
        reservations = new ArrayList<>();

        authors.add(new Author(1L, "Stefan", "Lazarov", "Macedonia", "lorem ipsum..."));
        authors.add(new Author(2L, "John", "Doe", "USA", "lorem ipsum..."));
        authors.add(new Author(3L, "Ana", "Todorovska", "Russia", "lorem ipsum..."));

        books.add(new Book("Book1", "Genre1", 1.0, authors.get(0)));
        books.add(new Book("Book2", "Genre2", 2.0, authors.get(1)));
        books.add(new Book("Book3", "Genre3", 3.0, authors.get(2)));
        books.add(new Book("Book4", "Genre4", 4.0, authors.get(0)));
        books.add(new Book("Book5", "Genre5", 5.0, authors.get(1)));
        books.add(new Book("Book6", "Genre6", 6.0, authors.get(2)));
        books.add(new Book("Book7", "Genre7", 7.0, authors.get(0)));
        books.add(new Book("Book8", "Genre8", 8.0, authors.get(1)));
        books.add(new Book("Book9", "Genre9", 9.0, authors.get(2)));
        books.add(new Book("Book10", "Genre10", 10.0, authors.get(0)));
    }
}
