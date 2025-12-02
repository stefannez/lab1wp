package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthor_Id(Long authorId);

    // 💡 Нов метод за пребарување: Наслов кој содржи (Ignoring case) И Рејтинг поголем или еднаков
    List<Book> findAllByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(String title, Double averageRating);

    // Доколку нема рејтинг, пребарува само по наслов
    List<Book> findAllByTitleContainingIgnoreCase(String title);

    // Доколку нема наслов, пребарува само по рејтинг
    List<Book> findAllByAverageRatingGreaterThanEqual(Double averageRating);
}
