// This program allows user to capture, update and view data
package level03Task08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		try {
			//Connecting to the database
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false",
					"otheruser",
					"swordfish");
			Statement statement = connection.createStatement();
			
			Scanner input = new Scanner(System.in);
			
			System.out.println("Do you want to capture details, update details or view projects? Enter (Capture/Update/view):");
			String action = input.nextLine();
			
			// Here user is allowed to capture data
			if(action.equalsIgnoreCase("CAPTURE")) {
				
				System.out.println("ENTER PERSONS DETAILS: \n");
				
				System.out.println("Enter project ID:");
				int id = Integer.parseInt(input.nextLine());
				
				System.out.println("Enter your name:");
				String name = input.nextLine();
					
				System.out.println("Enter your contacnt number:");
				String telephoneNumber = input.nextLine();
					
				System.out.println("Enter your email address:");
				String emailAddress = input.nextLine();
					
				System.out.println("Enter your physical address:");
				String physicalAddress = input.nextLine();
				
				PersonalDetails.savePersonalDetails(name, telephoneNumber, emailAddress, physicalAddress, id);
				
				System.out.println("\nENTER PROJECTS DETAILS: \n");
				
				System.out.println("Enter project number:");
				String projectNumber = input.nextLine();
				
				System.out.println("Enter project name:");
				String projectName = input.nextLine();
				
				System.out.println("Enter fee charged (only enter numbers):");
				float feeCharged = Float.parseFloat(input.nextLine());
					
				System.out.println("Enter amount paid (only enter numbers):");
				float amountPaid = Float.parseFloat(input.nextLine());
				float outstandingAmount = feeCharged - amountPaid;
					
				System.out.println("Enter building type:");
				String buildingType = input.nextLine();
					
				System.out.println("Enter ERF number:");
				int erfNumber = Integer.parseInt(input.nextLine());
					
				System.out.println("Enter due date of the project (yyyy-MM-dd):");
				String date = input.nextLine();
				
				//Converting the input date into Date type and making sure that the date entered is of correct format
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				LocalDate date2 = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String completionDate = date2.format(formatter);
				String nonCompletionDate = "0000-00-00";
					
				try {
					
					Date dueDate = dateFormat.parse(date);
					System.out.println("Due date : " + dueDate);
					
				}
				catch (ParseException e) {
					
					System.out.println("Incorrect date format");
						
					e.printStackTrace();
				}
				
				System.out.println("Is the project finalised?(Yes/No):");
				String response = input.nextLine();
				
				if(response.equalsIgnoreCase("Yes")) {
					// save data and generate invoice if the customer did not pay the whole amount for the project
					if(feeCharged > amountPaid) {
						ProjectDetails.saveProjectDetails(projectNumber, projectName, buildingType, erfNumber, feeCharged, amountPaid, date, 
								completionDate, outstandingAmount);
						
						int projectNum = Integer.parseInt(projectNumber);
						DisplayProjects.displayInvoice(projectNum);
					}
					else {
						ProjectDetails.saveProjectDetails(projectNumber, projectName, buildingType, erfNumber, feeCharged, amountPaid, date, 
								completionDate, outstandingAmount);
					}
					
				}
				else if(response.equalsIgnoreCase("No")){
					//saving the captured information
					ProjectDetails.saveProjectDetails(projectNumber, projectName, buildingType, erfNumber, feeCharged, amountPaid, date, 
							nonCompletionDate, outstandingAmount);
				}
				else {
					System.out.println("Incorrect input!");
				}
			}
			// Update data in the data base
			else if(action.equalsIgnoreCase("Update")) {
				
				DisplayProjects.displayAllProjects(statement);
				
				System.out.println("\nEnter project number you want to edit:");
				int projectNumber = Integer.parseInt(input.nextLine());
				DisplayProjects.displayChosenProject(projectNumber);
				
				System.out.println("\nEnter number of field you want to edit: ");
				int numField = Integer.parseInt(input.nextLine());
				
				System.out.println("\nEnter updated information: ");
				String updatedContent = input.nextLine();
				Modify.ModifyDetails(updatedContent, projectNumber, numField);
				//Update date
				if(numField == 8) {
					
					String date = input.nextLine();
					
					String query = "UPDATE project_details SET due_date = ? WHERE project_number = ?";
					
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, date);
					preparedStatement.setInt(2, projectNumber);
					
					preparedStatement.executeUpdate();
					System.out.println("Information has been successfully updated .");
				}
			}
			else if(action.equalsIgnoreCase("View")) {
				// View incomplete or past due date projects
				LocalDate date2 = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String completionDate = date2.format(formatter);
				
				DisplayProjects.displayAllProjects(statement);
				
				System.out.println("\nDo you want to view incomplete projects or projects that are past due date?Enter (incomplete/overdue): ");
				String view = input.nextLine();
				
				if(view.equalsIgnoreCase("incomplete")) {
					
					DisplayProjects.incompleteProjects();
				}
				else if(view.equalsIgnoreCase("overdue")) {
					
					DisplayProjects.displayPastDueDateProjects(completionDate);
				}
				else {
					
				}
			}
			else {
				System.out.println("Incorrect input!!!");
				}

			input.close();
			
			System.out.println("\nHave a nice day.");
			
		}
		catch(SQLException e) {
			System.out.println("Error occured.");
			e.printStackTrace();
		}
	}

}
