package mk.ukim.finki.wp.lab1wp.service.impl;

import mk.ukim.finki.wp.lab1wp.model.BookReservation;
import mk.ukim.finki.wp.lab1wp.repository.BookReservationRepository;
import mk.ukim.finki.wp.lab1wp.service.BookReservationService;
import org.springframework.stereotype.Service;

@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final BookReservationRepository reservationRepository;

    public BookReservationServiceImpl(BookReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        BookReservation reservation = new BookReservation(bookTitle, readerName, readerAddress, (long) numberOfCopies);
        return reservationRepository.save(reservation);
    }
}
