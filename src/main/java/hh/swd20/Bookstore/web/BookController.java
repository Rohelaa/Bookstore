package hh.swd20.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping("/index")
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping("/booklist")
	public String getBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	// RESTful palvelut
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> getBooksRest() {
		return (List<Book>) bookRepository.findAll();
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> getBookRest(@PathVariable("id") Long bookId) {
		return bookRepository.findById(bookId);
	}
	
	@RequestMapping("/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}
	
	// PathVariable poimii urlista muuttujan id, joka talletetaan muuttujaan bookId
	// tämän perusteella poistetaan reposta sinne talletettu Book-olio
	
	
	@RequestMapping(value = "/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId) { 
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
		
	}
	
	@RequestMapping(value = "/edit/{id}" )
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		return "editbook";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
}
