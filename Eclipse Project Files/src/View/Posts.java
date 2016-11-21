/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package View;

import java.util.ArrayList;

public class Posts {

	private String username,time_created;
	private String text_entry,photo_location,link_location,video_location;
	private ArrayList<Comments> comments_list;
	private float rating;
	
	public Posts(String uname, String time_created, String text, float rate) {
		photo_location="";
		link_location="";
		video_location="";
		this.username=uname;
		this.time_created=time_created;
		comments_list = new ArrayList<Comments>();
		this.rating=rate;
		this.text_entry=text;
	}

	/**
	 * @return the comments_list
	 */
	public ArrayList<Comments> getComments_list() {
		return comments_list;
	}

	/**
	 * @param comments_list the comments_list to set
	 */
	public void setComments_list(ArrayList<Comments> comments_list) {
		this.comments_list = comments_list;
	}

	/**
	 * @return the rating
	 */
	public float getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(float rating) {
		this.rating = rating;
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
