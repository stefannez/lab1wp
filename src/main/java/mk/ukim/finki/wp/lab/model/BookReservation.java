package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "book_reservations")
public class BookReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Релација со книгата која е резервирана.
     * @ManyToOne: Многу резервации може да се однесуваат на една книга.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    private String username;
    private LocalDateTime reservationTime;


    public BookReservation() {

    }

    public BookReservation(Book book, String username, LocalDateTime reservationTime) {
        this.book = book;
        this.username = username;
        this.reservationTime = reservationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }
}