package book.interest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.interest.entity.Medium;

//Here is the MediumDAO which extends a JPA Repository,
//in there we have a method declaration which spring JPA 
//will turn into a SQL call. 

public interface MediumDao extends JpaRepository<Medium, Long> {

}
