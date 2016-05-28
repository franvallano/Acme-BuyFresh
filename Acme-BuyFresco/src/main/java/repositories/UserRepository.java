/* 
* Autogenerated interface code 
* Variables (text between %) must have been replaced when code is autogenerated
*/
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select a from User a where a.userAccount.id = ?1")
	User findByPrincipal(int userAccountId);
	
	@Query("select count(u) from User u")
	Integer getNumberOfUser();
	
	@Query("select a from User a where a.subcriptions.size >= ALL(select a1.subcriptions.size from User a1)")
	Collection<User> UserMoreSubscriptions();
	
	@Query("select a from User a where a.assessments.size >= ALL(select a1.assessments.size from User a1)")
	Collection<User> UserMoreAssessments();
}
