/*
 * Name: DUY TRAN
 * Date: March 6th, 2015
 */

package friday.week5.problem2;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PopulateSalary {

	public static void main(String[] args) {
		String url = "http://cs.armstrong.edu/liang/data/Salary.txt";
		try {
			Document doc = Jsoup.connect(url).get();
			Scanner sc = new Scanner(doc.body().text());
			
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		    EntityManager entitymanager = emfactory.createEntityManager( );
		    entitymanager.getTransaction().begin();
		    
		    while (sc.hasNext()) {
		    	Salary sal = new Salary(sc.next(), sc.next(), sc.next(), sc.nextDouble());
		    	entitymanager.persist(sal);
		    }
		    
		    entitymanager.getTransaction().commit();
		    
		    printSalary(entitymanager);
		    dropSalary(entitymanager);
		    
		    entitymanager.close();
		    emfactory.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Name: printSalary()
	 * @param em - an EntityManager instance
	 * Process: print all records from the Salary table
	 */
	private static void printSalary(EntityManager em) {
		List<Salary> salaries = em.createQuery("SELECT s FROM Salary s").getResultList();
		Iterator<Salary> iter = salaries.iterator();
		System.out.printf("\n%-20s%-20s%-20s%-20s\n", "FIRST_NAME", "LAST_NAME", "RANK", "SALARY");
		while (iter.hasNext()) {
			Salary salary = (Salary)iter.next();
			System.out.printf("%-20s%-20s%-20s%-20.2f\n",
					salary.getFirstname(),
					salary.getLastname(),
					salary.getRank(),
					salary.getSalary());
		}
	}
	
	/**
	 * Name: dropSalary()
	 * @param em - an EntityManager instance
	 * Process: Drop the Salary table
	 */
	private static void dropSalary(EntityManager em) {
		em.getTransaction( ).begin( );
	    	
		Query del = em.createNativeQuery("DELETE FROM Salary");
		del.executeUpdate();
		
		em.getTransaction( ).commit( );
	}
}
