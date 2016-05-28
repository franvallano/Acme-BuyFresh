package forms;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import datatypes.CreditCard;
import domain.Allergen;


public class UserForm {
	private String username;
	private String password;
	private String name;
	private String surname;
	private String repeatedPass;
	private String email;
	private CreditCard creditCard;
	private String phone;
	private String address;
	private boolean agree;
	private Collection<Allergen>allergens;
	private boolean checkBoxCreditCard;
	
	
	@ElementCollection
	@Column(name="allergens")
	public Collection<Allergen> getAllergens() {
		return allergens;
	}
	public void setAllergens(Collection<Allergen> allergens) {
		this.allergens = allergens;
	}
	
	@Size(min=5, max=32)
	@SafeHtml(whitelistType=WhiteListType.SIMPLE_TEXT)
	@NotBlank
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@SafeHtml(whitelistType=WhiteListType.SIMPLE_TEXT)
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@SafeHtml(whitelistType=WhiteListType.SIMPLE_TEXT)
	@NotBlank
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Size(min=5, max=32)
	@SafeHtml(whitelistType=WhiteListType.SIMPLE_TEXT)
	@NotBlank
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Size(min=5, max=32)
	@SafeHtml(whitelistType=WhiteListType.SIMPLE_TEXT)
	@NotBlank
	public String getRepeatedPass() {
		return repeatedPass;
	}
	public void setRepeatedPass(String repeatedPass) {
		this.repeatedPass = repeatedPass;
	}
	
	@SafeHtml(whitelistType=WhiteListType.SIMPLE_TEXT)
	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	@Pattern(regexp = "(((\\+34)? ?(\\(0\\))? ?)|(0))( ?[0-9]{3,4}){3}")
	@SafeHtml(whitelistType=WhiteListType.SIMPLE_TEXT)
	@NotBlank
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@SafeHtml(whitelistType=WhiteListType.SIMPLE_TEXT)
	@NotBlank
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean isAgree() {
		return agree;
	}
	public void setAgree(boolean agree) {
		this.agree = agree;
	}
	public boolean isCheckBoxCreditCard() {
		return checkBoxCreditCard;
	}
	public void setCheckBoxCreditCard(boolean checkBoxCreditCard) {
		this.checkBoxCreditCard = checkBoxCreditCard;
	}
}
