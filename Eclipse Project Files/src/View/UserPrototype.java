/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package View;
import java.util.ArrayList;

public abstract class UserPrototype {
	protected String userName;
	protected static ArrayList<String> usernameList=new ArrayList<String>();
	protected String password;
	
	protected String[] email;
	protected static ArrayList<String> emailList=new ArrayList<String>();
	
	protected String firstName;
	protected String lastName;
	protected String postalAddress;
	protected String aboutMe;
	protected String[] profilePhotoUrls;
	
	public UserPrototype(String uname,String pwd,String[] email,String fname, String lname, String add, String about, String[] profilePhoto){
		this.userName=uname;
		this.password=pwd;
		//email=new String[2];
		this.email=email;
		this.firstName=fname;
		this.lastName=lname;
		this.postalAddress=add;
		this.aboutMe=about;
		//profilePhotoUrls=new String[3];
		this.profilePhotoUrls=profilePhoto;
	}
	
	public Boolean authenticate(String email, String pwd){
		if(email.equals(this.email[0]) && pwd.equals(password))
			return true;
		else
			return false;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public void setPassword(String pwd){
		this.password=pwd;
	}
	
	public void setEmail(String[] email){
		this.email=email;
	}
	
	public String[] getEmail(){
		return this.email;
	}
	
	public void setName(String fname,String lname){
		this.firstName=fname;
		this.lastName=lname;
	}
	
	public String getName(){
		String t=this.firstName;
		return t.concat(" "+this.lastName);
	}
	
	public void setPostalAddress(String add){
		this.postalAddress=add;
	}
	
	public String getPostalAddress(){
		return this.postalAddress;
	}
	
	public void setAboutMe(String add){
		this.aboutMe=add;
	}
	
	public String getAboutMe(){
		return this.aboutMe;
	}
	
	public void setProfilePhotoUrls(String[] url){
		this.profilePhotoUrls=url;
	}
	
	public String[] getProfilePhotoUrls(){
		return this.profilePhotoUrls;
	}
}
