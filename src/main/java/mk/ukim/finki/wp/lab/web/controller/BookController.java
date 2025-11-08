package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/books", "/", ""})
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    // 4.1 Listing - GET /books
    @GetMapping
    public String getBooksPage(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) Double minRating,
            Model model) {

        List<Book> books;

        if ((searchText != null && !searchText.isEmpty()) || minRating != null) {
            books = bookService.searchBooks(searchText, minRating);
        } else {
            books = bookService.listAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("authors", authorService.findAll());

        if (error != null) {
            model.addAttribute("error", error);
        }

        // За да се задржат вредностите во формата после пребарување
        model.addAttribute("searchText", searchText);
        model.addAttribute("minRating", minRating);

        return "listBooks";
    }


    // 7. Get add form - GET /books/book-form
    @GetMapping("/book-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    // 7. Get edit form - GET /books/book-form/{id}
    @GetMapping("/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        return bookService.findById(id)
                .map(book -> {
                    model.addAttribute("book", book);
                    model.addAttribute("authors", authorService.findAll());
                    return "book-form";
                })
                .orElse("redirect:/books?error=BookNotFound");
    }

    // 4.2 Add new book - POST /books/add
    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        Author author = authorService.findById(authorId);
        bookService.save(title, genre, averageRating, author);
        return "redirect:/books";
    }

    // 4.3 Edit book - POST /books/edit/{bookId}
    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        Author author = authorService.findById(authorId);
        bookService.update(bookId, title, genre, averageRating, author);
        return "redirect:/books";
    }

    // 4.4 Delete book - POST /books/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
