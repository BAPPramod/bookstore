package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
    public CommandLineRunner demo(BookRepository repository) {
        return (args) -> {
            repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", "HHB00397", 1929));
            repository.save(new Book("Animal Farm", "George Orwell", "AF1945", 1945));
            System.out.println("Sample books added!");
        };
    }

}
	