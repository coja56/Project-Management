// This class contains methods that displays all projects information, particular chosen information etc
package level03Task08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayProjects {
	
	public static void displayAllProjects(Statement statement)throws
	SQLException{
		
		// Querying and creating a direct line to the database for running our queries
		ResultSet results = statement.executeQuery("SELECT project_details.project_number, personal_details.name, personal_details.phone_number, "
				+ "personal_details.email, personal_details.home_address, project_details.building_type, project_details.fee, project_details.due_date "
				+ "FROM personal_details "
				+ "INNER JOIN project_details "
				+ "ON project_details.project_number = personal_details.id;");
		
		//Printing particular columns data
		while(results.next()) {
			System.out.printf("\nProject Number		: %s\n"
	        		+ "Customer Name		: %s\n"
	        		+ "Telephone Number	: %s\n"
	        		+ "Email Address		: %s\n"
	        		+ "Physical Address	: %s\n"
	        		+ "Building Type		: %s\n"
	        		+ "Fee charged 		: R%s\n"
	        		+ "Due date		: %s\n", results.getInt("project_number"), results.getString("name"), results.getString("phone_number"), 
	        		results.getString("email"), results.getString("home_address"), results.getString("building_type"), results.getInt("fee"),
	        		results.getString("due_date"));
			
		}

	}
	
	public static void displayChosenProject(int projectNumber) {
		
		try {
			
			//Connecting to the database
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false",
					"otheruser",
					"swordfish");
			// Querying and creating a direct line to the database for running our queries
			String query = "SELECT project_details.project_number, personal_details.name, personal_details.phone_number, personal_details.email, personal_details.home_address, project_details.building_type, project_details.fee, project_details.due_date"
					+ " FROM personal_details"
					+ " INNER JOIN project_details"
					+ " ON project_details.project_number = personal_details.id"
					+ " WHERE project_details.project_number = " + projectNumber + ";";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet results = preparedStatement.executeQuery();
			if(results.next()) {
				
				// Printing out particular columns data
				System.out.printf("\n1-Project Number	: %s\n"
		        		+ "2-Customer Name		: %s\n"
		        		+ "3-Telephone Number	: %s\n"
		        		+ "4-Email Address		: %s\n"
		        		+ "5-Physical Address	: %s\n"
		        		+ "6-Building Type		: %s\n"
		        		+ "7-Fee charged 		: R%s\n"
		        		+ "8-Due date		: %s\n", results.getInt("project_number"), results.getString("name"), results.getString("phone_number"), 
		        		results.getString("email"), results.getString("home_address"), results.getString("building_type"), results.getInt("fee"),
		        		results.getString("due_date"));
			}
			
		}
		catch(SQLException e) {
			System.out.println("Error occured.");
			e.printStackTrace();
		}
	}
	
	public static void incompleteProjects() {
		
		try {
			//Connecting to the database
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false",
					"otheruser",
					"swordfish"
					);
			
			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();
			
			String query = "SELECT project_details.project_number, personal_details.name, personal_details.phone_number,"
					+ "personal_details.email, personal_details.home_address, project_details.project_name, project_details.building_type, project_details.erf_number, "
					+ "project_details.due_date, project_details.fee, project_details.amount_paid, project_details.outstanding_amount"
					+ " FROM personal_details"
					+ " INNER JOIN project_details"
					+ " ON project_details.project_number = personal_details.id"
					+ " WHERE project_details.completion_date = 0000-00-00";
			System.out.println(query);
			ResultSet results = statement.executeQuery(query); 
			
			// Printing particular columns data
			while(results.next()) {
				System.out.printf("\nProject Number		: %s\n"
						+ "Customer name		: %s\n"
			        		+ "Telephone Number	: %s\n"
			        		+ "Email Address		: %s\n"
			        		+ "Physical Address	: %s\n"
			        		+ "Project name		: %s\n"
			        		+ "Building type		: %s\n"
			        		+ "ERF number		: %s\n"
			        		+ "Due date		: %s\n"
			        		+ "Fee charged		: R%s\n"
			        		+ "Amount Paid		: R%s\n"
			        		+ "Outstanding Amount	: R%s\n", results.getInt("project_number"), results.getString("name"), results.getString("phone_number"), 
		        		results.getString("email"), results.getString("home_address"), results.getString("project_name"), results.getString("building_type"), results.getString("erf_number"), 
		        		results.getString("due_date"), results.getFloat("fee"), results.getFloat("amount_paid"), results.getFloat("outstanding_amount"));
			}
		}
		catch(SQLException e) {
			
			System.out.println("Error ocurred!");
			e.printStackTrace();
		}
	}
	
	public static void displayPastDueDateProjects(String completionDate) {
		
		try {
			//Connecting to the database
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false",
					"otheruser",
					"swordfish"
					);
			
			// Querying and creating a direct line to the database for running our queries
			String query = "SELECT project_details.project_number, personal_details.name, personal_details.phone_number,"
					+ "personal_details.email, personal_details.home_address, project_details.project_name, project_details.building_type, project_details.erf_number, "
					+ "project_details.due_date, project_details.fee, project_details.amount_paid, project_details.outstanding_amount"
					+ " FROM personal_details"
					+ " INNER JOIN project_details"
					+ " ON project_details.project_number = personal_details.id"
					+ " WHERE project_details.due_date < ?";
					
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, completionDate);
			
			ResultSet results = preparedStatement.executeQuery();
			
			// Printing particular columns data
			while(results.next()) {
				System.out.printf("\nProject Number		: %s\n"
						+ "Customer name		: %s\n"
			        		+ "Telephone Number	: %s\n"
			        		+ "Email Address		: %s\n"
			        		+ "Physical Address	: %s\n"
			        		+ "Project name		: %s\n"
			        		+ "Building type		: %s\n"
			        		+ "ERF number		: %s\n"
			        		+ "Due date		: %s\n"
			        		+ "Fee charged		: R%s\n"
			        		+ "Amount Paid		: R%s\n"
			        		+ "Outstanding Amount	: R%s\n", results.getInt("project_number"), results.getString("name"), results.getString("phone_number"), 
		        		results.getString("email"), results.getString("home_address"), results.getString("project_name"), results.getString("building_type"), results.getString("erf_number"), 
		        		results.getString("due_date"), results.getFloat("fee"), results.getFloat("amount_paid"), results.getFloat("outstanding_amount"));
			}
		}
		catch(SQLException e) {
			
			System.out.println("Error ocurred!");
			e.printStackTrace();
		}
	}
	public static void displayInvoice(int projectNumb) {
		
		try {
			//Connecting to the database
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false",
					"otheruser",
					"swordfish");
			
			// Querying and creating a direct line to the database for running our queries
			String query = "SELECT project_details.project_number, personal_details.name, personal_details.phone_number,"
					+ "personal_details.email, personal_details.home_address, project_details.project_name, project_details.building_type, project_details.erf_number, "
					+ "project_details.due_date, project_details.fee, project_details.amount_paid, project_details.outstanding_amount"
					+ " FROM personal_details"
					+ " INNER JOIN project_details"
					+ " ON project_details.project_number = personal_details.id"
					+ " WHERE project_details.project_number = " + projectNumb + ";";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet results = preparedStatement.executeQuery();
			
			System.out.println("\nINVOICE");
			// Printing particular columns data
			if(results.next()) {
				System.out.printf("\nProject Number		: %s\n"
						+ "Customer name		: %s\n"
			        		+ "Telephone Number	: %s\n"
			        		+ "Email Address		: %s\n"
			        		+ "Physical Address	: %s\n"
			        		+ "Project name		: %s\n"
			        		+ "Building type		: %s\n"
			        		+ "ERF number		: %s\n"
			        		+ "Due date		: %s\n"
			        		+ "Fee charged		: R%s\n"
			        		+ "Amount Paid		: R%s\n"
			        		+ "Outstanding Amount	: R%s\n", results.getInt("project_number"), results.getString("name"), results.getString("phone_number"), 
		        		results.getString("email"), results.getString("home_address"), results.getString("project_name"), results.getString("building_type"), results.getString("erf_number"), 
		        		results.getString("due_date"), results.getFloat("fee"), results.getFloat("amount_paid"), results.getFloat("outstanding_amount"));
			}
		}
		catch(SQLException e) {
			System.out.println("Error occured.");
			e.printStackTrace();
		}
	}
}