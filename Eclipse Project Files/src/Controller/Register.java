/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package Controller;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
/*
 * Controller_Register Class in order to handle all the Registration 
 * related data between the Model and Viewer.
 */
public class Register extends Controller {

	public Register() {
		super();
	}
	
	/*
	 * Method to register a normal user on the database.
	 */
	public void registerUser(String username, String password, String email1, String email2, String first_name, String last_name, String about_me, String photoURL1, String photoURL2, String photoURL3, String street_no, String street_name, String MajorMuniciality, String GoverningDistrict, String PostalArea, int UserTypeId, int Status){
		try{
			String query = "INSERT INTO `user` VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email1);
			ps.setString(4, email2);
			ps.setString(5, first_name);
			ps.setString(6, last_name);
			ps.setString(7, about_me);
			ps.setString(8, photoURL1);
			ps.setString(9, photoURL2);
			ps.setString(10, photoURL3);
			ps.setString(11, street_no);
			ps.setString(12, street_name);
			ps.setString(13, MajorMuniciality);
			ps.setString(14, GoverningDistrict);
			ps.setString(15, PostalArea);
			ps.setInt(16, UserTypeId);
			ps.setInt(17, Status);
			db.executeForNoReturn(ps);
			
			query = "INSERT INTO `enduser` VALUES(?,?,CURTIME())";
			ps.close();
			ps = db.getConn().prepareStatement(query);
			ps.setString(1, username);
			ps.setInt(2, 0);
			db.executeForNoReturn(ps);
		}
		catch(Exception e){
			System.out.println("Something bad happened in Controller_Register.registerUser Method. Please try again later.\n\n");
		}
	}
	
	/*
	 * Method to register an Administrator on the database.
	 */
	public void addAdministrator(String username, String phone){
		try{
			String query = "INSERT INTO `administrator` VALUES(?, ?)";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, phone);
			db.executeForNoReturn(ps);
		}
		catch(Exception e){
			System.out.println("Something bad happened in Controller_Register.addAdministrator Method. Please try again later.\n\n");
		}
	}
	
	/*
	 * Method to register a Moderator on the database.
	 */
	public void addModerator(String username, String phone, ArrayList<String> qual){
		try{
			String query = "INSERT INTO `moderator` VALUES(?, ?)";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, phone);
			db.executeForNoReturn(ps);
			ps.close();
			
			query = "{CALL smarthealthdb.mod_qualification(?,?)}";
			for(String qualification:qual){
				CallableStatement cs= db.getConn().prepareCall(query);
				cs.setString(1, qualification);
				cs.setString(2, username);
				db.executeForNoReturn(cs);
				cs.close();
			}
		}
		catch(Exception e){
			System.out.println("Something bad happened in Controller_Register.addModerator Method. Please try again later.\n\n");
		}
	}
	
	/*
	 * Method to verify the uniqueness of the username.
	 */
	public boolean isUsernameUnique(String username) {
		try {
			String query="SELECT * FROM User WHERE username = ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, username);
			System.out.println(ps.toString());
			return db.executeForBooleanReturn(ps);
		}
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Register.isUsernameUnique Method. Please try again later.\n\n");
		}
		return false;
	}
	
	/*
	 * Method to verify the uniqueness of the Primary and Secondary Email.
	 */
	public Boolean isEmailUnique(String email){
		try {
			String query="SELECT * FROM User WHERE email1 = ? OR email2 = ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, email);
			System.out.println(ps.toString());
			return db.executeForBooleanReturn(ps);
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Register.isEmailUnique Method. Please try again later.\n\n");
		}
		return false;
	}
	
	/*
	 * Method to update the usertype of a normal user.
	 */
	public void updateUserType(String userName, int type) {
		try {
			String query="UPDATE user SET usertypeid = ? WHERE username=?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setInt(1, type);
			ps.setString(2, userName);
			db.executeForDMLBooleanReturn(ps);
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Register.updateUserType Method. Please try again later.\n\n");
		}
	}	
}