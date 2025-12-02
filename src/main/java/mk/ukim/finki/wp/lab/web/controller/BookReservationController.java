package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookReservationController {

    private final BookService bookService;
    private final BookReservationService bookReservationService;

    public BookReservationController(BookService bookService, BookReservationService bookReservationService) {
        this.bookService = bookService;
        this.bookReservationService = bookReservationService;
    }

    @PostMapping("/bookReservation")
    public String reserveBook(
            // 💡 Ги задржуваме само bookId и readerName/username
            // Ги отстрануваме readerAddress, numCopies, clientIp (освен ако не се користат на друго место)
            @RequestParam String readerName,
            @RequestParam Long bookId,
            Model model) {

        // 1. Податоците за книгата се потребни само за приказ или проверка,
        // но не се потребни за повикот на placeReservation, бидејќи сервисот ја наоѓа книгата по ID.
        Book selectedBook = bookService.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        // 2. 💡 КОРЕКЦИЈА: Повикување на JPA сервисот со ID и username
        BookReservation reservation = bookReservationService.placeReservation(
                bookId,
                readerName // Го користиме readerName како username
        );

        model.addAttribute("reservation", reservation);
        // Можеби ќе треба да го додадеш и selectedBook ако reservationConfirmation.html го бара
        model.addAttribute("book", selectedBook);

        return "reservationConfirmation";
    }
}
