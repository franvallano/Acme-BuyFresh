/* 
* Autogenerated service code 
* Variables (text between %) must have been replaced when code is autogenerated
*/

package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AssessmentRepository;
import domain.Assessment;
import domain.User;

@Service
@Transactional
public class AssessmentService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AssessmentRepository assessmentRepository;

	// Ancillary services -----------------------------------------------------
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdministratorService administratorService;
	
	// Constructor ------------------------------------------------------------
	public AssessmentService(){
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Assessment create(){
		Assessment newbye;
		Date date;
		User u;

		u = userService.findByPrincipal();
		date = new Date(System.currentTimeMillis()-1);
		
		newbye = new Assessment();
		
		newbye.setMoment(date);
		newbye.setDeleted(false);
		newbye.setUser(u);
		
		return newbye;
	}

	public void save(Assessment entity){
		Assert.notNull(entity);
		Assert.notNull(entity.getText());
		
		this.assessmentRepository.save(entity);
	}

	public void delete(Assessment entity){
		
		administratorService.findByPrincipal();
		
		Assert.isTrue(entity.getId()!=0);
		Assert.isTrue(this.assessmentRepository.exists(entity.getId()));
		
		entity.setDeleted(true);
		
		this.save(entity);
	}

	public Assessment findOne(int id){
		Assert.isTrue(id != 0);
		
		Assessment res;
		
		res = this.assessmentRepository.findOne(id);
		
		return res;
	}

	public Collection<Assessment> findAll(){
		Collection<Assessment> res;
		
		res = assessmentRepository.findAll();
		
		return res;
	}
	
	public Collection<Assessment> AssessmentsWithRatingBigger(){
		Collection<Assessment> res;
		
		res = assessmentRepository.AssessmentsWithRatingBigger();
		
		return res;
	}

	// Other business methods -------------------------------------------------

	public Collection<Assessment> findAllWithoutDeleted(){
		Collection<Assessment> res;
		
		res = assessmentRepository.findAllWithoutDelete();
		
		return res;
	}
	
	// Ancillary methods ------------------------------------------------------
	
	public Double findAvgRating(){
		return assessmentRepository.findAvgRating();
	}
}
