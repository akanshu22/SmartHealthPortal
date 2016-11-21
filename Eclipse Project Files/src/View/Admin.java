/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package View;
/*
 * Admin class to handle Administrator user.
 */
public class Admin extends UserPrototype {

	protected long emergencyContactNo;
	
	//Admin class constructor which utilizes super class constructor.
	public Admin(String uname, String pwd, String[] email, String fname,
			String lname, String add, String about, String[] profilePhoto, long no) {
		super(uname, pwd, email, fname, lname, add, about, profilePhoto);
		
		this.emergencyContactNo=no;
	}
	
	//Getter Function for emergencyContactNo
	public long getEmergencyContactNo(){
		return this.emergencyContactNo;
	}
	
	//displayData class to display the user profile
	public void displayData() {
		System.out.println("\t\t\t!!!___Welcome "+this.userName+" to Your Profile___!!!\n\n");
		System.out.print("Username: "+this.getUserName()+"\n");
		String[] e=this.getEmail();
		
		System.out.print("Primary Email: "+e[0]+"\n");
		System.out.print("Alternate Email: "+e[1]+"\n");
		System.out.print("First and Last Name: "+this.getName()+"\n");
		System.out.print("Emergency Contact Number: "+this.getEmergencyContactNo()+"\n");
		System.out.print("Postal Address: "+this.getPostalAddress()+"\n");
		System.out.print("About Me: "+this.getAboutMe()+"\n");
		System.out.print("Profile Photo URLs: \n\t1: "+this.getProfilePhotoUrls()[0]+"\n\t2: "+this.getProfilePhotoUrls()[1]+"\n\t3: "+this.getProfilePhotoUrls()[2]+"\n\n");
	}
}
