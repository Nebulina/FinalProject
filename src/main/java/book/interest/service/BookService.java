package book.interest.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book.interest.controller.model.BookData;
import book.interest.controller.model.MediumData;
import book.interest.dao.BooksDao;
import book.interest.dao.GenreDao;
import book.interest.dao.MediumDao;
import book.interest.entity.Books;
import book.interest.entity.Genre;
import book.interest.entity.Medium;

@Service
public class BookService {
	
//This is the Service class which contains methods for the controller
//class. We call on our DAO interfaces from this class. 

	@Autowired
	private MediumDao mediumDao;

	@Autowired
	private GenreDao genreDao;
	
	@Autowired
	private BooksDao booksDao;

	@Transactional(readOnly = false)
	public MediumData saveMedium(MediumData mediumData) {
		Long mediumId = mediumData.getMediumId();
		Medium medium = findOrCreateMedium(mediumId);

		setFieldsInMedium(medium, mediumData);
		return new MediumData(mediumDao.save(medium));
	}

	private void setFieldsInMedium(Medium medium, MediumData mediumData) {
		medium.setMediumPreference(mediumData.getMediumPreference());

	}

	private Medium findOrCreateMedium(Long mediumId) {
		Medium medium;

		if (Objects.isNull(mediumId)) {
			medium = new Medium();
		} else {
			medium = findMediumById(mediumId);
		}
		return medium;
	}

	private Medium findMediumById(Long mediumId) {
		return mediumDao.findById(mediumId)
				.orElseThrow(() -> new NoSuchElementException("Medium with ID=" + mediumId + " was not found."));

	}
	@Transactional(readOnly = true)
	public List<MediumData> retrieveAllMediums() {
		List<Medium> mediums = mediumDao.findAll();
		List<MediumData> response = new LinkedList<>();
		
		for(Medium medium : mediums) {
			response.add(new MediumData(medium));
		}
		
		return response;
	}

	@Transactional(readOnly = true)
	public MediumData retrieveMediumById(Long mediumId) {
		Medium medium = findMediumById(mediumId);
		return new MediumData(medium);
	}

	@Transactional(readOnly = false)
	public void deleteMediumById(Long mediumId) {
		Medium medium = findMediumById(mediumId);
		mediumDao.delete(medium);
		
	}
	@Transactional(readOnly = false)
	public BookData saveBook(Long mediumId, BookData bookData) {
		Medium medium = findMediumById(mediumId);

		Set<Genre> genres = genreDao.findAllByGenreIn(bookData.getGenres());
		
		Books books = findOrCreateBook(bookData.getBookId());
		setBookFields(books, bookData);

		books.setMedium(medium);
		medium.getBooks().add(books);

		for(Genre genre : genres) {
			genre.getBooks().add(books);
			books.getGenres().add(genre);
		}

		
		Books dbBooks = booksDao.save(books);
		return new BookData(dbBooks);
	}


	private void setBookFields(Books books, BookData bookData) {
		books.setBookName(bookData.getBookName());
		books.setAuthor(bookData.getAuthor());
		books.setBookId(bookData.getBookId());

		
	}

	private Books findOrCreateBook(Long bookId) {
		Books books;
		
		if(Objects.isNull(bookId) ) {
			books = new Books();
		} 
		else {
			books = findBookById(bookId);
		}
		
		return books;
	}

	private Books findBookById(Long bookId) {
		return booksDao.findById(bookId)
				.orElseThrow(() -> new NoSuchElementException("Book with ID=" + bookId + " does not exist."));
	}

	@Transactional(readOnly = true)
	public BookData retrieveBookById(Long mediumId, Long bookId) {
		findMediumById(mediumId);
		Books books = findBookById(bookId);
	
		if(books.getMedium().getMediumId() != mediumId) {
			throw new IllegalStateException("Book with ID=" + bookId + " is not owned by medium with ID=" + mediumId);
		}
		
		return new BookData(books);
	}

	@Transactional(readOnly = false)
	public void deleteBookById(Long bookId) {
		Books books = findBookById(bookId);
		booksDao.delete(books);
	
	}
	
}
