/* 
* Autogenerated interface code 
* Variables (text between %) must have been replaced when code is autogenerated
*/
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select a from User a where a.userAccount.id = ?1")
	User findByPrincipal(int userAccountId);
	
	@Query("select count(u) from User u")
	Integer getNumberOfUser();
}
