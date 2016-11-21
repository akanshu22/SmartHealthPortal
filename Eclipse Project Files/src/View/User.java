/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package View;
import java.util.ArrayList;

import Controller.Register;

public class User extends UserPrototype {

	protected int karma;
	protected int userType; //1 for New, 2 for Middle & 3 for Old
	protected long regDate; //In order to upgrade user's type automatically on the basis of his duration in the website.
	private static long[] typeCriteria;
	protected ArrayList<HealthData> health_data;
	
	public User(String uname, String pwd, String[] email, String fname,
			String lname, String add, String about, String[] profilephoto, int k,int utype,long time) {
		
		super(uname, pwd, email, fname, lname, add, about, profilephoto);
		this.karma=k;
		this.userType=utype;
		this.regDate=time;
		health_data = new ArrayList<HealthData>();

		typeCriteria=new long[2];
		typeCriteria[0]=43800;
		typeCriteria[1]=525600;
		Register r= new Register();
		if(userType==1 && (typeCriteria[0]-regDate)<0){
			userType=2;
			r.updateUserType(this.userName,userType);
		}else if(userType==2 && (typeCriteria[1]-regDate)<0){
			userType=3;
			r.updateUserType(this.userName,userType);
		}
	}
	
	public long getKarma(){
		return this.karma;
	}
	
	public int getUserType(){
		return this.userType;
	}

	public ArrayList<HealthData> getHealth_data() {
		return health_data;
	}

	public void setHealth_data(ArrayList<HealthData> health_data) {
		this.health_data = health_data;
	}

	public void displayData() {
		System.out.println("\t\t\t!!!___Welcome "+this.userName+" to Your Profile___!!!\n\n");
		System.out.print("Username: "+this.getUserName()+"\n");
		String[] e=this.getEmail();
		String[] a={"asd","New","Middle","Old"};
		System.out.print("Primary Email: "+e[0]+"\n");
		System.out.print("Alternate Email: "+e[1]+"\n");
		System.out.print("First and Last Name: "+this.getName()+"\n");
		System.out.print("Usertype: "+a[this.getUserType()]+"\n");
		System.out.print("Postal Address: "+this.getPostalAddress()+"\n");
		System.out.print("About Me: "+this.getAboutMe()+"\n");
		System.out.print("Karma Value: "+this.getKarma()+"\n");
		System.out.print("Profile Photo URLs: \n\t1: "+this.getProfilePhotoUrls()[0]+"\n\t2: "+this.getProfilePhotoUrls()[1]+"\n\t3: "+this.getProfilePhotoUrls()[2]+"\n\n");
	}
}