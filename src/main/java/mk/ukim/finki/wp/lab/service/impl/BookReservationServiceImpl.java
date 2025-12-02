package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.jpa.BookReservationRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService; // 👈 НОВ IMPORT
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final BookReservationRepository reservationRepository;
    private final BookService bookService; // 👈 Додаваме BookService за да ја најдеме книгата

    // 💡 Ажуриран конструктор
    public BookReservationServiceImpl(
            BookReservationRepository reservationRepository,
            BookService bookService) {
        this.reservationRepository = reservationRepository;
        this.bookService = bookService;
    }

    // 💡 Ажуриран потпис (користејќи Book ID, Username, и другите полиња)
    @Override
    public BookReservation placeReservation(Long bookId, String username) {

        // 1. Најди ја книгата од базата со помош на BookService
        Book book = bookService.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));

        // 2. Креирај го новиот JPA ентитет
        // Ги користиме само book и username, бидејќи тоа се зачувува во моделот.
        BookReservation reservation = new BookReservation(
                book,
                username,
                LocalDateTime.now() // Го користиме LocalDateTime.now() за време на резервацијата
        );

        // 3. Зачувај го ентитетот во базата
        return reservationRepository.save(reservation);
    }
}