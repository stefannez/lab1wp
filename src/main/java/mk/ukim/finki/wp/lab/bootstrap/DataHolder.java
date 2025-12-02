package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataHolder(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        // Проверка дали базата е празна пред полнење
        if (authorRepository.count() == 0 && bookRepository.count() == 0) {

            // 1. ПОЛНЕЊЕ НА АВТОРИ И НИВНО ЗАЧУВУВАЊЕ
            Author author1 = new Author("Stefan", "Lazarov", "Macedonia");
            Author author2 = new Author("John", "Doe", "USA");
            Author author3 = new Author("Ana", "Todorovska", "Bulgaria");

            author1 = authorRepository.save(author1);
            author2 = authorRepository.save(author2);
            author3 = authorRepository.save(author3);

            // 2. ПОЛНЕЊЕ НА КНИГИ
            // Користи ги перзистираните Author објекти (author1, author2, author3)
            bookRepository.save(new Book("Book1", "Genre1", 1.0, author1));
            bookRepository.save(new Book("Book2", "Genre2", 2.0, author2));
            bookRepository.save(new Book("Book3", "Genre3", 3.0, author3));
            bookRepository.save(new Book("Book4", "Genre4", 4.0, author1));
            bookRepository.save(new Book("Book5", "Genre5", 5.0, author2));
            bookRepository.save(new Book("Book6", "Genre6", 6.0, author3));
            bookRepository.save(new Book("Book7", "Genre7", 7.0, author1));
            bookRepository.save(new Book("Book8", "Genre8", 8.0, author2));
            bookRepository.save(new Book("Book9", "Genre9", 9.0, author3));
            bookRepository.save(new Book("Book10", "Genre10", 10.0, author1));
        }
    }
}
