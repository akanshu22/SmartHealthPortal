/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * Model Class to handling the database schema.
 */
public class Model {
	
	private Connection conn = null;
	
	//JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/smarthealthdb?autoReconnect=true&useSSL=false";

	//Database credentials
	static final String USER = "akanshu22";
	static final String PASS = "admin";
	
	public Model(){
		
		//Registering JDBC Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Something bad happened in Model.Constructor. Please try again later.\n\n");
		}
		
		//Initializing a connection
		try {
			this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			System.out.println("Something bad happened in Model.Constructor. Please try again later.\n\n");
		}
		
	}
	
	//Method to execute a query
	public void executeForNoReturn(PreparedStatement stmt){
		try{
			stmt.execute();
		}catch(Exception e){
			System.out.println("Something bad happened in Model.executeForNoReturn Method. Please try again later.\n\n");
		}
	}
	
	//Method to execute a query and get the status of the query
	public Boolean executeForBooleanReturn(PreparedStatement stmt){
		try{
			ResultSet rs= stmt.executeQuery();
			return(rs.next());
		}catch(Exception e){
			System.out.println("Something bad happened in Model.executeForBooleanReturn Method. Please try again later.\n\n");
		}
		return false;
	}
	
	public Boolean executeForDMLBooleanReturn(PreparedStatement stmt){
		try{
			return(stmt.executeUpdate()!=0);
		}catch(Exception e){
			System.out.println("Something bad happened in Model.executeForDMLBooleanReturn Method. Please try again later.\n\n");
		}
		return false;
	}
	
	//Method to execute a query and get the resultSet
	public ResultSet executeForResultSetReturn(PreparedStatement stmt){
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery();
		}
		catch(Exception e){
			System.out.println("Something bad happened in Model.executeForResultSetReturn Method. Please try again later.\n\n");
		}
		return rs;
	}

	//Return's the conn object
	public Connection getConn() {
		return conn;
	}
}