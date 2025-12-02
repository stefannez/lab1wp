//package mk.ukim.finki.wp.lab.repository.mock;
//
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Author;
//import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class InMemoryAuthorRepository implements AuthorRepository {
//
//    @Override
//    public List<Author> findAll() {
//        return DataHolder.authors;
//    }
//
//    @Override
//    public Author findById(Long id) {
//        return DataHolder.authors.stream()
//                .filter(a -> a.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//}
