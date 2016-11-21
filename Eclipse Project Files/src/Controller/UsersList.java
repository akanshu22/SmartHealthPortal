package Controller;

public class UsersList {
	String username;
	String firstname;
	String lastname;
	String status;
	String email;
	
	public UsersList(String string, String string2, String string3) {
		username=string;
		firstname=string2;
		lastname=string3;
	}
	public UsersList(String uname, String fname, String lname, String email) {
		username=uname;
		firstname=fname;
		lastname=lname;
		this.email=email;
	}
	public UsersList(String string, String string2) {
		username=string;
		status=string2;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}