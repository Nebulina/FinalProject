package book.interest.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Medium {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	
//Below we have our fields for the Medium Class and also our one to many
//relationship to our Book table.	
	
	
	private Long mediumId;
	private String mediumPreference;

	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "medium", cascade = CascadeType.ALL) //If we delete or add a contributor that has pet parks associated it will delete child rows.
	private Set<Books> books = new HashSet<>();
	

	
}
