<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="View.*" import="Controller.*"%>
<%
	//Code to import suitable sidebar and content file
	User user;
	String sidebar_type="",content_type="";
	user = (User)session.getAttribute("User");
	sidebar_type="./include/user_home/sidebar.jsp";
	content_type="./include/user_home/content.jsp";
	Friendship friendship = new Friendship();
	
	ArrayList<UsersList> userslist = (ArrayList<UsersList>)session.getAttribute("UsersList");
	ArrayList<String> friendlist = (ArrayList<String>)session.getAttribute("FriendList");
	/*
	switch((Integer)session.getAttribute("Type")) {
		case 1: 
		case 2:
		case 3: user = (User)session.getAttribute("User");
			sidebar_type="./include/user_home/sidebar.jsp";
			content_type="./include/user_home/content.jsp";
			break;
		case 4: user = (Mod)session.getAttribute("User");
			sidebar_type="./include/mod_home/sidebar.jsp";
			content_type="./include/mod_home/content.jsp";
			break;
		case 5: user = (Admin)session.getAttribute("User");
			sidebar_type="./include/admin_home/sidebar.jsp";
			content_type="./include/admin_home/content.jsp";
			break;
		default: user=null;
	}
	*/
%>
<%
    	String index=request.getParameter("index");
    if(request.getParameter("friends")==null && request.getParameter("forum")==null){
    	if((index==null || index.equalsIgnoreCase("home")))
		{
		%>
<div class="row">
	<div class="col-lg-12">
		<h1><%=user.getName() %>'s Smart Health Portal
		</h1>
		<hr />
		<br />
		<h3>Welcome to our portal. Feel free to navigate inorder to use
			our services.</h3>
		<br />
		<br />
		<h3>Have a nice day ahead!!!</h3>
	</div>
</div>
<%
		}else{
			if(index.equalsIgnoreCase("profile"))
    		{
				user = (new Login()).getUserDetails(user.getEmail()[0]);
				session.setAttribute("User",user);
				String[] e=user.getEmail();
				String[] a={"asd","New","Middle","Old"};
    		%>

<div class="row">
	<div class="col-lg-12">
		<h1><%=user.getName() %>'s Profile
		</h1>
		<hr />
	</div>
	</div>

<div class="row">
	<div class="col-lg-6">
		<pre>
Name:					<%=user.getName() %>
Username:				<%=user.getUserName() %>
Usertype:				<%=a[user.getUserType()] %>
Primary Email:				<%=e[0] %>
Alternate Email:			<%=e[0] %>
                        </pre>
	</div>
	<div class="col-lg-6">
		<pre>
Postal Address:				<%=user.getPostalAddress() %>
About Me:				<%=user.getAboutMe() %>
Karma Value:				<%=user.getKarma() %>
Profile Photo URLs:			<%=user.getProfilePhotoUrls()[0] %>, <%=user.getProfilePhotoUrls()[1] %>, <%=user.getProfilePhotoUrls()[2] %>

                        </pre>
	</div>
</div>
<hr />
				<div class="row">
					<div class="col-lg-2"></div>
					<div class="col-lg-8">
						<h3><a href='#' class='button' >Profile Photos</a></h3>
					</div>
					<div class="col-lg-2"></div>
				</div>
				<div class="row">
					<div class="col-lg-4"><img alt="Couldn\'t locate the photo" src="<%=user.getProfilePhotoUrls()[0] %>" style='height:240px; width:320px'></div>
					<div class="col-lg-4"><img alt="Couldn\'t locate the photo" src="<%=user.getProfilePhotoUrls()[1] %>" style='height:240px; width:320px'></div>
					<div class="col-lg-4"><img alt="Couldn\'t locate the photo" src="<%=user.getProfilePhotoUrls()[2] %>" style='height:240px; width:320px'></div>
				</div>
<hr/>
				
				<div class="row">
					<div class="col-lg-4"></div>
					<div class="col-lg-4">
						<h3>Health Data</h3>
					</div>
					<div class="col-lg-4"></div>
				</div>
	    		<%
	    		int i=0;	    	
	    		for(View.HealthData hd : user.getHealth_data()){
		    		i++;
		    		if(i==1){
		    			out.print("<div class='row'><div class=\"col-lg-2\"></div>");
						out.print("<div class=\"col-lg-1\">No.</div>");
						out.print("<div class=\"col-lg-2\">Property Name</div>");
						out.print("<div class=\"col-lg-2\">Value</div>");
						out.print("<div class=\"col-lg-3\">Time Performed</a></div>");
						out.print("<div class=\"col-lg-2\"></div></div>");
						out.print("<div class='row'><div class=\"col-lg-2\"></div><div class='col-lg-8'><hr/></div><div class=\"col-lg-2\"></div></div>");
		    		}
	    			out.print("<div class='row'><div class=\"col-lg-2\"></div>");
					out.print("<div class=\"col-lg-1\">"+i+"</div>");
					out.print("<div class=\"col-lg-2\">"+hd.getProperty()+"</div>");
					out.print("<div class=\"col-lg-2\">"+hd.getValue()+"</div>");
					out.print("<div class=\"col-lg-3\">"+hd.getDate()+"</div>");
					out.print("<div class=\"col-lg-2\"></div></div>");
	    		}
	    		%>
<hr />
<div class="row">
	<div class="col-lg-5"></div>
	<div class="col-lg-2">
		<form action="LoginServlet" method="get">
			<input type="submit" value="Deactivate Profile" name="deactivate" />
		</form>
	</div>
	<div class="col-lg-5"></div>
</div>
<%
		    	}else if(index.equalsIgnoreCase("friends")){
		    		friendlist = friendship.getMyFriendList(request.getSession().getAttribute("Username").toString());
		    		userslist = friendship.getUsersList(request.getSession().getAttribute("Username").toString());
    %>
<div class="row">
	<div class="col-lg-12">
		<h1><%=user.getName() %>'s Friends
		</h1>
		<hr />
	</div>
</div>


<%
						int i=1;
						out.print("<div class='row'><div class=\"col-lg-1\"></div>");
						out.print("<div class=\"col-lg-1\">No.</div>");
						out.print("<div class=\"col-lg-3\">Friend's Username</div>");
						out.print("<div class=\"col-lg-2\">Profile Tab</a></div>");
						out.print("<div class=\"col-lg-2\">Health Data Tab</a></div>");
						out.print("<div class=\"col-lg-2\">UnFriend Tab</a></div><div class=\"col-lg-1\"></div></div>");
						out.print("<div class='row'><div class=\"col-lg-1\"></div><div class='col-lg-10'><hr/></div><div class=\"col-lg-1\"></div></div>");
					
						for(String a: friendlist){
							out.print("<div class='row'><div class=\"col-lg-1\"></div>");
							out.print("<div class=\"col-lg-1\">"+(i++)+"</div>");
							out.print("<div class=\"col-lg-3\">"+a+"</div>");
							out.print("<div class=\"col-lg-2\"><a href='home.jsp?friends=showProfile&uname="+a+"'>Show Profile</a></div>");
							out.print("<div class=\"col-lg-2\"><a href='./HealthDataServlet?type=showHealthData&uname="+a+"'>View Health Data</a></div>");
							out.print("<div class=\"col-lg-2\"><a href='./FriendshipServlet?type=unfriend&uname="+a+"'>UnFriend</a></div>");
							out.print("<div class=\"col-lg-1\"></div></div>");
						}
						if(i==1)
							out.println("<script>window.location.assign('home.jsp?error=2')</script>");
							
	    		}else if(index.equalsIgnoreCase("request")){
	    			ArrayList<String> requestlist = friendship.getPendingRequestsOnMyEnd(request.getSession().getAttribute("Username").toString());
	    			ArrayList<UsersList> pendinglist = friendship.getYetToBeAnsweredRequests(request.getSession().getAttribute("Username").toString());
        %>
<div class="row">
	<div class="col-lg-12">
		<h1><%=user.getName() %>'s Friend Requests
		</h1>
		<hr />
	</div>
</div>


<%
    						int i=1;    						    					
    						for(String a: requestlist){
    							if(i==1){
    								out.print("<div class='row'><div class=\"col-lg-2\"></div>");
    	    						out.print("<div class=\"col-lg-1\">No.</div>");
    	    						out.print("<div class=\"col-lg-3\">Requester's Username</div>");
    	    						out.print("<div class=\"col-lg-2\">Accept Request Tab</a></div>");
    	    						out.print("<div class=\"col-lg-2\">Reject Request Tab</a></div>");
    	    						out.print("<div class=\"col-lg-2\"></div></div>");
    	    						out.print("<div class='row'><div class=\"col-lg-2\"></div><div class='col-lg-8'><hr/></div><div class=\"col-lg-2\"></div></div>");
    							}
    							out.print("<div class='row'><div class=\"col-lg-2\"></div>");
    							out.print("<div class=\"col-lg-1\">"+(i++)+"</div>");
    							out.print("<div class=\"col-lg-3\">"+a+"</div>");
    							out.print("<div class=\"col-lg-2\"><a href='./FriendshipServlet?type=acceptRequest&uname="+a+"'>Accept Request</a></div>");
    							out.print("<div class=\"col-lg-2\"><a href='./FriendshipServlet?type=rejectRequest&uname="+a+"'>Reject Request</a></div>");
    							out.print("<div class=\"col-lg-2\"></div></div>");
    						}
    						if(i==1)
    							out.println("<div class=\"col-lg-4\"></div><div class=\"col-lg-6\"><i>No pending friend requests found.</i></div><div class=\"col-lg-4\"></div>");
				%>
<br />
<br />
<hr />
<div class="row">
	<div class="col-lg-12">
		<h1><%=user.getName() %>'s Sent Requests Status
		</h1>
		<hr />
	</div>
</div>
<%
				    			i=1;    						    					
								for(UsersList a: pendinglist){
									if(i==1){
										out.print("<div class='row'><div class=\"col-lg-3\"></div>");
										out.print("<div class=\"col-lg-1\">No.</div>");
										out.print("<div class=\"col-lg-2\">Username</div>");
										out.print("<div class=\"col-lg-3\">Request Status</a></div>");
										out.print("<div class=\"col-lg-3\"></div></div>");
										out.print("<div class='row'><div class=\"col-lg-3\"></div><div class='col-lg-6'><hr/></div><div class=\"col-lg-3\"></div></div>");
									}
									out.print("<div class='row'><div class=\"col-lg-3\"></div>");
									out.print("<div class=\"col-lg-1\">"+(i++)+"</div>");
									out.print("<div class=\"col-lg-2\">"+a.getUsername()+"</div>");
									out.print("<div class=\"col-lg-3\">"+a.getStatus()+"</div>");
									out.print("<div class=\"col-lg-3\"></div></div>");
								}
								if(i==1)
									out.println("<div class=\"col-lg-4\"></div><div class=\"col-lg-6\"><i>No pending sent friend requests found.</i></div><div class=\"col-lg-4\"></div>");

    	}else if(index.equalsIgnoreCase("sendRequest")){
    %>
<script type="text/javascript">
var uname = prompt("Enter the username to send the friend request?", "Username");
document.location.href='./FriendshipServlet?type=sendRequest&uname='+uname;
</script>
<%
		}else if(index.equalsIgnoreCase("addhealth")){
			%>
			<div class="row">
				<div class="col-lg-12">
					<h1>Add Your HealthData</h1>
					<hr />
				</div>
			</div>
			
			<form action="./HealthDataServlet" method="post">
				<div class="row">
					<div class="col-lg-4"></div>
					<div class="col-lg-2">Health Property:</div>
					<div class="col-lg-2">
						<select name="property" required="required">
						  <option value="">Select a Health Property</option>
						  <option value="1">Distance Covered in KMs</option>
						  <option value="2">Calories Burned</option>
						  <option value="3">Blood Pressure</option>
						</select>
					</div>
					<div class="col-lg-4"></div>
				</div>
				<div class="row">
					<div class="col-lg-12"><br/></div>
				</div>
				<div class="row">
					<div class="col-lg-4"></div>
					<div class="col-lg-2">Property Value:</div>
					<div class="col-lg-2"><input type='text' placeholder="Enter it's value" required="required" name='value'></div>
					<div class="col-lg-4"></div>
				</div>
				<div class="row">
					<div class="col-lg-12"><br/><br/></div>
				</div>
				<div class="row">
					<div class="col-lg-5"></div>
					<div class="col-lg-2"><input type='submit' name='addHealthData' value="Add Data"></div>
					<div class="col-lg-5"></div>
				</div>
			</form>
			<%
		}else if(index.equalsIgnoreCase("forums")){
			DiscussionForum controller = new DiscussionForum();
			ArrayList<Forum> forums_list=controller.getActiveForumsList();;
			if(forums_list==null)
				out.println("<script>window.location=\"home.jsp?index=home&error=13\"</script>");
			else{
			%>
			<div class="row">
				<div class="col-lg-12">
					<h1>Smart Health Forums</h1>
					<hr />
				</div>
			</div>
			<%
			
			int i=1;    						    					
			for(Forum f : forums_list){
				if(i==1){
					out.print("<div class='row'><div class=\"col-lg-1\"></div>");
					out.print("<div class=\"col-lg-2\">Forum ID</div>");
					out.print("<div class=\"col-lg-3\">Forum Topic</div>");
					out.print("<div class=\"col-lg-3\">Created By</a></div>");
					out.print("<div class=\"col-lg-2\">Action</div><div class=\"col-lg-1\"></div></div>");
					out.print("<div class='row'><div class=\"col-lg-2\"></div><div class='col-lg-9'><hr/></div><div class=\"col-lg-1\"></div></div>");
				}
				out.print("<div class='row'><div class=\"col-lg-1\"></div>");
				out.print("<div class=\"col-lg-2\">"+f.getForum_id()+"</div>");
				out.print("<div class=\"col-lg-3\">"+f.getTopic()+"</div>");
				out.print("<div class=\"col-lg-3\">"+f.getCreated_by()+"</a></div>");
				out.print("<div class=\"col-lg-2\"><a href=\"./DiscussionForumServlet?type=showforum&id="+f.getForum_id()+"\">Show Forum</a></div><div class=\"col-lg-1\"></div></div>");
				i++;
			}
			if(i==1)
				out.println("<div class=\"col-lg-4\"></div><div class=\"col-lg-6\"><i>No active forums found.</i></div><div class=\"col-lg-4\"></div>");
			}
		}//closing braces of else
    }//closing braces of main if
   }
			
		if(request.getParameter("friends")!=null){
			if(request.getParameter("friends").equalsIgnoreCase("showProfile") && request.getParameter("uname")!=null){
				Login control = new Login();
				User u = control.getUserDetails(control.getEmailAddress(request.getParameter("uname")));
				String[] e=u.getEmail();
				String[] a={"asd","New","Middle","Old"};
	    		%>

<div class="row">
	<div class="col-lg-12">
		<h1><%=u.getName() %>'s Profile
		</h1>
		<hr />
	</div>
</div>

<div class="row">
	<div class="col-lg-6">
		<pre>
Name:					<%=u.getName() %>
Username:				<%=u.getUserName() %>
Usertype:				<%=a[u.getUserType()] %>
Primary Email:				<%=e[0] %>
Alternate Email:			<%=e[0] %>
	                        </pre>
	</div>
	<div class="col-lg-6">
		<pre>
Postal Address:				<%=u.getPostalAddress() %>
About Me:				<%=u.getAboutMe() %>
Karma Value:				<%=u.getKarma() %>
Profile Photo URLs:			<%=u.getProfilePhotoUrls()[0] %>, <%=u.getProfilePhotoUrls()[1] %>, <%=u.getProfilePhotoUrls()[2] %>

	                        </pre>
	</div>
</div>
<hr />
<div class="row">
	<div class="col-lg-5"></div>
	<div class="col-lg-2">
		<input type="button" value="Back"
			onclick="window.location='?index=friends'" />
	</div>
	<div class="col-lg-5"></div>
</div>
<%
	    	}else if(request.getParameter("friends").equalsIgnoreCase("healthdata")){
	    		ArrayList<View.HealthData> list = (ArrayList<View.HealthData>)request.getSession().getAttribute("HealthDataList");
	    		int i=0;
	    		%>
				<hr />
				<div class="row">
					<div class="col-lg-12">
						<h1><%=request.getParameter("uname")%>'s Health Data</h1>
						<hr />
					</div>
				</div>
	    		<%
	    		for(View.HealthData hd : list){
		    		i++;
		    		if(i==1){
		    			out.print("<div class='row'><div class=\"col-lg-2\"></div>");
						out.print("<div class=\"col-lg-1\">No.</div>");
						out.print("<div class=\"col-lg-2\">Property Name</div>");
						out.print("<div class=\"col-lg-2\">Value</div>");
						out.print("<div class=\"col-lg-3\">Time Performed</a></div>");
						out.print("<div class=\"col-lg-2\"></div></div>");
						out.print("<div class='row'><div class=\"col-lg-2\"></div><div class='col-lg-8'><hr/></div><div class=\"col-lg-2\"></div></div>");
		    		}
	    			out.print("<div class='row'><div class=\"col-lg-2\"></div>");
					out.print("<div class=\"col-lg-1\">"+i+"</div>");
					out.print("<div class=\"col-lg-2\">"+hd.getProperty()+"</div>");
					out.print("<div class=\"col-lg-2\">"+hd.getValue()+"</div>");
					out.print("<div class=\"col-lg-3\">"+hd.getDate()+"</div>");
					out.print("<div class=\"col-lg-2\"></div></div>");
	    		}
	    	}
		}
		
		if(request.getParameter("forum")!=null){
			String type = request.getParameter("forum");
			int forum_id = Integer.valueOf(request.getParameter("id"));
			Forum forum = (Forum)request.getSession().getAttribute("Forum");
			
			if(type.equalsIgnoreCase("showpost")){
				ArrayList<Posts> posts_list = (ArrayList<Posts>)request.getSession().getAttribute("Posts");
				out.println("<hr/>");
				out.println("<h1>ID: "+forum.getForum_id()+" | Forum's Topic: "+forum.getTopic()+"</h1>");
				out.println("<b>Summary: "+forum.getSummary()+"</b><br/><br/>");
				out.println("<i style='float:right'>Date Created: "+forum.getWhen_created()+" | Created By: "+forum.getCreated_by()+"</i><br/><br/>");
				out.println("<div class='row'><div class=\"col-lg-8\"><form action='./DiscussionForumServlet' method='get'><textarea required name='text' cols='80' rows='5' placeholder=\"Enter the post content here.\"></textarea></div><div class=\"col-lg-4\"> &nbsp; &nbsp; &nbsp;<input type='hidden' name='id' value='"+forum_id+"'/><input type='submit' value='Create Post' name='type' class='button'/></form></div></div>");
				out.println("<hr/>");
				out.println("<hr/>");
				
				if(posts_list == null){
					out.println("<br/><br/><span style='float:right'>No Posts found on this forum.</span><br/>");
					return;
				}
				int i=1;
				for(Posts p:posts_list){
					
					out.println("<h3>Post No.: "+i+" | Ratings: "+p.getRating()+"</h3>");
					out.println("<b>Post Content: "+p.getText_entry()+"</b><br/>");
					out.println("<i style='float:left'><form action=\"./DiscussionForumServlet\" method='get'><input type='hidden' name='id' value='"+forum_id+"'/><input type='hidden' name='timecreated' value='"+p.getTime_created()+"'/><input type='hidden' name='username' value='"+p.getUsername()+"'/><input type='number' max='5' min='1' name='rating' value='4' required/><input type='submit' value='Rate Post' name='type'/> </form></i><i style='float:right'>Date Created: "+p.getTime_created()+" | Created By: "+p.getUsername()+"</i><br/><br/>");
					
					
					int j=0;
					ArrayList<Comments> comments_list = p.getComments_list();
					for(Comments c: comments_list){
						out.print("<div class='row'><div class=\"col-lg-4\"></div>");
						out.print("<div class=\"col-lg-2\"><b>Comment "+(++j)+":</b></div>");
						out.print("<div class=\"col-lg-6\">"+c.getText_entry()+"</div>");
						out.print("</div>");
						
						out.print("<div class='row'><div class=\"col-lg-4\"></div>");
						out.print("<div class=\"col-lg-4\"><i>Time: "+c.getTime_created()+"</i></div>");
						out.print("<div class=\"col-lg-4\"><i>Commented By: "+c.getUsername()+"</i></div>");
						out.print("</div>");
						
						out.print("<div class='row'><div class=\"col-lg-4\"></div>");
						out.print("<div class=\"col-lg-8\"><hr/></div>");
						out.print("</div>");
						
						if(j==comments_list.size()){
							out.print("<div class='row'><div class=\"col-lg-4\"></div>");
							out.print("<div class=\"col-lg-8\">");
							out.println("<div class='row'><div class=\"col-lg-8\"><form action='./DiscussionForumServlet' method='get'><textarea required name='text' cols='50' rows='3' placeholder=\"Enter the comment here.\"></textarea></div><div class=\"col-lg-4\"> &nbsp; &nbsp; &nbsp;<input type='hidden' name='id' value='"+forum_id+"'/><input type='hidden' name='timecreated' value='"+p.getTime_created()+"'/><input type='hidden' name='username' value='"+p.getUsername()+"'/><input type='submit' value='Comment' name='type' class='button'/></form></div></div>");
							out.print("</div></div>");
						}
							
					}
					if(j==0){
						out.println("<br/><span style='float:right'>No Comments on this post.</span><br/>");
						out.print("<div class='row'><div class=\"col-lg-4\"></div>");
						out.print("<div class=\"col-lg-8\"><hr/></div>");
						out.print("</div>");
						if(j==comments_list.size()){
							out.print("<div class='row'><div class=\"col-lg-4\"></div>");
							out.print("<div class=\"col-lg-8\">");
							out.println("<div class='row'><div class=\"col-lg-8\"><form action='./DiscussionForumServlet' method='get'><textarea required name='text' cols='50' rows='3' placeholder=\"Enter the comment here.\"></textarea></div><div class=\"col-lg-4\"> &nbsp; &nbsp; &nbsp;<input type='hidden' name='id' value='"+forum_id+"'/><input type='hidden' name='timecreated' value='"+p.getTime_created()+"'/><input type='hidden' name='username' value='"+p.getUsername()+"'/><input type='submit' value='Comment' name='type' class='button'/></form></div></div>");
							out.print("</div></div>");
						}
					}
					out.println("<hr/>");
					i++;
				}
				if(i==0){
					out.println("<br/><br/><span style='float:right'>No Posts found on this forum.</span><br/>");
				}
			}
		}
    
		String error_no_string = request.getParameter("error");
   		String success_string = request.getParameter("success");
   		if(error_no_string!=null){
   			int error_no = Integer.valueOf(error_no_string);
   	   		if(error_no==1)
   	   			out.println("<script>alert(\"Problem while removing your friend. Do try after some time.\")</script>");
   	   		else if(error_no==2)
   	   	   		out.println("<script>alert(\"No friends found in your list. Use search to look for a friend.\")</script>");
   	   		else if(error_no==3)
	   	   		out.println("<script>alert(\"Cannot accept the friend request at the moment. Try again later.\")</script>");
   	   		else if(error_no==4)
   	   			out.println("<script>alert(\"Cannot withdraw your friend request at the moment. Try again later.\")</script>");
   	   		else if(error_no==5)
	   			out.println("<script>alert(\"No user found. Try again with some other query.\")</script>");
   	   		else if(error_no==6)
   	   			out.println("<script>alert(\"Cannot reject your friend request at the moment. Try again later.\")</script>");
   	   		else if(error_no==7)
	   			out.println("<script>alert(\"No healthdata found. The friend might not have any health data associated with him.\")</script>");
   	   		else if(error_no==8)
   				out.println("<script>alert(\"Couldn't add your healthdata. Please check your inputs and try again later.\")</script>");
   	   		else if(error_no==9)
				out.println("<script>alert(\"No posts exist in this post.\")</script>");
   	   		else if(error_no==10)
				out.println("<script>alert(\"Couldn't add your new post. Please try again later.\")</script>");
   	   		else if(error_no==11)
				out.println("<script>alert(\"Couldn't add your new comment. Please try again later.\")</script>");
   	   		else if(error_no==12)
				out.println("<script>alert(\"Couldn't rate your post at the moment. You might already have rated this post. Please try again later.\")</script>");
   	   		else if(error_no==13)
   	   			out.println("<script>alert(\"No Active Forums found in the database. Please try again after some time.\")</script>");
   		}
   		if(success_string!=null){
   			int success_no = Integer.valueOf(success_string);
   	   		if(success_no==1)
   	   			out.println("<script>alert(\"Unfriended successfully.\")</script>");
   	   		else if(success_no==2)
	   			out.println("<script>alert(\"Friend Request accepted successfully.\")</script>");
   	   		else if(success_no==3)
   				out.println("<script>alert(\"Friend Request withdrawn successfully.\")</script>");
   	   		else if(success_no==4)
				out.println("<script>alert(\"Friend Request rejected successfully.\")</script>");
   	   		else if(success_no==5)
				out.println("<script>alert(\"Health Data have been added successfully.\")</script>");
   	   		else if(success_no==6)
				out.println("<script>alert(\"Post has been successfully created.\")</script>");
   	   		else if(success_no==7)
				out.println("<script>alert(\"Comment has been successfully created.\")</script>");
   	   		else if(success_no==8)
				out.println("<script>alert(\"Post has been rated successfully.\")</script>");
   		}
%>