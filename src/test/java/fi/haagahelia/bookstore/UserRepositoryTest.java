package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository drepository;

    @Test
    public void findByNameShouldReturnUser() {
        User user = drepository.findByUsername("admin");

        assertThat(user).isNotNull();
        assertThat(user.getRole()).isEqualTo("ADMIN");
    }

    @Test
    public void createNewUser() {
        User user = new User("cashier", "$2a$10$3Zz4Z6", "cashier@bookstore.com", "CASHIER");
        drepository.save(user);
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void deleteNewUser() {
        User user = drepository.findByUsername("user");
        drepository.delete(user);
        User newUser = drepository.findByUsername("user");
        assertThat(newUser).isNull();
    }
}
