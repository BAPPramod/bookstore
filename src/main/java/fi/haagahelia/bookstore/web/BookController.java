package fi.haagahelia.bookstore.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/addbook")
    public String showAddBookForm() {
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(@RequestParam String title,
            @RequestParam String author,
            @RequestParam String isbn,
            @RequestParam int publicationYear) {
        Book book = new Book(title, author, isbn, publicationYear);
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }
}
