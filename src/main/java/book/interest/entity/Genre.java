package book.interest.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Genre {

	//Below we have our fields for the Genre Class and also our many to many
	//relationship to our Book table.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long genreId;
	private String genre;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "genres")
	private Set<Books> books = new HashSet<>();
}
