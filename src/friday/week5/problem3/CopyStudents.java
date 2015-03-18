/*
 * Name: DUY TRAN
 * Date: March 6th, 2015
 */

package friday.week5.problem3;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CopyStudents {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
	    EntityManager entitymanager = emfactory.createEntityManager( );
	    
	    insertStudent1(entitymanager);
	    copyToStudent2(entitymanager);
	    printTables(entitymanager);
	    dropTables(entitymanager);
	    
	    entitymanager.close();
	    emfactory.close();
	}

	/**
	 * Name: dropTables()
	 * @param em - EntityManager instance
	 * Process: Drop both Student1 and Student2 if exists
	 */
	private static void dropTables(EntityManager em) {
		em.getTransaction( ).begin( );
	    	
		Query del_1 = em.createNativeQuery("DELETE FROM Student1");
		Query del_2 = em.createNativeQuery("DELETE FROM Student2");
		del_1.executeUpdate();
		del_2.executeUpdate();
		
		em.getTransaction( ).commit( );
	}
	
	/**
	 * Name: inserStudent1()
	 * @param em - EntityManager instance
	 * Process: Insert 10 records into Student1 as test data
	 */
	private static void insertStudent1(EntityManager em) {
		em.getTransaction( ).begin( );
		for(int i = 1; i <= 10; i++) {
			Student1 stu = new Student1();
			stu.setUsername("student_" + i);
			stu.setPassword(i + "_student");
			stu.setFullname("firstname_" + i + " middle_name lastname_" + i);
			em.persist(stu);
		}
		em.getTransaction( ).commit( );
	}
	
	/**
	 * Name: copyToStudent2()
	 * @param em - EntityManager instance
	 * Process: Retrieve data from Student1, parse the name and insert into Student2
	 */
	private static void copyToStudent2(EntityManager em) {
		em.getTransaction( ).begin( );
		
		List<Student1> stds = em.createQuery("SELECT s FROM Student1 s").getResultList();
		Iterator<Student1> iter = stds.iterator();
		
		while (iter.hasNext()) {
			Student1 std_1 = (Student1)iter.next();
			String[] fullname = std_1.getFullname().split("\\s+");
			
			Student2 std_2 = new Student2();
			std_2.setUsername(std_1.getUsername());
			std_2.setPassword(std_1.getPassword());
			std_2.setFirstname(fullname[0]);
			std_2.setLastname(fullname[fullname.length-1]);
			em.persist(std_2);
		}
		
		em.getTransaction( ).commit( );
	}
	
	/**
	 * Name: printTables()
	 * @param em - EntityManager instance
	 * Process: Print all records from both Tables
	 */
	private static void printTables(EntityManager em) {
		/* Select all records from Student1 */
		System.out.printf("\n%-20s%-20s%-20s\n", "USERNAME", "PASSWORD", "FULL NAME");
		List<Student1> stds_1 = em.createQuery("SELECT s FROM Student1 s").getResultList();
		Iterator<Student1> iter_1 = stds_1.iterator();

		while (iter_1.hasNext()) {
			Student1 std_1 = (Student1)iter_1.next();
			System.out.printf("%-20s%-20s%-20s\n",
					std_1.getUsername(),
					std_1.getPassword(),
					std_1.getFullname());
		}
		
		/* Select all records from Student2 */
		System.out.printf("\n%-20s%-20s%-20s%-20s\n", "USERNAME", "PASSWORD", "FIRST NAME", "LAST NAME");
		List<Student2> stds_2 = em.createQuery("SELECT s FROM Student2 s").getResultList();
		Iterator<Student2> iter_2 = stds_2.iterator();
		
		while (iter_2.hasNext()) {
			Student2 std_2 = (Student2)iter_2.next();
			System.out.printf("%-20s%-20s%-20s%-20s\n",
					std_2.getUsername(),
					std_2.getPassword(),
					std_2.getFirstname(),
					std_2.getLastname());
		}
	}
}
