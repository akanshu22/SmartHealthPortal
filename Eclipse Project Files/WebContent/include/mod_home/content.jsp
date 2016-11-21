<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="View.*" import="Controller.*" import="java.util.ArrayList"%>					
<%
	Mod user = (Mod)session.getAttribute("User");
    String index=request.getParameter("index");
    if(request.getParameter("forum")==null){
    	if((index==null || index.equalsIgnoreCase("home")))
		{
		%>
<div class="row">
	<div class="col-lg-12">
		<h1><%=user.getName() %>'s Smart Health Portal
		</h1>
		<hr />
		<br />
		<h3>Welcome to Moderator's portal. Feel free to navigate inorder to use
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
				String[] e=user.getEmail();
				String[] a={"asd","New","Middle","Old","Moderator","Administrator"};
    		%>

<div class="row">
	<div class="col-lg-12">
		<h1><%=user.getName() %>'s Profile</h1>
		<hr />
	</div>
	</div>

<div class="row">
	<div class="col-lg-6">
		<pre>
Name:					<%=user.getName() %>
Username:				<%=user.getUserName() %>
Usertype:				<%=a[4] %>
Primary Email:				<%=e[0] %>
Alternate Email:			<%=e[0] %>
                        </pre>
	</div>
	<div class="col-lg-6">
		<pre>
Postal Address:				<%=user.getPostalAddress() %>
About Me:				<%=user.getAboutMe() %>
Emergency Contact No.:			<%=user.getEmergencyContactNo() %>
Profile Photo URLs:			<%=user.getProfilePhotoUrls()[0] %>, <%=user.getProfilePhotoUrls()[1] %>, <%=user.getProfilePhotoUrls()[2] %>
Moderator's Qualification:		<%=user.getProfQualString() %>

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
	<div class="col-lg-5"></div>
	<div class="col-lg-2">
		<form action="LoginServlet" method="get">
			<input type="submit" value="Deactivate Profile" name="deactivate" />
		</form>
	</div>
	<div class="col-lg-5"></div>
</div>
<%
		}else if(index.equalsIgnoreCase("forums-active")){
			DiscussionForum controller = new DiscussionForum();
			ArrayList<Forum> forums_list=controller.getActiveForumsList();
			if(forums_list==null)
				out.println("<script>window.location=\"home.jsp?index=home&error=3\"</script>");
			else{
			%>
			<div class="row">
				<div class="col-lg-12">
					<h1>Smart Health Active Discussion Forums</h1>
					<hr />
				</div>
			</div>
			<%
			
			int i=1;    						    					
			for(Forum f : forums_list){
				if(i==1){
					out.print("<div class='row'><div class=\"col-lg-1\"></div>");
					out.print("<div class=\"col-lg-2\">Forum ID</div>");
					out.print("<div class=\"col-lg-2\">Forum Topic</div>");
					out.print("<div class=\"col-lg-2\">Created By</a></div>");
					out.print("<div class=\"col-lg-4\">Action</div><div class=\"col-lg-1\"></div></div>");
					out.print("<div class='row'><div class=\"col-lg-1\"></div><div class='col-lg-10'><hr/></div><div class=\"col-lg-1\"></div></div>");
				}
				out.print("<div class='row'><div class=\"col-lg-1\"></div>");
				out.print("<div class=\"col-lg-2\">"+f.getForum_id()+"</div>");
				out.print("<div class=\"col-lg-2\">"+f.getTopic()+"</div>");
				out.print("<div class=\"col-lg-2\">"+f.getCreated_by()+"</a></div>");
				out.print("<div class=\"col-lg-4\"><a href=\"./DiscussionForumServlet?type=showforum&id="+f.getForum_id()+"\">Show Forum</a> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<a href=\"./DiscussionForumServlet?type=deleteforum&id="+f.getForum_id()+"\">Delete Forum</a></div><div class=\"col-lg-1\"></div></div>");
				i++;
			}
			if(i==1)
				out.println("<div class=\"col-lg-4\"></div><div class=\"col-lg-6\"><i>No active forums found.</i></div><div class=\"col-lg-4\"></div>");
			}
		}else if(index.equalsIgnoreCase("forums-delete")){
			DiscussionForum controller = new DiscussionForum();
			ArrayList<Forum> forums_list=controller.getDeletedForumsList();
			if(forums_list==null)
				out.println("<script>window.location=\"home.jsp?index=home&error=4\"</script>");
			else{
			%>
			<div class="row">
				<div class="col-lg-12">
					<h1>Smart Health Deleted Discussion Forums</h1>
					<hr />
				</div>
			</div>
			<%
			
			int i=1;    						    					
			for(Forum f : forums_list){
				if(i==1){
					out.print("<div class='row'><div class=\"col-lg-1\"></div>");
					out.print("<div class=\"col-lg-2\">Forum ID</div>");
					out.print("<div class=\"col-lg-4\">Forum Topic</div>");
					out.print("<div class=\"col-lg-2\">Created By</a></div>");
					out.print("<div class=\"col-lg-2\">Deleted By</div><div class=\"col-lg-1\"></div></div>");
					out.print("<div class='row'><div class=\"col-lg-1\"></div><div class='col-lg-10'><hr/></div><div class=\"col-lg-1\"></div></div>");
				}
				out.print("<div class='row'><div class=\"col-lg-1\"></div>");
				out.print("<div class=\"col-lg-2\">"+f.getForum_id()+"</div>");
				out.print("<div class=\"col-lg-4\">"+f.getTopic()+"</div>");
				out.print("<div class=\"col-lg-2\">"+f.getCreated_by()+"</div>");
				out.print("<div class=\"col-lg-2\">"+f.getDeleted_by()+"</div><div class=\"col-lg-1\"></div></div>");
				i++;
			}
			if(i==1)
				out.println("<div class=\"col-lg-4\"></div><div class=\"col-lg-6\"><i>No deleted forums found.</i></div><div class=\"col-lg-4\"></div>");
			}
		}else if(index.equalsIgnoreCase("forums-new")){

			%>
			<div class="row">
				<div class="col-lg-12">
					<h1>Create a new Discussion Forums</h1>
					<hr />
					<br/>
				</div>
			</div>
			<form action="./DiscussionForumServlet" method="get">
			<div class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-4">Enter Forum's Topic:</div>
				<div class="col-lg-4"><input type='text' name='name' placeholder='Sample_Forum_Name' required/></div>
				<div class="col-lg-2"><br/><br/></div>
			</div>
			<div class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-4">Enter Summary for the Forum's Topic:</div>
				<div class="col-lg-4"><input type='hidden' name='id' value="5"/><textarea name='summary' placeholder='Sample forum summary' required cols='40' rows='4'></textarea></div>
				<div class="col-lg-2"><br/><br/><br/><br/></div>
			</div>
			<div class="row">
				<div class="col-lg-3"></div>
				<div class="col-lg-3"><input type='reset' value='Reset'/></div>
				<div class="col-lg-3"><input type='submit' name='type' value='Create Forum'/></div>
				<div class="col-lg-3"></div>
			</div>
			</form>
			<%
		}//closing braces of else
    }//closing braces of main if
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
				out.println("<i style='float:right'>Date Created: "+forum.getWhen_created()+" | Created By: "+forum.getCreated_by()+"</i><br/>");
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
					out.println("<i style='float:right'>Date Created: "+p.getTime_created()+" | Created By: "+p.getUsername()+"</i><br/><br/>");
					
					
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
					}
					if(j==0){
						out.println("<br/><span style='float:right'>No Comments on this post.</span><br/>");
						out.print("<div class='row'><div class=\"col-lg-4\"></div>");
						out.print("<div class=\"col-lg-8\"><hr/></div>");
						out.print("</div>");
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
				out.println("<script>alert(\"Problem encountered while deleting forum. Please try again after some time.\")</script>");
			else if(error_no==2)
				out.println("<script>alert(\"Problem encountered while creating a forum. Please try again after some time.\")</script>");
			else if(error_no==3)
				out.println("<script>alert(\"No Active Forums found in the database. Please try again after some time.\")</script>");
			else if(error_no==4)
				out.println("<script>alert(\"No Deleted Forums found in the database. Please try again after some time.\")</script>");
   		}
   		if(success_string!=null){
   			int success_no = Integer.valueOf(success_string);
   	   		if(success_no==1)
				out.println("<script>alert(\"Forum deactivated successfully.\")</script>");
   	   		else if(success_no==2)
				out.println("<script>alert(\"Forum created successfully.\")</script>");
   		}
%>