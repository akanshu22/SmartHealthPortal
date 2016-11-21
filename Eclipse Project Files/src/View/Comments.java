/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package View;

public class Comments {

	private String username,time_created;
	private String text_entry,photo_location,link_location,video_location;
	
	public Comments(String uname, String time_created, String text) {
		photo_location="";
		link_location="";
		video_location="";
		this.username=uname;
		this.time_created=time_created;
		this.text_entry=text;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the time_created
	 */
	public String getTime_created() {
		return time_created;
	}

	/**
	 * @return the text_entry
	 */
	public String getText_entry() {
		return text_entry;
	}

	/**
	 * @return the photo_location
	 */
	public String getPhoto_location() {
		return photo_location;
	}

	/**
	 * @return the link_location
	 */
	public String getLink_location() {
		return link_location;
	}

	/**
	 * @return the video_location
	 */
	public String getVideo_location() {
		return video_location;
	}

}
