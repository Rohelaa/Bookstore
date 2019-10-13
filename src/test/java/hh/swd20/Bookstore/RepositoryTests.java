package hh.swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	// Bookrepo tests
	@Test
	public void createNewBook() {
		Book newBook = new Book("1984", "George Orwell", "1949", "-", 4.99, new Category("Dystopian fiction"));
		bookRepository.save(newBook);
		assertThat(newBook.getId()).isNotNull();
	}
	
	// CategoryRepo tests
	@Test
	public void findByNameShouldReturnCategory() {
		Category category = categoryRepository.findByName("Historia");
		assertThat(category.getId()).isNotNull();
		assertThat(categoryRepository.findByName("Historia")).getClass().equals(Category.class);
	}
	
}
