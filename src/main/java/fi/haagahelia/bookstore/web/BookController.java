package fi.haagahelia.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final CategoryRepository categoryRepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    // RESTful service to create a new book
    @PostMapping("/api/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/booklist")
    public String showBookList(Model model) {
        List<Book> books = (List<Book>) bookRepository.findAll();
        model.addAttribute("books", books);
        return "booklist";
    }

    // RESTful service to get all students
    @RequestMapping(value = "/books")
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) bookRepository.findAll();
    }

    // RESTful service to get student by id
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
        return bookRepository.findById(id);
    }

    @GetMapping("/addbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(@RequestParam String title,
            @RequestParam String author,
            @RequestParam String isbn,
            @RequestParam int publicationYear,
            @RequestParam Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        Book book = new Book(title, author, isbn, publicationYear, category);
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }

    // RESTful service to delete a book by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/api/books/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteBookRest(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("book", book);
        model.addAttribute("categories", categories);
        return "editbook";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookRepository.save(book);
        return "redirect:/booklist";
    }
}
