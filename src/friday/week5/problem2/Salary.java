package friday.week5.problem2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Salary {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="my_entity_seq_gen")
	@SequenceGenerator(name="my_entity_seq_gen", sequenceName="MY_ENTITY_SEQ")
	private long id;
	
	@Column(name = "firstname", length = 20)
	private String firstname;
	
	@Column(name = "lastname", length = 20)
	private String lastname;
	
	@Column(name = "rank", length = 20)
	private String rank;
	
	@Column(name = "salary", columnDefinition="Decimal(10,2)")
	private double salary;
	
	public Salary() { }
	
	public Salary(String firstname, String lastname, String rank, double salary) {
		setFirstname(firstname);
		setLastname(lastname);
		setRank(rank);
		setSalary(salary);
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

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		if (salary >= 0)
			this.salary = salary;
	}
}
