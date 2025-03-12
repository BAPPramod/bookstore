package fi.haagahelia.bookstore;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository drepository;

    @Test
    public void findByNameShouldReturnCategory() {
        List<Category> categories = drepository.findByName("Fiction");

        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getId()).isEqualTo(1);
    }

    @Test
    public void createNewCategory() {
        Category category = new Category("Horror");
        drepository.save(category);
        assertThat(category.getId()).isNotNull();
    }

    @Test
    public void deleteNewCategory() {
        List<Category> categories = drepository.findByName("Non-Fiction");
        Category category = categories.get(0);
        drepository.delete(category);
        List<Category> newCategories = drepository.findByName("Non-Fiction");
        assertThat(newCategories).hasSize(0);
    }
}
