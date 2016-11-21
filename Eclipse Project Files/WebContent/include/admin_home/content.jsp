<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="View.*" import="Controller.*" import="java.util.ArrayList"%>					
<%
	Admin user = (Admin)session.getAttribute("User");
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
		<h3>Welcome to Administrator's portal. Feel free to navigate inorder to use
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
Usertype:				<%=a[5] %>
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
		}//closing braces of else
    }//closing braces of main if
   }
%>