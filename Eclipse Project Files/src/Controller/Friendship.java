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
 * Controller_Friendship Class in order to handle all the friendship 
 * related data between the Model and Viewer.
 */

public class Friendship extends Controller {

	public Friendship() {
		super();
	}
	
	/*
	 * Method to get list of all users
	 * available in this Smart Health web portal.
	 */
	@SuppressWarnings("finally")
	public ArrayList<UsersList> getUsersList(String uname){
		ArrayList<UsersList> userlist = null;
		try {
			String query="SELECT Username,FirstName,LastName FROM user NATURAL JOIN usertype WHERE UserTypeID<=3 AND username <> ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, uname);
			ResultSet rs= db.executeForResultSetReturn(ps);
			//System.out.println("\n\n\t\t\t!!___See the complete User List___!!\n\n");
			userlist = new ArrayList<UsersList>();
			//int i=1;
			//System.out.println("\tNo. \t Username \t FName \t LName\n");
			while(rs.next()){
				UsersList u = new UsersList(rs.getString(1),rs.getString(2),rs.getString(3));
				//System.out.println("\t"+i+". \t "+rs.getString(1)+" \t "+rs.getString(2)+" \t "+rs.getString(3)+"\n");
				userlist.add(u);
			}
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Friendship.getUsersList Method. Please try again later.\n\n");
		}finally {
			return userlist;
		}
	}
	
