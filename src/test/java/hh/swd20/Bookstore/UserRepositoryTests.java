package hh.swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.Bookstore.domain.User;
import hh.swd20.Bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void createNewUser() {
		User user = new User("user1", "password", "user1@user.com", "USER");
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void findByUsernameShouldReturnUser() {
		userRepository.save(new User("user2", "password", "user2@user.com", "USER"));
		User user = userRepository.findByUsername("user2");
		assertThat(user.getId()).isNotNull();
	}

}
