package book.interest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import book.interest.controller.model.BookData;
import book.interest.controller.model.MediumData;
import book.interest.service.BookService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/book_interest")
@Slf4j
public class BookController {
	
	
//This is the Controller class which contains the CRUD operations with methods
//that are called from the BookService class. 
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/medium")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MediumData insertMedium(@RequestBody MediumData mediumData) {
		log.info("Creating medium{}", mediumData);
	return bookService.saveMedium(mediumData);
	}
	
	@PutMapping("/medium/{mediumId}")
	public MediumData updateContributor(@PathVariable Long mediumId,
			@RequestBody MediumData mediumData) {
		mediumData.setMediumId(mediumId);
		log.info("Updating contributor {}", mediumData);
		return bookService.saveMedium(mediumData);
	}

	@GetMapping("/medium")
	public List<MediumData> retrieveAllMediums() {
		log.info("Retrieve all mediums called.");
		return bookService.retrieveAllMediums();
				}
	
	@GetMapping("/medium/{mediumId}")
	public MediumData retrieveMediumById(@PathVariable Long mediumId) {
		log.info("Retrieving contributor with ID={}", mediumId);
		return bookService.retrieveMediumById(mediumId);
	}

	@DeleteMapping("/medium/{mediumId}")
	public Map<String, String> deleteMediumById(
			@PathVariable Long mediumId){
		log.info("Deleting medium with ID=()", mediumId);
		
		bookService.deleteMediumById(mediumId);
		
		return Map.of("message", "Deletion of medium with ID=" + mediumId + " was successful.");
	}
	
	@PostMapping("/medium/{mediumId}/book")
	@ResponseStatus(code = HttpStatus.CREATED)
	public BookData insertBook(@PathVariable Long mediumId, 
			@RequestBody BookData bookData) {

		log.info("Creating book {} for book with ID={}", mediumId, bookData);
		return bookService.saveBook(mediumId, bookData);
	}
	
	@PutMapping("/medium/{mediumId}/book/{bookId}")
	public BookData updateBook(@PathVariable Long mediumId, @PathVariable Long bookId,
			@RequestBody BookData bookData) {
		bookData.setBookId(bookId);
		log.info("Creating book {} for medium with ID={}", bookData, mediumId);

		return bookService.saveBook(mediumId, bookData);
	}

	@GetMapping("/medium/{mediumId}/book/{bookId}")
	public  BookData retrieveBookById(@PathVariable Long mediumId, @PathVariable Long bookId) {
		log.info("Retrieving book with ID={}", bookId, mediumId);
		return bookService.retrieveBookById(mediumId, bookId);
	}

	@DeleteMapping("/medium/{mediumId}/book/{bookId}")
	public Map<String, String> deleteBookById(
			@PathVariable Long bookId){
		log.info("Deleting book with ID=()", bookId);
		
		bookService.deleteBookById(bookId);
		
		return Map.of("message", "Deletion of book with ID=" + bookId + " was successful.");
	}
	
}
