package book.interest.dao;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import book.interest.entity.Genre;

//Here is the GenreDAO which extends a JPA Repository,
//in there we have a method declaration which spring JPA 
//will turn into a SQL call. 

public interface GenreDao extends JpaRepository<Genre, Long> {

	Set<Genre> findAllByGenreIn(Set<String> genres);

}
