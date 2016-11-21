/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import View.*;
/*
 * Controller_Login Class in order to handle all the login 
 * related data between the Model and Viewer.
 */
public class Login extends Controller {

	public Login() {
		super();
	}
	
	/*
	 * Method to authenticate a user with email and password.
	 */
	public Boolean authenticateUser(String email, String password){
		try {
			String query="SELECT * FROM User WHERE email1 = ? AND password = ? AND Status = 1";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			return db.executeForBooleanReturn(ps);
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Login.authenticateUser Method. Please try again later.\n\n");
		}
		return false;
	}
	
	/*
	 * Method to get the UserTypeID of the current user.
	 */
	public int getUserType(String email){
		try {
			String query="SELECT usertypeid FROM User WHERE email1 = ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs= db.executeForResultSetReturn(ps);
			if(!rs.next()){
				return 0;
			}else{
				return rs.getInt(1);
			}
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Login.getUserType Method. Please try again later.\n\n");
		}
		return 0;
	}
	
	/*
	 * Method to get the Email Address of a user.
	 */
	public String getEmailAddress(String username){
		try {
			String query="SELECT email1 FROM User WHERE username = ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs= db.executeForResultSetReturn(ps);
			if(!rs.next()){
				return null;
			}else{
				return rs.getString(1);
			}
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Login.getUserType Method. Please try again later.\n\n");
		}
		return null;
	}
	
	/*
	 * Method to get the details of a normal user.
	 */
	public User getUserDetails(String email){
		User u = null;
		try {
			String query="SELECT Username,Password,Email2,FirstName,LastName,AboutMe,PhotoURL1,PhotoURL2,PhotoURL3,PostalArea,karma,usertypeid,MINUTE(datecreated) FROM `user` NATURAL JOIN enduser WHERE Email1 = ?";
			String[] email_list= new String[2];
			String[] photo_list= new String[3];
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs= db.executeForResultSetReturn(ps);
			if(rs.next()){
				int karma=rs.getInt(11);
				int utype=rs.getInt(12);
				long time=rs.getLong(13);
				email_list[0]=email;
				email_list[1]=rs.getString(3);
				photo_list[0]=rs.getString(7);
				photo_list[1]=rs.getString(8);
				photo_list[2]=rs.getString(9);
				u = new User(rs.getString(1), rs.getString(2), email_list, rs.getString(4), rs.getString(5), rs.getString(10), rs.getString(6), photo_list,karma,utype,time);
				return u;
			}
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Login.getUserDetails Method. Please try again later.\n\n");
		}
		return u;
	}
	
	/*
	 * Method to get the details of the Moderator.
	 */
	public Mod getModeratorDetails(String email){
		Mod u = null;
		try {
			String query="SELECT Username,Password,Email2,FirstName,LastName,AboutMe,PhotoURL1,PhotoURL2,PhotoURL3,PostalArea,Phone FROM `user` NATURAL JOIN `moderator` WHERE Email1 = ?";
			String[] email_list= new String[2];
			String[] photo_list= new String[3];
			ArrayList<String> qual = new ArrayList<String>();
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs= db.executeForResultSetReturn(ps);
			if(rs.next()){
				email_list[0]=email;
				email_list[1]=rs.getString(3);
				photo_list[0]=rs.getString(7);
				photo_list[1]=rs.getString(8);
				photo_list[2]=rs.getString(9);
				u = new Mod(rs.getString(1), rs.getString(2), email_list, rs.getString(4), rs.getString(5), rs.getString(10), rs.getString(6), photo_list,Long.valueOf(rs.getString(11)));
			}
			ps.close();
			rs.close();
			
			//Query for extracting the Qualifications
			query="SELECT Description FROM moderatorqualification NATURAL JOIN qualification WHERE Username = ?";
			ps=db.getConn().prepareStatement(query);
			ps.setString(1, u.getUserName());
			rs = db.executeForResultSetReturn(ps);
			while(rs.next()){
				qual.add(rs.getString(1));
			}
			u.setProfQual(qual);
			return u;
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Login.getModeratorDetails Method. Please try again later.\n\n");
		}
		return u;
	}
	
	/*
	 * Method to get the details of the Administrator.
	 */
	public Admin getAdminDetails(String email){
		Admin u = null;
		try {
			String query="SELECT Username,Password,Email2,FirstName,LastName,AboutMe,PhotoURL1,PhotoURL2,PhotoURL3,PostalArea,Phone FROM `user` NATURAL JOIN `administrator` WHERE Email1 = ?";
			String[] email_list= new String[2];
			String[] photo_list= new String[3];
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs= db.executeForResultSetReturn(ps);
			if(rs.next()){
				email_list[0]=email;
				email_list[1]=rs.getString(3);
				photo_list[0]=rs.getString(7);
				photo_list[1]=rs.getString(8);
				photo_list[2]=rs.getString(9);
				u = new Admin(rs.getString(1), rs.getString(2), email_list, rs.getString(4), rs.getString(5), rs.getString(10), rs.getString(6), photo_list,Long.valueOf(rs.getString(11)));
				return u;
			}
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Login.getAdministratorDetails Method. Please try again later.\n\n");
		}
		return u;
	}
	
	/*
	 * Method to deactivate a account with his username.
	 */
	public Boolean deactivateMyProfile(String userName) {
		try {
			String query="UPDATE user SET status = 0 WHERE Username=?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1,userName);
			return(db.executeForDMLBooleanReturn(ps));
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Login.deactivateMyProfile Method. Please try again later.\n\n");
		}
		return false;
	}
	
	/*
	 * Method to reactivate a account with his username.
	 */
	public boolean reactivateAccount(String email, String passwd) {
		try {
			String query="UPDATE user SET status = 1 WHERE email1=? AND password=?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, passwd);
			return db.executeForDMLBooleanReturn(ps);
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Login.reactivateMyProfile Method. Please try again later.\n\n");
		}
		return false;
	}
}