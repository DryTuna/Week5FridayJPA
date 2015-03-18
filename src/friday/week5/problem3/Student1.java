package friday.week5.problem3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Student1 {
	@Id
	@Column(name = "username", nullable = false, length = 50)
	private String username;
	
	@Column(name = "password", nullable = false, length = 50)
	private String password;
	
	@Column(name = "fullname", nullable = false, length = 100)
	private String fullname;
	
	public Student1() {}
	
	public Student1(String username, String password, String fullname) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
}
