package fi.haagahelia.bookstore.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/booklist")
    public String showBookList(Model model) {
        List<Book> books = (List<Book>) bookRepository.findAll();
        model.addAttribute("books", books);
        return "booklist";
    }



    /* @GetMapping("/index")
    public String showBookList(Model model) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Sample", "Sample", 2020, "1234567", 30.99));
        books.add(new Book("Sample", "Sample", 2022, "3267899", 29.99));

        model.addAttribute("books", books);

        return "index";
    } */


 /* @RequestMapping("/index")
    public String index() {
    return "Welcome to the Bookstore!";
    } */
}
