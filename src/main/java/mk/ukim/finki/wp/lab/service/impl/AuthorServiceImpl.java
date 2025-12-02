package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    // Вбризгување на новиот JpaRepository
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        // Користиме JpaRepository метод
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        // Користиме JpaRepository метод
        return authorRepository.findById(id);
    }

    // Додајте го и методот за зачувување ако го немате
    @Override
    public Author save(String name, String surname, String country) {
        Author author = new Author(name, surname, country);
        return authorRepository.save(author);
    }
}
