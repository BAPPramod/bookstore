package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CategoryRepository categoryRepo, BookRepository repository) {
		return (args) -> {
			Category fiction = new Category("Fiction");
            Category nonFiction = new Category("Non-Fiction");
            categoryRepo.save(fiction);
            categoryRepo.save(nonFiction);

			repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", "1232323-21", 1929, fiction));
			repository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 1945, nonFiction));
			
			System.out.println("SSample categories and books added!");
		};
	}

}
