/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package View;

import java.util.ArrayList;

public class Forum {

	private int forum_id;
	private String topic,url,summary,created_by,deleted_by;
	private String when_created,when_closed;
	private ArrayList<Posts> posts_list;
	
	public Forum(int fid, String topic, String url, String summary, String createdBy, String whenCreated) {
		this.forum_id=fid;
		this.topic=topic;
		this.url=url;
		this.summary=summary;
		this.created_by=createdBy;
		this.when_created=whenCreated;
		
		this.deleted_by=null;
		this.when_closed=null;
		posts_list=new ArrayList<Posts>();
	}

	/**
	 * @return the posts_list
	 */
	public ArrayList<Posts> getPosts_list() {
		return posts_list;
	}

	/**
	 * @param posts_list the posts_list to set
	 */
	public void setPosts_list(ArrayList<Posts> posts_list) {
		this.posts_list = posts_list;
	}

	/**
	 * @return the deleted_by
	 */
	public String getDeleted_by() {
		return deleted_by;
	}

	/**
	 * @param deleted_by the deleted_by to set
	 */
	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	/**
	 * @return the when_closed
	 */
	public String getWhen_closed() {
		return when_closed;
	}

	/**
	 * @param when_closed the when_closed to set
	 */
	public void setWhen_closed(String when_closed) {
		this.when_closed = when_closed;
	}

	/**
	 * @return the forum_id
	 */
	public int getForum_id() {
		return forum_id;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @return the created_by
	 */
	public String getCreated_by() {
		return created_by;
	}

	/**
	 * @return the when_created
	 */
	public String getWhen_created() {
		return when_created;
	}
}
