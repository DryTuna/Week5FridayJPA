package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Staff{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
    @Column(name = "lastName", nullable = true, length = 15)
	private String lastName;
	
    @Column(name = "firstName", nullable = true, length = 15)
	private String firstName;
	
    @Column(name = "mi", nullable = true, length = 1)
	private char mi;
	
    @Column(name = "address", nullable = true, length = 20)
	private String address;
	
    @Column(name = "city", nullable = true, length = 20)
	private String city;
	
    @Column(name = "state", nullable = true, length = 2)
	private String state;
	
    @Column(name = "telephone", nullable = true, length = 10)
	private String telephone;
	
    @Column(name = "email", nullable = true, length = 40)
	private String email;
	
	public Staff() {}

	public Staff(int id) {
		this.id = id;
		this.lastName = "";
		this.firstName = "";
		this.address = "";
		this.city = "";
		this.state = "";
		this.telephone = "";
		this.email = "";
	}

	public Staff(int id, String lastName, String firstName, char mi,
			String address, String city, String state, String telephone,
			String email) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.mi = mi;
		this.address = address;
		this.city = city;
		this.state = state;
		this.telephone = telephone;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public char getMi() {
		return mi;
	}

	public void setMi(char mi) {
		this.mi = mi;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
