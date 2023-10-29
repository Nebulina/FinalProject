package book.interest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.interest.entity.Books;

//Here is the BooksDAO which extends a JPA Repository,
//in there we have a method declaration which spring JPA 
//will turn into a SQL call. 

public interface BooksDao extends JpaRepository<Books, Long> {

}