	/*
	 * Method to get my list of friends
	 * available in this Smart Health web portal.
	 */
	public ArrayList<String> getMyFriendList(String userName) {
		ArrayList<String> friendlist = null;
		try {
			String query="SELECT `Requester_Username`,`Requested_Username` from friendship where WhenConfirmed is not null and WhenUnfriended is null and (Requester_Username= ? or Requested_Username =?)";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1,userName);
			ps.setString(2,userName);
			ResultSet rs= db.executeForResultSetReturn(ps);
//			System.out.println("\n\n\t\t\t!!___See Your Friend List___!!\n\n");
//			int i=1;
//			System.out.println("\tNo. \t Username\n");
			
			friendlist = new ArrayList<String>();
			while(rs.next()){
				if(rs.getString(1).equalsIgnoreCase(userName)){
					//System.out.println("\t"+i+". \t "+rs.getString(2)+"\n");
					friendlist.add(rs.getString(2));
				}else{
					friendlist.add(rs.getString(1));
				}
				//i++;
			}
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Friendship.getMyFriendList Method. Please try again later.\n\n");
		}finally {
			return friendlist;
		}
	}
	
	/*
	 * Method to unFriend a user.
	 */
	public boolean unFriend(String userName, String uname) {
		try {
			String query="UPDATE Friendship SET WhenUnfriended=CURTIME() where ((Requester_Username = ? AND Requested_Username = ?) OR (Requester_Username = ? AND Requested_Username = ?)) AND WhenConfirmed IS NOT NULL AND WhenRejected IS NULL AND WhenWithdrawn IS NULL AND WhenUnfriended IS NULL";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, userName);
			ps.setString(2, uname);
			ps.setString(4, userName);
			ps.setString(3, uname);
			
			String query2 = "UPDATE enduser SET Karma = Karma - 2 WHERE Username = ?";
			PreparedStatement ps2 = db.getConn().prepareStatement(query2);
			ps2.setString(1, userName);
			return db.executeForDMLBooleanReturn(ps) && db.executeForDMLBooleanReturn(ps2);
		}
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Friendship.sendRequest Method. Please try again later.\n\n");
		}
		return false;
	}

	/*
	 * Method to send the Friend Request to a user.
	 */
	public boolean sendRequest(String userName, String uname) {
		try {
			String query="SELECT * FROM friendship WHERE (Requester_Username=? AND Requested_Username=?) OR (Requester_Username=? AND Requested_Username=?)";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, userName);
			ps.setString(2, uname);
			ps.setString(4, userName);
			ps.setString(3, uname);
			if(!db.executeForBooleanReturn(ps)){
				//ps.close();
				query="INSERT INTO friendship(Requester_Username,Requested_Username,WhenRequested) VALUES(?,?,CURDATE())";
				ps = db.getConn().prepareStatement(query);
				ps.setString(1, userName);
				ps.setString(2, uname);

				String query2 = "UPDATE enduser SET Karma = Karma + 4 WHERE Username = ?";
				PreparedStatement ps2 = db.getConn().prepareStatement(query2);
				ps2.setString(1, userName);
				return db.executeForDMLBooleanReturn(ps) && db.executeForDMLBooleanReturn(ps2);
			}else{
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Friendship.sendRequest Method. Please try again later.\n\n");
		}
		return false;
	}

	/*
	 * Method to withdraw your Friend Request from a user.
	 */
	public boolean withdrawRequest(String userName, String uname) {
		try {
			String query="update Friendship set WhenWithdrawn=CURTIME() where Requester_Username = ? and Requested_Username = ? AND WhenRequested IS NOT NULL AND WhenConfirmed IS NULL";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, userName);
			ps.setString(2, uname);


			String query2 = "UPDATE enduser SET Karma = Karma - 4 WHERE Username = ?";
			PreparedStatement ps2 = db.getConn().prepareStatement(query2);
			ps2.setString(1, userName);
			return db.executeForDMLBooleanReturn(ps) && db.executeForDMLBooleanReturn(ps2);
		}
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Friendship.withdrawRequest Method. Please try again later.\n\n");
		}
		return false;
	}

	/*
	 * Method to get the list of all Pending Friend Request
	 * that needs to be answered on other user's end.
	 */
	public ArrayList<UsersList> getYetToBeAnsweredRequests(String userName) {
		ArrayList<UsersList> pendingrequests = null;
		try {
			String query="SELECT Requested_Username, CASE WHEN WhenRejected IS NOT NULL THEN 'Rejected' WHEN WhenWithdrawn IS NOT NULL THEN 'Withdrawn' WHEN WhenUnfriended IS NOT NULL THEN 'Unfriended' WHEN WhenConfirmed IS NOT NULL THEN 'Accepted' WHEN WhenConfirmed IS NULL THEN 'Not Responded' ELSE NULL END AS status FROM friendship WHERE Requester_Username = ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1,userName);
			ResultSet rs= db.executeForResultSetReturn(ps);
			pendingrequests = new ArrayList<UsersList>();
			//System.out.println("\n\n\t\t\t!!___Friend Requests Pending on other User's End___!!\n\n");
			int i=1;
			//System.out.println("\tNo. \t Username \t Status\n");
			while(rs.next()){
					UsersList u = new UsersList(rs.getString(1), rs.getString(2));
					pendingrequests.add(u);
//					System.out.println("\t"+i+". \t "+rs.getString(1)+" \t "+rs.getString(2)+"\n");
					i++;
			}	
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Friendship.getYetToBeAnsweredRequests Method. Please try again later.\n\n");
		}finally {
			return pendingrequests;
		}
	}
	
	/*
	 * Method to search a user.
	 */
	public ArrayList<UsersList> searchUser(String uname){
		ArrayList<UsersList> searchlist=null;
		try {
			String query="SELECT Username,FirstName,LastName,Email1 FROM smarthealthdb.user WHERE Username = ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1,uname);
			ResultSet rs= db.executeForResultSetReturn(ps);
			searchlist = new ArrayList<UsersList>();
			while(rs.next()){
					UsersList u = new UsersList(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4));
					searchlist.add(u);
			}
		}catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Friendship.searchUser Method. Please try again later.\n\n");
		}finally {
			return searchlist;
		}
	}

	/*
	 * Method to accept a Friend Request from a user.
	 */
	public boolean acceptFriendRequest(String userName, String uname) {
		try {
			String query="UPDATE Friendship SET WhenConfirmed=CURTIME() WHERE Requester_Username = ? and Requested_Username = ? AND WhenWithdrawn IS NULL";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, uname);
			ps.setString(2, userName);


			String query2 = "UPDATE enduser SET Karma = Karma + 4 WHERE Username = ?";
			PreparedStatement ps2 = db.getConn().prepareStatement(query2);
			ps2.setString(1, userName);
			return db.executeForDMLBooleanReturn(ps) && db.executeForDMLBooleanReturn(ps2);
		}
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Friendship.acceptFriendRequest Method. Please try again later.\n\n");
		}
		return false;
	}
	
	/*
	 * Method to reject a Friend Request from a user.
	 */
	public boolean rejectFriendRequest(String userName, String uname) {
		try {
			String query="UPDATE Friendship set WhenRejected=CURTIME() where Requester_Username = ? and Requested_Username = ? AND WhenWithdrawn IS NULL";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, uname);
			ps.setString(2, userName);


			String query2 = "UPDATE enduser SET Karma = Karma + 1 WHERE Username = ?";
			PreparedStatement ps2 = db.getConn().prepareStatement(query2);
			ps2.setString(1, userName);
			return db.executeForDMLBooleanReturn(ps) && db.executeForDMLBooleanReturn(ps2);
		}
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Friendship.rejectFriendRequest Method. Please try again later.\n\n");
		}
		return false;
	}

	/*
	 * Method to get the list of all Pending Friend Request
	 * that needs to be answered by me.
	 */
	public ArrayList<String> getPendingRequestsOnMyEnd(String userName) {
		ArrayList<String> requests = null;
		try {
			String query="SELECT Requester_Username from friendship WHERE WhenWithdrawn IS NULL AND WhenRejected IS NULL AND WhenConfirmed IS NULL AND Requested_Username=?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1,userName);
			ResultSet rs= db.executeForResultSetReturn(ps);
			requests = new ArrayList<String>();
			//System.out.println("\n\n\t\t\t!!___Friend Requests Pending on My End___!!\n\n");
			int i=1;
			//System.out.println("\t\tNo. \t Username\n");
			while(rs.next()){
					requests.add(rs.getString(1));
					//System.out.println("\t\t"+i+". \t "+rs.getString(1)+"\n");
					i++;
			}	
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Friendship.getPendingRequestsOnMyEnd Method. Please try again later.\n\n");
		}
		finally {
			return requests;
		}
	}

	/*
	 * Method to check whether a given user
	 * is my friend or not
	 */
	public Boolean isMyFriend(String userName,String check_username) {
		try {
			String query="SELECT `Requester_Username`,`Requested_Username` from friendship where WhenConfirmed is not null and WhenUnfriended is null and ((Requester_Username= ? AND Requested_Username =?)OR(Requester_Username= ? AND Requested_Username =?))";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1,userName);
			ps.setString(2,check_username);
			ps.setString(3,check_username);
			ps.setString(4,userName);
			return db.executeForBooleanReturn(ps);
		} 
		catch (SQLException e) {
			System.out.println("Something bad happened in Controller_Friendship.getMyFriendList Method. Please try again later.\n\n");
		}
		return false;
	}
	
}