/*
 * Name: DUY TRAN
 * Date: March 6th, 2015
 */

package application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Staff;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StaffApplication extends Application {
	// Create controls.
	private Label status = new Label("No Connection.");
	private Label authentication = new Label("Please enter your username and password.");
	private Map<String, Button> btn = new HashMap<>();
	private Map<String, Label> lb = new HashMap<>();
	private Map<String, TextField> tf = new HashMap<>();
	
	// Create managers
	private EntityManagerFactory emfactory;
    private EntityManager entitymanager;
	
	@Override
	public void start(Stage primaryStage) {
		createControls();
		
		// Create the scenes
		Scene main_scene = mainScene();
		Scene login_scene = loginScene();

		// Set the stage title.
		primaryStage.setTitle("Staff GUI");
		// Place the scene in the stage.
		primaryStage.setScene(login_scene);
		// Display the stage.
		primaryStage.show();
		
		// Set the actions for the buttons.
		btn.get("login").setOnAction((ActionEvent e) -> {
			if (initializeDB()) {
				primaryStage.setScene(main_scene);
				authentication.setText("Please enter your username and password.");
			}
			else
				authentication.setText("Wrong combination of username and password.");
		});
		
		btn.get("logout").setOnAction((ActionEvent e) -> {
			entitymanager.close();
		    emfactory.close();
		    primaryStage.setScene(login_scene);
		});
		
		btn.get("view").setOnAction((ActionEvent e) -> { view(); });
		btn.get("insert").setOnAction((ActionEvent e) -> { insert(); });
		btn.get("update").setOnAction((ActionEvent e) -> { update(); });
		btn.get("delete").setOnAction((ActionEvent e) -> { delete(); });
		btn.get("clear").setOnAction((ActionEvent e) -> { clear(); });
	}
	
	/** Create Buttons and TextFields */
	private void createControls() {
		/* CREATE BUTTONS */
		btn.put("login", new Button("Log In"));
		btn.put("logout", new Button("Log Out"));
		btn.put("view", new Button("View"));
		btn.put("insert", new Button("Insert"));
		btn.put("update", new Button("Update"));
		btn.put("delete", new Button("Delete"));
		btn.put("clear", new Button("Clear"));
		
		/* CREATE LABELS */
		lb.put("username", new Label("Username  "));
		lb.put("password", new Label("Password  "));
		lb.put("id", new Label("ID "));
		lb.put("lastName", new Label("Last Name "));
		lb.put("firstName", new Label("First Name "));
		lb.put("mi", new Label("MI "));
		lb.put("address", new Label("Address "));
		lb.put("city", new Label("City "));
		lb.put("state", new Label("State "));
		lb.put("telephone", new Label("Phone "));
		lb.put("email", new Label("Email "));
		
		/* CREATE TEXTFIELDS */
		tf.put("username", new TextField());
		tf.put("password", new TextField());
		tf.put("id", new TextField());
		tf.put("lastName", new TextField());
		tf.put("firstName", new TextField());
		tf.put("mi", new TextField());
		tf.put("address", new TextField());
		tf.put("city", new TextField());
		tf.put("state", new TextField());
		tf.put("telephone", new TextField());
		tf.put("email", new TextField());
		
		/* SET PROMPT TEXT for each TEXTFIELD */
		tf.get("username").setPromptText("drytuna");
		tf.get("password").setPromptText("Pa$$word");
		tf.get("id").setPromptText("e.g. 1234");
		tf.get("lastName").setPromptText("e.g. Tuna");
		tf.get("firstName").setPromptText("e.g. Dry");
		tf.get("address").setPromptText("e.g. 9876 Sylvanas Ln.");
		tf.get("city").setPromptText("e.g. Azeroth");
		tf.get("state").setPromptText("e.g. FL");
		tf.get("telephone").setPromptText("e.g. 1234567890");
		tf.get("email").setPromptText("e.g. drytuna@draenor.net");
	}
	
	/** Build the scene for Login */
	private Scene loginScene() {
		VBox vBox = new VBox(5);

		HBox hBox1 = new HBox(5);
		hBox1.getChildren().addAll(lb.get("username"), tf.get("username"));
		hBox1.setAlignment(Pos.BASELINE_CENTER);
		
		HBox hBox2 = new HBox(5);
		hBox2.getChildren().addAll(lb.get("password"), tf.get("password"));
		hBox2.setAlignment(Pos.BASELINE_CENTER);

		HBox hBox3 = new HBox(5);
		hBox3.getChildren().add(btn.get("login"));
		hBox3.setAlignment(Pos.CENTER);
		
		vBox.getChildren().addAll(hBox1, hBox2, hBox3);
		vBox.setAlignment(Pos.CENTER);
		
		BorderPane pane = new BorderPane(vBox, authentication, null, null, null);

		// Create a scene and place it in the stage.
		return new Scene(pane, 500, 300);
	}
	
	/** Build the scene for the main application */
	private Scene mainScene() {
		VBox vBox = new VBox(5);

		HBox hBox1 = new HBox(5);
		// Add first row of fields to window.
		hBox1.getChildren().addAll(lb.get("id"), tf.get("id"));
		tf.get("id").setMaxWidth(100);
		
		HBox hBox2 = new HBox(5);
		// Add second row of fields to window.
		hBox2.getChildren().addAll(lb.get("lastName"), tf.get("lastName"),
								   lb.get("firstName"), tf.get("firstName"),
								   lb.get("mi"), tf.get("mi"));
		// Set the size of these fields.
		tf.get("lastName").setMaxWidth(100);
		tf.get("firstName").setMaxWidth(100);
		tf.get("mi").setMaxWidth(30);

		HBox hBox3 = new HBox(5);
		// Add third row of fields to window.
		hBox3.getChildren().addAll(lb.get("address"), tf.get("address"));
		hBox3.setMaxWidth(450);
		tf.get("address").setMaxWidth(300);
		
		HBox hBox4 = new HBox(5);
		// Add fourth row of fields to window.
		hBox4.getChildren().addAll(lb.get("city"), tf.get("city"),
								   lb.get("state"), tf.get("state"));
		tf.get("state").setMaxWidth(60);
		
		HBox hBox5 = new HBox(5);
		// Add fifth row of fields to window.
		hBox5.getChildren().addAll(lb.get("telephone"), tf.get("telephone"),
								   lb.get("email"), tf.get("email"));
		tf.get("telephone").setMaxWidth(130);
		tf.get("email").setMaxWidth(270);
		
		HBox hBox = new HBox(5);
		// Add buttons
		hBox.getChildren().addAll( btn.get("view"), 
				btn.get("insert"), btn.get("update"), 
				btn.get("delete"), btn.get("clear"));
		// Set the buttons to be centered.
		hBox.setAlignment(Pos.CENTER);
		
		// Add horizontal boxes to vertical box.
		vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5, hBox);

		// Create a pane
		BorderPane pane = new BorderPane(vBox, status, null, btn.get("logout"), null);
		pane.setAlignment(btn.get("logout"), Pos.BOTTOM_CENTER);
		
		// Create a scene and place it in the stage.
		return new Scene(pane, 450, 300);
	}

	/** Collect username/password and connects to the Database */
	private boolean initializeDB() {
		Map<String, String> pers = new HashMap<>();
		pers.put("javax.persistence.jdbc.user", tf.get("username").getText());
		pers.put("javax.persistence.jdbc.password", tf.get("password").getText().trim());
		try {
		      emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA", pers);
		      entitymanager = emfactory.createEntityManager( );
		      entitymanager.getTransaction( ).begin( );
		      entitymanager.getTransaction( ).commit( );
		      status.setText("Database Connected.");
		      clear();
		      return true;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			status.setText("Cannot connect to Database.");
			return false;
		}
	}

	/** View record by ID. */
	private void view() {
		String id = tf.get("id").getText();
		int primary_key = (id == null) ? -1 : Integer.parseInt(id.trim());
		Staff staff = entitymanager.find(Staff.class, primary_key);
		
		if (staff == null) {
			status.setText("Staff with ID:" + primary_key + " does not exist.");
		}
		else {
			loadToTextField(staff);
			status.setText("Staff with ID:" + primary_key + " found.");
		}
	}
	
	/** Load the record into text fields. */
	private void loadToTextField(Staff staff){
		tf.get("firstName").setText(staff.getFirstName());
		tf.get("lastName").setText(staff.getLastName());
		tf.get("mi").setText(staff.getMi()+"");
		tf.get("address").setText(staff.getAddress());
		tf.get("city").setText(staff.getCity());
		tf.get("state").setText(staff.getState());
		tf.get("telephone").setText(staff.getTelephone());
		tf.get("email").setText(staff.getEmail());
	}
	
	/** Insert a new record. */
	private void insert() {
		String id = tf.get("id").getText();
		int primary_key = (id == null) ? -1 : Integer.parseInt(id.trim());
		Staff staff = entitymanager.find(Staff.class, primary_key);
		
		if (staff == null) {
			staff = new Staff(primary_key);
			setStaff(staff);

			entitymanager.getTransaction().begin();
			entitymanager.persist(staff);
		    entitymanager.getTransaction().commit();

			status.setText("Inserted information for Staff ID:" + primary_key);
		}
		else {
			status.setText("Staff with ID:" + primary_key + " already exist. Please use Update.");
		}
	}
	
	/**
	 * @param staff - an instance of Staff to be modified
	 * Process: Loads inputs from TextFields to Staff
	 */
	private void setStaff(Staff staff) {
		if (tf.get("firstName").getText() != null)
			staff.setFirstName(tf.get("firstName").getText().trim());
		
		if (tf.get("lastName").getText() != null)
			staff.setLastName(tf.get("firstName").getText().trim());
		
		if (tf.get("address").getText() != null)
			staff.setAddress(tf.get("address").getText().trim());
		
		if (tf.get("city").getText() != null)
			staff.setCity(tf.get("city").getText().trim());
		
		if (tf.get("telephone").getText() != null)
			staff.setTelephone(tf.get("telephone").getText().trim());
		
		if (tf.get("email").getText() != null)
			staff.setEmail(tf.get("email").getText().trim());
		
		String mi = tf.get("mi").getText();
		if (mi != null) {
			mi = mi.trim();
			if (mi.length() > 0)
				staff.setMi(mi.toUpperCase().charAt(0));
		}
		
		String state = tf.get("state").getText();
		if (state != null) {
			state = state.trim();
			if (state.length() > 1)
				staff.setState(state.substring(0, 2).toUpperCase());
		}
	}
	
	/** Update a record. */
	private void update() {
		String id = tf.get("id").getText();
		int primary_key = (id == null) ? -1 : Integer.parseInt(id.trim());
		Staff staff = entitymanager.find(Staff.class, primary_key);
		
		if (staff != null) {
			entitymanager.getTransaction().begin();
			setStaff(staff);
		    entitymanager.getTransaction().commit();

			status.setText("Updated information for Staff ID:" + primary_key);
		}
		else {
			status.setText("Staff with ID:" + primary_key + " does not exist. Please use Insert.");
		}
	}
	
	/** Delete a record. */
	private void delete() {
		if (tf.get("id").getText() == null || tf.get("id").getText().trim().isEmpty())
			return;
		
		int primary_key = Integer.parseInt(tf.get("id").getText().trim());
		Staff staff = entitymanager.find(Staff.class, primary_key);
		if (staff != null) {
			entitymanager.getTransaction().begin();
			entitymanager.remove(staff);
		    entitymanager.getTransaction().commit();

			status.setText("Deleted Staff ID:" + primary_key + " from the database.");
			clear();
		}
		else {
			status.setText("Staff with ID:" + primary_key + " does not exist.");
		}
	}
	
	/** Clear text fields. */
	private void clear() {
		for (String key: tf.keySet())
			tf.get(key).setText(null);
	}
	
	/**
	 * The main method is only needed for the IDE with limited javaFX support.
	 * Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Name: tablePrint()
	 * Process: Print the query results with organized spacing
	 */
	private void tablePrint() {
		List<Staff> staffs = entitymanager.createQuery("SELECT s FROM Staff s").getResultList();
		System.out.printf("\n%-10s%-5s%-20s%-20s%-30s%-10s%-10s%-15s%-30s\n",
				"ID",
				"Mi",
				"First Name",
				"Last Name",
				"Address",
				"City",
				"State",
				"Telephone",
				"Email");
		Iterator iter = staffs.iterator();
		while (iter.hasNext()) {
			Staff staff = (Staff)iter.next();
			System.out.printf("%-10d%-5s%-20s%-20s%-30s%-10s%-10s%-15s%-30s\n",
					staff.getId(),
					staff.getMi(),
					staff.getFirstName(),
					staff.getLastName(),
					staff.getAddress(),
					staff.getCity(),
					staff.getState(),
					staff.getTelephone(),
					staff.getEmail());
		}
	}
}
