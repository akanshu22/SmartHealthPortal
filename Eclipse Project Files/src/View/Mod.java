/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package View;
import java.util.ArrayList;
/*
 * Moderator class to handle Moderator's data.
 */
public class Mod extends UserPrototype {

	protected ArrayList<String> profQual;
	protected long emergencyContactNo;
	
	//Mod class constructor which utilizes super class constructor.
	public Mod(String uname, String pwd, String[] email, String fname,
			String lname, String add, String about, String[] profilePhoto, long no) {
		super(uname, pwd, email, fname, lname, add, about, profilePhoto);
		
		this.emergencyContactNo=no;
		this.profQual=new ArrayList<String>();
		
//		this.profQual=new ArrayList<Integer>();
//		for(String b:a){
//			setProfQual(b);
//		}
	}
	
	//Getter Function for emergencyContactNo
	public long getEmergencyContactNo(){
		return this.emergencyContactNo;
	}
		
	//Setter for ProfQual
	public void setProfQual(ArrayList<String> profQual) {
		this.profQual = profQual;
	}

	//Getter for ProfQual
	public ArrayList<String> getProfQual(){
		return profQual;
	}
	
	//Getter for ProfQual
	public String getProfQualString(){
		String qualification="";
		int i=0;
		for(String s: profQual){
			qualification+=s;
			i++;
			if(i!=profQual.size())
				qualification+=", ";
		}
		return qualification;
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
		
		System.out.print("Professional Qualification: ");
		int i=1;
		for(String a:this.profQual){
			System.out.print("\n\t"+i+": "+a+".");
			i++;
		}
		System.out.print("\n");
		
		System.out.print("Postal Address: "+this.getPostalAddress()+"\n");
		System.out.print("About Me: "+this.getAboutMe()+"\n");
		System.out.print("Profile Photo URLs: \n\t1: "+this.getProfilePhotoUrls()[0]+"\n\t2: "+this.getProfilePhotoUrls()[1]+"\n\t3: "+this.getProfilePhotoUrls()[2]+"\n\n");
	}
}
