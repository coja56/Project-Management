// This class allows user to save data into a database
package level03Task08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonalDetails {
	
	public static void savePersonalDetails(String name, String telephoneNumber, String emailAddress, String physicalAddress, int id) {
		
		try {
			//Connecting to the database
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false",
					"otheruser",
					"swordfish");
			
			//Inserting books into the database by query and executing the query
			String query = "INSERT INTO personal_details VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, telephoneNumber);
			preparedStatement.setString(3, emailAddress);
			preparedStatement.setString(4, physicalAddress);
			preparedStatement.setInt(5, id);
			
			preparedStatement.executeUpdate();
			System.out.println("\nInformation captured successfully.\n");
		}
		catch(SQLException e) {
			System.out.println("Error occured.");
			e.printStackTrace();
		}
	}

}
