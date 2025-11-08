package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Service;

@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final BookReservationRepository reservationRepository;

    public BookReservationServiceImpl(BookReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, Long numCopies, String clientIp) {
        BookReservation reservation = new BookReservation(bookTitle, readerName, readerAddress, numCopies, clientIp);
        DataHolder.reservations.add(reservation);
        return reservation;
    }
}
