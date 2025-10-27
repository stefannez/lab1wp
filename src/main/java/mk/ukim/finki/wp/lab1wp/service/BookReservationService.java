package mk.ukim.finki.wp.lab1wp.service;

import mk.ukim.finki.wp.lab1wp.model.BookReservation;

public interface BookReservationService {
    BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies);
}
