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
public class MediumData {

//This is the MediumData class that helps to turn Mediums to
//Medium data using constructors. Also solidifies the books being
//reliant on a medium.
	
	private Long mediumId;
	private String mediumPreference;
	private Set<BooksResponse> books = new HashSet<>();
	
	public MediumData(Medium medium) {
		mediumId = medium.getMediumId();
		mediumPreference = medium.getMediumPreference();
		
		for (Books book : medium.getBooks()) {
			books.add(new BooksResponse(book));
		}	
		
	}
	
	@Data
	@NoArgsConstructor
	static class BooksResponse {
		private Long bookId;	
		private String bookName;
		private String author;
		private Set<String> genres = new HashSet<>();
		
		BooksResponse(Books books) {
			bookId = books.getBookId();
			bookName = books.getBookName();
			author = books.getAuthor();
			
			for (Genre genre : books.getGenres()) {
				genres.add(genre.getGenre());
			}
		}
	}
	
	
}
