package nl.moukail.streamtv.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="user")
@NamedQueries({
	@NamedQuery(
			name = "User.byEmailAndActivationKey",
			query = "from User u where u.email = :email AND u.activationKey = :activationKey"
	),
	@NamedQuery(
			name = "User.byEmail",
			query = "from User u where u.email = :email"
	),
	@NamedQuery(
			name = "User.byUsername",
			query = "from User u where u.username = :username"
	),
})
public class User{

	@Id
	@Column(unique = true)
	@GeneratedValue
	private Integer id;
	
	private String firstname;
	
	private String lastname;
	
	@Email
	@Column(unique=true)
	private String email;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	@Column(name="password", insertable=false, updatable=false)
	private String confirmpassword;
	
	@Column(name="role")
	private String role;
	
	@Column(name="registration")
	private Date registrationDate;
	
	private String activationKey;
	
	private boolean enabled;
	
	private boolean locked;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

}