package level03Task08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectDetails {
	
	public static void saveProjectDetails(String projectNumber, String projectName, String buildingType, int erfNumber, float feeCharged, 
			float amountPaid, String dueDate, String completionDate, float outstandingAmount) {
		
		try {
			//Connecting to the database
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false",
					"otheruser",
					"swordfish");
			
			//Inserting books into the database
			String query = "INSERT INTO project_details VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, projectNumber);
			preparedStatement.setString(2, projectName);
			preparedStatement.setFloat(3, feeCharged);
			preparedStatement.setFloat(4, amountPaid);
			preparedStatement.setString(5, buildingType);
			preparedStatement.setInt(6, erfNumber);
			preparedStatement.setString(7, dueDate);
			preparedStatement.setString(8, completionDate);
			preparedStatement.setFloat(9, outstandingAmount);
			
			preparedStatement.executeUpdate();
			System.out.println("\nInformation captured successfully.\n");
		}
		catch(SQLException e) {
			
			System.out.println("An error occured.");
			e.printStackTrace();
		}
	}
}
