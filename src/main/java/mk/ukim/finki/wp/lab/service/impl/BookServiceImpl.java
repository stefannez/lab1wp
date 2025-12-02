package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    // Ова ни треба за да можеме да зачуваме нова книга
    private final AuthorRepository authorRepository;

    // Вбризгување на JpaRepositories
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        // Користиме JpaRepository метод
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        // Користиме JpaRepository метод
        return bookRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        // Користиме JpaRepository метод
        bookRepository.deleteById(id);
    }

    // save() метод: Зачувува нова книга
    @Override
    public Book save(String title, String genre, Double averageRating, Author author) {
        Book newBook = new Book(title, genre, averageRating, author);
        return bookRepository.save(newBook);
    }

    // update() метод: Ажурира постоечка книга
    @Override
    public Book update(Long id, String title, String genre, Double averageRating, Author author) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        return bookRepository.save(book); // Hibernate го детектира update
    }

    // Имплементација на бараниот findAllByAuthor_Id метод
    public List<Book> findAllByAuthor(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }
    @Override
    public List<Book> searchBooks(String searchText, Double minRating) {
        if ((searchText != null && !searchText.isEmpty()) && minRating != null) {
            // Пребарување по наслов и рејтинг
            return bookRepository.findAllByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(searchText, minRating);
        } else if (searchText != null && !searchText.isEmpty()) {
            // Пребарување само по наслов
            return bookRepository.findAllByTitleContainingIgnoreCase(searchText);
        } else if (minRating != null) {
            // Пребарување само по рејтинг
            return bookRepository.findAllByAverageRatingGreaterThanEqual(minRating);
        } else {
            // Нема филтри - врати сè
            return bookRepository.findAll();
        }
    }
}
