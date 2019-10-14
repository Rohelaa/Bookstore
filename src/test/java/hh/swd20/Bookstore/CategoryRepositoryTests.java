package hh.swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTests {

	@Autowired 
	private CategoryRepository categoryRepository;
	
	// CategoryRepo tests
	
	// testaa palauttaako findByName-metodi Category-luokan olion 
	@Test
	public void findByNameShouldReturnCategory() {
		Category category = categoryRepository.findByName("Historia");
		assertThat(category.getClass().equals(Category.class));
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Science fiction");
		categoryRepository.save(category);
		assertThat(category.getId()).isNotNull();
	}
	
}
