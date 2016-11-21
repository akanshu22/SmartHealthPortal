/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import View.Comments;
import View.Forum;
import View.Posts;

/*
 * Controller class to handle Discussion Forum related interaction between Model and the View.
 */
public class DiscussionForum extends Controller {

	public DiscussionForum() {
		super();
	}

	public ArrayList<Forum> getActiveForumsList() {
		try {
			ArrayList<Forum> forums=null;
			Forum c;
			String query="SELECT ForumID,Topic,URL,Summary,WhenCreated,CreatedByModerator_Username from forum WHERE WhenClosed IS NULL";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ResultSet rs=db.executeForResultSetReturn(ps);
			if(rs.isBeforeFirst())
				forums = new ArrayList<Forum>();
			while(rs.next()!=false){
				c = new Forum(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6),rs.getString(5));
				forums.add(c);
			}
			return forums;
		}catch (SQLException e) {
			System.out.println("Something bad happened in Controller.DiscussionForum.getActiveForumsList Method. Please try again later.\n\n");
		}
		return null;
	}
	
	public Forum getForum(int id) {
		try {
			Forum forum=null;
			String query="SELECT ForumID,Topic,URL,Summary,WhenCreated,CreatedByModerator_Username from forum WHERE WhenClosed IS NULL AND ForumID = ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs=db.executeForResultSetReturn(ps);
			if(rs.next()!=false){
				forum = new Forum(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6),rs.getString(5));				
			}
			return forum;
		}catch (SQLException e) {
			System.out.println("Something bad happened in Controller.DiscussionForum.getActiveForumsList Method. Please try again later.\n\n");
		}
		return null;
	}

	public ArrayList<Forum> getDeletedForumsList() {
		try {
			ArrayList<Forum> forums=null;
			Forum c;
			String query="SELECT ForumID,Topic,URL,Summary,WhenCreated,WhenClosed,CreatedByModerator_Username,DeletedByModerator_Username from forum WHERE WhenClosed IS NOT NULL";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ResultSet rs=db.executeForResultSetReturn(ps);
			if(rs.isBeforeFirst())
				forums = new ArrayList<Forum>();
			while(rs.next()!=false){
				c = new Forum(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(7), rs.getString(5));
				c.setDeleted_by(rs.getString(8));
				c.setWhen_closed(rs.getString(6));
				forums.add(c);
			}
			return forums;
		}catch (SQLException e) {
			System.out.println("Something bad happened in Controller.DiscussionForum.getDeletedForumsList Method. Please try again later.\n\n");
		}
		return null;
	}

	public Boolean createForum(String username, String topic, String summary) {
		try{
			String query = "SELECT MAX(ForumID)+1 FROM forum";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ResultSet rs = db.executeForResultSetReturn(ps);
			rs.next();
			String url = "http://www.anshulkhantwal.com/forum/"+rs.getInt(1);
			
			query = "INSERT INTO forum(Topic,URL,Summary,WhenCreated,CreatedByModerator_Username) VALUES(?,?,?,curtime(),?)";
			ps = db.getConn().prepareStatement(query);
			ps.setString(1, topic);
			ps.setString(2, url);
			ps.setString(3, summary);
			ps.setString(4, username);
			

			String query2 = "UPDATE enduser SET Karma = Karma + 10 WHERE Username = ?";
			PreparedStatement ps2 = db.getConn().prepareStatement(query2);
			ps2.setString(1, username);
			return db.executeForDMLBooleanReturn(ps) && db.executeForDMLBooleanReturn(ps2);
		}catch(Exception e){
			System.out.println("Something bad happened in Controller.DiscussionForum.createForum Method. Please try again later.\n\n");
		}
		return false;
	}

	public boolean deleteForum(String username, int forumid) {
		try{
			String query = "UPDATE forum SET WhenClosed = curtime(), DeletedByModerator_Username = ? WHERE ForumID = ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, username);
			ps.setInt(2, forumid);
			

			String query2 = "UPDATE enduser SET Karma = Karma + 5 WHERE Username = ?";
			PreparedStatement ps2 = db.getConn().prepareStatement(query2);
			ps2.setString(1, username);
			return db.executeForDMLBooleanReturn(ps) && db.executeForDMLBooleanReturn(ps2);
		}catch(Exception e){
			System.out.println("Something bad happened in Controller.DiscussionForum.deleteForum Method. Please try again later.\n\n");
		}
		return false;
	}
	
	public boolean isForumExists(int forumid){
		try{
			String query = "SELECT ForumID FROM forum WHERE ForumID = ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setInt(1, forumid);
			return db.executeForBooleanReturn(ps);
		}catch(Exception e){
			System.out.println("Something bad happened in Controller.DiscussionForum.isForumExists Method. Please try again later.\n\n");
		}
		return false;
	}

	//User's View
	
	public boolean createNewPost(String username, int forumid, String post) {
		try{
			String query = "INSERT INTO post VALUES(?,curtime(),?,?,NULL,NULL,NULL)";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, username);
			ps.setInt(2, forumid);
			ps.setString(3, post);
			
			String query2 = "UPDATE enduser SET Karma = Karma + 10 WHERE Username = ?";
			PreparedStatement ps2 = db.getConn().prepareStatement(query2);
			ps2.setString(1, username);
			return db.executeForDMLBooleanReturn(ps) && db.executeForDMLBooleanReturn(ps2);
			
			
		}catch(Exception e){
			System.out.println("Something bad happened in Controller.DiscussionForum.createNewPost Method. Please try again later.\n\n");
		}
		return false;
	}
	
	public ArrayList<Posts> fetchPostsWithForumID(int forumid){
		ArrayList<Posts> posts_list=null;
		try{
			String query = "SELECT Username,TimeCreated,TextEntry FROM post WHERE ForumID = ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setInt(1, forumid);
			ResultSet rs = db.executeForResultSetReturn(ps);
			if(rs.next()==false){
				return null;
			}
			posts_list= new ArrayList<Posts>();
			rs.beforeFirst();
			while(rs.next()){
				Posts p=new Posts(rs.getString(1), rs.getString(2), rs.getString(3), 0);
				posts_list.add(p);
			}
			return posts_list;
		}catch(Exception e){
			System.out.println("Something bad happened in Controller.DiscussionForum.fetchPostsWithForumID Method. Please try again later.\n\n");
		}
		return posts_list;
	}
	
	public boolean commentOnPost(String post_username, String post_time, String commenter_username, String comment_text){
		try{
			String query = "INSERT INTO comment VALUES(?,?,?,curtime(),?,NULL,NULL,NULL)";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, post_username);
			ps.setString(2, post_time);
			ps.setString(3, commenter_username);
			ps.setString(4, comment_text);
			

			String query2 = "UPDATE enduser SET Karma = Karma + 5 WHERE Username = ?";
			PreparedStatement ps2 = db.getConn().prepareStatement(query2);
			ps2.setString(1, commenter_username);
			return db.executeForDMLBooleanReturn(ps) && db.executeForDMLBooleanReturn(ps2);
		}catch(Exception e){
			System.out.println("Something bad happened in Controller.DiscussionForum.commentOnPost Method. Please try again later.\n\n");
		}
		return false;	
	}
	
	public ArrayList<Posts> fetchAllPosts(String username){
		ArrayList<Posts> posts_list=null;
		try{
			String query = "SELECT Username,TimeCreated,TextEntry FROM post WHERE Username <> ?";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = db.executeForResultSetReturn(ps);
			if(rs.next()==false){
				return null;
			}
			posts_list= new ArrayList<Posts>();
			rs.beforeFirst();
			while(rs.next()){
				Posts p=new Posts(rs.getString(1), rs.getString(2), rs.getString(3), 0);
				posts_list.add(p);
			}
			return posts_list;
		}catch(Exception e){
			System.out.println("Something bad happened in Controller.DiscussionForum.fetchPostsWithForumID Method. Please try again later.\n\n");
		}
		return posts_list;
	}

	public boolean rateThePost(String post_username, String post_time, String rater_username, int rating) {
		try{
			String query = "INSERT INTO rating VALUES(?,?,?,?)";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString(1, post_username);
			ps.setString(2, post_time);
			ps.setString(3, rater_username);
			ps.setInt(4, rating);
			

			String query2 = "UPDATE enduser SET Karma = Karma + 3 WHERE Username = ?";
			PreparedStatement ps2 = db.getConn().prepareStatement(query2);
			ps2.setString(1, rater_username);
			return db.executeForDMLBooleanReturn(ps) && db.executeForDMLBooleanReturn(ps2);
		}catch(Exception e){
			System.out.println("Something bad happened in Controller.DiscussionForum.rateThePost Method. Please try again later.\n\n");
		}
		return false;
	}
	
	public ArrayList<Posts> fetchPostsAlongWithCommentsWithForumID(int forumid){
		ArrayList<Posts> posts_list=null;
		try{
			//String query = "SELECT Username,TimeCreated,TextEntry,avg(rating.Stars) FROM post JOIN rating ON (post.Username=rating.Post_Username AND post.TimeCreated = rating.Post_TimeCreated) WHERE ForumID = ?";
			String query = "SELECT Username,TimeCreated,TextEntry FROM post WHERE ForumID = ? ORDER BY TimeCreated DESC";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setInt(1, forumid);
			ResultSet rs = db.executeForResultSetReturn(ps);
			if(rs.next()==false){
				return null;
			}
			posts_list= new ArrayList<Posts>();
			rs.beforeFirst();
			while(rs.next()){
				ArrayList<Comments> comments_list = null;
				Posts p=new Posts(rs.getString(1), rs.getString(2), rs.getString(3), 0);
				String Query="SELECT Commenter_Username,CommentTime,CommentText from comment WHERE Post_Username = ? AND Post_TimeCreated = ?";
				PreparedStatement ps2 = db.getConn().prepareStatement(Query);
				ps2.setString(1, p.getUsername());
				ps2.setString(2, p.getTime_created());
				ResultSet rs2 = db.executeForResultSetReturn(ps2);
				if(rs2.next()!=false){
					comments_list = new ArrayList<Comments>();
					Comments c = new Comments(rs2.getString(1), rs2.getString(2),rs2.getString(3));
					comments_list.add(c);
					while(rs2.next()){
						c = new Comments(rs2.getString(1), rs2.getString(2),rs2.getString(3));
						comments_list.add(c);
					}
					p.setComments_list(comments_list);
				}
				Query="SELECT avg(rating.Stars) FROM rating WHERE rating.Post_Username = ? AND rating.Post_TimeCreated = ?";
				PreparedStatement ps3 = db.getConn().prepareStatement(Query);
				ps3.setString(1, p.getUsername());
				ps3.setString(2, p.getTime_created());
				ResultSet rs3 = db.executeForResultSetReturn(ps3);
				
				if(rs3.next()!=false){
					p.setRating(rs3.getFloat(1));
				}
				posts_list.add(p);
			}
			return posts_list;
		}catch(Exception e){
			System.out.println("Something bad happened in Controller.DiscussionForum.fetchPostsAlongWithCommentsWithForumID Method. Please try again later.\n\n");
		}
		return posts_list;
	}
}
