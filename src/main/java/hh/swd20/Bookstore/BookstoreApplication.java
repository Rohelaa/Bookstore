package hh.swd20.Bookstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;
import hh.swd20.Bookstore.domain.User;
import hh.swd20.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookdemo(BookRepository repository, CategoryRepository categoryRepository, UserRepository userRepository) {
		
		
		return (args) -> {
			
			log.info("let's add some categories");
			categoryRepository.save(new Category("Historia"));
			categoryRepository.save(new Category("Psykologia"));
			
			log.info("let's save few books");
			repository.save(new Book("12 elämänohjetta", "Jordan B. Peterson", "2018", "34343", 24.99, categoryRepository.findByName("Psykologia")));
			repository.save(new Book("Sapiens", "Yuval Noah Harari", "2011", "b456", 10.95, categoryRepository.findByName("Historia")));
	
			log.info("Fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			
			User user1 = new User("user", "$2a$10$b69ITphqZwXH6oKk0.mfuuDtKAfnocmvlTC8BwvGwfsXoSCCDEiDC", "user1@user.com", "USER");
			User user2 = new User("admin", "$2a$10$EKKCE5N7FG1djy/VptmkCuRGggKDnn0Q01Y2LTBjDWiGg4j0voYhO", "user2@user.com", "ADMIN");
			
			userRepository.save(user1);
			userRepository.save(user2);
			
			
		};
	}

}
