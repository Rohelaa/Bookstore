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

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTests {

	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void createNewBook() {
		Book newBook = new Book("1984", "George Orwell", "1949", "-", 4.99, new Category("Dystopian fiction"));
		bookRepository.save(newBook);
		assertThat(newBook.getId()).isNotNull();
	}
}
