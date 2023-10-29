package book.interest.controller.model;

import java.util.HashSet;
import java.util.Set;

import book.interest.entity.Books;
import book.interest.entity.Genre;
import book.interest.entity.Medium;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookData {

//This is the BookData class that helps to turn books to
//book data using constructors. Also solidifies the books being
//reliant on a medium.
	
	private Long bookId;
	private String bookName;	
	private String author;
	private BookMedium medium;
	private Set<String> genres = new HashSet<>();
	
	
	public BookData(Books books) {
		bookId = books.getBookId();
		bookName = books.getBookName();
		author = books.getAuthor();
		medium = new BookMedium(books.getMedium());

		for(Genre genre : books.getGenres()) {
			genres.add(genre.getGenre());
	}
}
	@Data
	@NoArgsConstructor
	public static class BookMedium {
		private Long mediumId;
		private String mediumPreference;
		
		public BookMedium(Medium medium) {
			mediumId = medium.getMediumId();
			mediumPreference = medium.getMediumPreference();

			
		}
	}
	
}


