package friday.week5.problem3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Student2 {
	@Id
	@Column(name = "username", nullable = false, length = 50)
	private String username;
	
	@Column(name = "password", nullable = false, length = 50)
	private String password;
	
	@Column(name = "firstname", nullable = true, length = 50)
	private String firstname;
	
	@Column(name = "lastname", nullable = true, length = 50)
	private String lastname;
	
	public Student2() {}
	
	public Student2(String username, String password, String firstname, String lastname) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
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
	
}
