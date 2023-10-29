package book.interest.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Books {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

//Below we have our fields for the Books Class and also our relationships
//to our Medium and Genre tables. With Medium being a many to one and 
//Genre being a many to many.
	
	private Long bookId;
	
	@Column(unique = true)
	private String bookName;
	
	private String author;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "medium_id", nullable = false)
		private Medium medium;
		
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)// If we delete a park we don't want to delete the amenity rows if we delete a table.
	@JoinTable(name = "book_genre", 
	joinColumns = @JoinColumn(name = "book_id"),
	inverseJoinColumns = @JoinColumn(name = "genre_id"))

	private Set<Genre> genres = new HashSet<>();
	
}
