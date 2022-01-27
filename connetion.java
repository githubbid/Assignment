package curd;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner; 
//import java.sql.PreparedStatement;


public class connetion {

	 public static void main(String[] args)
	 {
			String jdbcURL = "jdbc:mysql://localhost:3306/demo1";
	        String dbUser = "root";
	        String dbPassword = "root";
	        
	  
	  {
		 System.out.println("Select the options from below menu:- ");
		 System.out.println();
		 System.out.println(" Press 1 to insert the Student data\n Press 2 to Update the Student data\n Press 3 to Delete the Student data\n Press 4 to get a list of all Students\n Press 5 to Get one student details ");
		 Scanner sc = new Scanner(System.in);
		 var DataInput = sc.nextLine();
		 switch (DataInput)
		 {
		 case "1":
			 try (Connection con = DriverManager.getConnection(jdbcURL, dbUser, dbPassword))
			 {
				System.out.println("Enter Student First Name :");
				var fname=sc.nextLine();
				System.out.println(fname);
				System.out.println("Enter Student Last Name :");
				var lname=sc.nextLine();
				System.out.println(lname);
				System.out.println("Enter Student Email :");
				var email=sc.nextLine();
				System.out.println(email);
				
				PreparedStatement stmt = con.prepareStatement("insert into student (first_name,last_name,email)" + "VALUES (?,?,?)");
				stmt.setString(1, fname);
				stmt.setString(2, lname);
				stmt.setString(3, email);
				
				stmt.executeUpdate();
				
				System.out.println("Student is inserted Successfully!");
			}
	 
	            catch(SQLException e)
	           {
		            e.printStackTrace();
	           }
			 break;
 
		  case "2":
			 
				 try (Connection con = DriverManager.getConnection(jdbcURL, dbUser, dbPassword))
				 {
				 System.out.println("Enter Student id :");
				 String id=sc.nextLine();
				 System.out.println("Enter Student First Name :");
				 var fname=sc.nextLine();
				 System.out.println(fname);
				 System.out.println("Enter Student Last Name :");
				 var lname=sc.nextLine();
				 System.out.println(lname);
				 System.out.println("Enter Student Email :");
				 var email=sc.nextLine();
				 System.out.println(email);
					

				 String sql = "update student set first_name=?, last_name=?, email=? where id=?";
				 PreparedStatement ps=con.prepareStatement(sql);  
				 
				 ps.setString(1, fname);
				 ps.setString(2, lname);
				 ps.setString(3, email);
				 ps.setString(4, id);
				 
				int rowupdated = ps.executeUpdate();
				if (rowupdated>0)
				{
				 System.out.println("the users are updated sucessfully!");
				 
				 }
				 }
				 catch (SQLException e)
			 {
				 e.printStackTrace();
			 }
			 break; 
			 
		  case "3":
			  try (Connection con = DriverManager.getConnection(jdbcURL, dbUser, dbPassword))
			  {
				  String sql = "DELETE FROM student  WHERE id=?";
				  
				  System.out.println("Enter Student ID :");
				  var id=sc.nextLine();
				  System.out.println(id);
						
				  PreparedStatement statement = con.prepareStatement(sql);
				  statement.setString(1,id);
				  
				  int rowsDeleted = statement.executeUpdate();
				  if (rowsDeleted > 0) {
				  System.out.println("A user was deleted successfully!");}
			  }
			  
			  catch (SQLException e)
				 {
					 e.printStackTrace();
				 }
			  break;
			  
		  case "4":
			  ResultSet rs = null;
			  try (Connection con = DriverManager.getConnection(jdbcURL, dbUser, dbPassword))
			  {
		        
				String sql = "select id, first_name, last_name, email from student";
				    Statement stmt = con.createStatement();
			    	 rs = stmt.executeQuery(sql);
					
					while (rs.next())
					{
						System.out.println("ID: " + rs.getInt("id"));
			            System.out.println(" Firstname: " + rs.getString("first_name"));
			            System.out.println(" Lastname: " + rs.getString("last_name"));
			            System.out.println(" Email: " + rs.getString("email"));

						
					}
			}
					catch (SQLException e) 
					{ 
						e.printStackTrace();
						
					}
		 
			  break;
			  
		  case "5":
			  
			
			  	   try (Connection con = DriverManager.getConnection(jdbcURL, dbUser, dbPassword))
				 {

				  ResultSet rss = null;
				  ArrayList students = new ArrayList();

                  String sql = "select id, first_name, last_name, email from student where id=6";
                   
			    	  PreparedStatement stmt = con.prepareStatement(sql);
			    	  rss = stmt.executeQuery(sql);
					   
					while (rss.next())
					{
						int id = rss.getInt("id");
						String first_name = rss.getString("first_name");
			            String last_name = rss.getString("last_name");
			            String email =  rss.getString("email");
			            students.add(id);
                        students.add(first_name);
                        students.add(last_name);
                        students.add(email);
                        System.out.println(students);
						
					}
				  
			  }
			  
				catch (SQLException e) 
				{ 
					e.printStackTrace();
					
				}
			  	   //return students;
			  
	        break;
		  default:
			
		     System.out.println("Enter a correct Choice: ");
		     System.out.println("you have exited the program");
	       }
        }
	 }
}
