package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository drepository;

    @Test
    public void findByISBNShouldReturnBook() {
        List<Book> books = repository.findByIsbn("1232323-21");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Ernest Hemingway");
    }

    
    @Test
    public void createNewBook() {
        Category category = new Category("Fiction");
        drepository.save(category);
        Book book = new Book("Animal Bro", "BAP Pramod", "2244443-5", 2025, category);
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteNewBook() {
        List<Book> books = repository.findByIsbn("1232323-21");
        Book book = books.get(0);
        repository.delete(book);
        List<Book> newBooks = repository.findByIsbn("1232323-21");
        assertThat(newBooks).hasSize(0);
    }
}
