/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package Controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * HealthData Controller Class in order to handle all the Health 
 * related data between the Model and Viewer.
 */
public class HealthData extends Controller {

	public HealthData() {
		super();
	}

	/*
	 * Method to validate Username
	 * provided by the user for health data related queries.
	 */
	public boolean validateUsername(String username) {
		try {
			String query="SELECT * FROM User WHERE username = ? AND UserTypeID < 4 AND Status = 1";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, username);
			return db.executeForBooleanReturn(ps);
		}
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Register.isUsernameUnique Method. Please try again later.\n\n");
		}
		return false;
	}

	/*
	 * Method to insert Health Data into the database
	 * available in this Smart Health web portal.
	 */
	public Boolean insertHealthData(String username, int input, String value) {
		try{
			String query = "INSERT INTO datum (Username,PropertyID,Value,WhenSaved) VALUES(?,?,?,curtime())";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, username);
			ps.setInt(2, input);
			ps.setString(3, value);
			return db.executeForDMLBooleanReturn(ps);
		}catch(Exception e){
			System.out.println("Something bad happened in Controller.HealthData.insertHealthData Method. Please try again later.\n\n");
		}
		return false;		
	}

	/*
	 * Method to get list of all users
	 * available in this Smart Health web portal for health data related queries.
	 */
	public ArrayList<String> getHealthUsersList() {
		try {
			ArrayList<String> names=null;
			String query="SELECT Username FROM User WHERE UserTypeID < 4 AND Status = 1";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ResultSet rs=db.executeForResultSetReturn(ps);
			if(rs.isBeforeFirst())
				names = new ArrayList<String>();
			while(rs.next()!=false){
				names.add(rs.getString(1));
			}
			return names;
		}catch (SQLException e) {
			System.out.println("Something bad happened in Controller.HealthData.getHealthUsersList Method. Please try again later.\n\n");
		}
		return null;
	}

	/*
	 * Method to retrieve Health Data
	 * corresponding to a particular username
	 */
	public ArrayList<View.HealthData> retrieveHealthData(String username) {
		ArrayList<View.HealthData> healthlist=null;
		try{
			String query = "SELECT WhenSaved, Name, Value FROM datum NATURAL JOIN property WHERE Username = ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs=db.executeForResultSetReturn(ps);
			healthlist = new ArrayList<View.HealthData>();
			if(rs.next()==false){
				System.out.println("You don't have your health data ready as of now. Please try again later.");
				return healthlist;
			}
			rs.beforeFirst();
			while(rs.next()!=false){
				View.HealthData hd = new View.HealthData(rs.getString(1), rs.getString(2), rs.getString(3));
				healthlist.add(hd);
			}
		}catch (SQLException e) {
			System.out.println("Something bad happened in Controller.HealthData.retrieveHealthData Method. Please try again later.\n\n");
		}finally {
			return healthlist;
		}
	}
	
}
