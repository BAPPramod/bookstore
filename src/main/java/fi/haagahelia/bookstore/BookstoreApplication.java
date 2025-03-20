package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;
import jakarta.transaction.Transactional;
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
	@Transactional
	public CommandLineRunner demo(CategoryRepository categoryRepo, BookRepository repository,
			UserRepository userRepository) {
		return (args) -> {

			/* User user1 = new User("user",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"user@bookstore.com", "USER");
			User user2 = new User("admin",
					"$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"admin@bookstore.com", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);

			Category fiction = new Category("Fiction");
			Category nonFiction = new Category("Non-Fiction");
			categoryRepo.save(fiction);
			categoryRepo.save(nonFiction);

			repository.save(new Book("A Farewell to Arms", "Ernest Hemingway",
					"1232323-21", 1929, fiction));
			repository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 1945,
					nonFiction)); */

			System.out.println("Sample categories and books added!");
		};
	}

}
