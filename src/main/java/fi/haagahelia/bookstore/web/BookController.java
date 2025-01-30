package fi.haagahelia.bookstore.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fi.haagahelia.bookstore.domain.Book;

@Controller
public class BookController {

    @GetMapping("/index")
    public String showBookList(Model model) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Sample", "Sample", 2020, "1234567", 30.99));
        books.add(new Book("Sample", "Sample", 2022, "3267899", 29.99));

        model.addAttribute("books", books);

        return "index";
    }


 /* @RequestMapping("/index")
    public String index() {
    return "Welcome to the Bookstore!";
    } */
}
