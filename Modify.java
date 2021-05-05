// This class allows user to modify data
package level03Task08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Modify {

	public static void ModifyDetails(String updatedContent, int projectNumber, int numField) {
		try {
			//Connecting to the database
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false",
					"otheruser",
					"swordfish");
			
			if(numField == 2) {
				// Updating date by querying and executing the query
				String query = "UPDATE personal_details SET name = ? WHERE id = ?";
				
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, updatedContent);
				preparedStatement.setInt(2, projectNumber);
				
				preparedStatement.executeUpdate();
				System.out.println("Information updated successfully.");
			}
			else if(numField == 3) {
				// Updating date by querying and executing the query
				String query = "UPDATE personal_details SET phone_number = " + updatedContent + " WHERE id = " + projectNumber +";";
				
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.executeUpdate();
				System.out.println("Information updated successfully.");
			}
			else if(numField == 4) {
				// Updating date by querying and executing the query
				String query = "UPDATE personal_details SET email = ? WHERE id = ?";
				
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, updatedContent);
				preparedStatement.setInt(2, projectNumber);
				
				preparedStatement.executeUpdate();
				System.out.println("Information updated successfully.");
			}
			else if(numField == 5) {
				// Updating date by querying and executing the query
				String query = "UPDATE personal_details SET home_address = ? WHERE id = ?";
				System.out.println(query);
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, updatedContent);
				preparedStatement.setInt(2, projectNumber);
				
				preparedStatement.executeUpdate();
				System.out.println("Information updated successfully.");
			}
			else if(numField == 6) {
				// Updating date by querying and executing the query
				String query = "UPDATE project_details SET building_type = ? WHERE project_number = ?";
				
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, updatedContent);
				preparedStatement.setInt(2, projectNumber);
				
				preparedStatement.executeUpdate();
				System.out.println("Information updated successfully.");
			}
			else if(numField == 8) {
				
				System.out.println("Confirm date & enter in this format(yyyy-MM-dd)");
			}
			else {
				System.out.println("Incorrect input. You cannot change project number of charged fee!");
			}
			
		}
		catch(SQLException e) {
			System.out.println("Error occured.");
			e.printStackTrace();
		}
		
	}
}
