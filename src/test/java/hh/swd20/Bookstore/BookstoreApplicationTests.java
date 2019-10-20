package hh.swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.Bookstore.web.BookController;

import hh.swd20.Bookstore.web.UserDetailServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTests {
	
	@Autowired
	private BookController controller;
	@Autowired
	private UserDetailServiceImpl userDetailService;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(userDetailService).isNotNull();
	}

}
