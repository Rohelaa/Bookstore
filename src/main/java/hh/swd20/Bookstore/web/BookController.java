package hh.swd20.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@RequestMapping("/index")
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping("/booklist")
	public String getBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	@RequestMapping("/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
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
	public String deleteBook(@PathVariable("id") Long bookId) { 
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
		
	}
	
	@RequestMapping(value = "/edit/{id}" )
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		return "editbook";
	}
}
