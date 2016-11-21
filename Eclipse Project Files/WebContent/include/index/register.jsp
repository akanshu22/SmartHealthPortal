<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<h1>Register with Us</h1>
<div class="col-lg-12">
	<hr />
	<%
		if (request.getParameter("usertype") == null) {
			int i;
	%>
	<div class="col-lg-12">
		<h4>Select a user type:</h4>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<h1></h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-3">
			<input type='button' value="Normal User" name="typeUser"
				onclick="window.location.href='?index=register&usertype=user'" />
		</div>
		<div class="col-lg-3">
			<input type='button' value="Moderator" name="typeMod"
				onclick="window.location.href='?index=register&usertype=mod'" />
		</div>
		<div class="col-lg-3">
			<input type='button' value="Administrator" name="typeAdmin"
				onclick="window.location.href='?index=register&usertype=admin'" />
		</div>
		<div class="col-lg-1"></div>
	</div>

	<%
		}else{
			int i;
	%>
	<form action="RegistrationServlet" method="POST">
		<input type="hidden" name="type"
			value="<%=(String)request.getParameter("usertype") %>" />
		<div class="row">
			<div class="col-lg-3">
				<b>Username:</b>
			</div>
			<div class="col-lg-3">
				<input type='text' name='username' placeholder="Enter your Username"
					required="required" />
			</div>
			<div class="col-lg-3">
				<b>Password:</b>
			</div>
			<div class="col-lg-3">
				<input type='password' name='passwd'
					placeholder="Enter your Password" required="required" />
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1></h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3">
				<b>First Name:</b>
			</div>
			<div class="col-lg-3">
				<input type='text' name='firstname'
					placeholder="Enter your Firstname" required="required" />
			</div>
			<div class="col-lg-3">
				<b>Last Name:</b>
			</div>
			<div class="col-lg-3">
				<input type='text' name='lastname' placeholder="Enter your Lastname"
					required="required" />
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1></h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3">
				<b>Primary Email Address:</b>
			</div>
			<div class="col-lg-3">
				<input type='email' name='primaryemail'
					placeholder="Enter your Email" required="required" />
			</div>
			<div class="col-lg-3">
				<b>Alternate Email Address:</b>
			</div>
			<div class="col-lg-3">
				<input type='email' name='alternateemail'
					placeholder="Enter your Alternate Email" required="required" />
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1></h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3">
				<b>About Me:</b>
			</div>
			<div class="col-lg-3">
				<input type='text' name='aboutme'
					placeholder="Tell something about yourself" required="required" />
			</div>
			<div class="col-lg-3">
				<b>Postal Address:</b>
			</div>
			<div class="col-lg-3">
				<input type='textarea' name='postaladdress'
					placeholder="Enter your postel address" required="required" />
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1></h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3">
				<b>Profile Pic URL 1:</b>
			</div>
			<div class="col-lg-3">
				<input type='url' name='imageurl1'
					placeholder="Link for your Profile Picture" required="required" />
			</div>
			<div class="col-lg-3">
				<b>Profile Pic URL 2:</b>
			</div>
			<div class="col-lg-3">
				<input type='url' name='imageurl2'
					placeholder="Link for your Profile Picture" required="required" />
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1></h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3">
				<b>Profile Pic URL 3:</b>
			</div>
			<div class="col-lg-3">
				<input type='url' name='imageurl3'
					placeholder="Link for your Profile Picture" required="required" />
			</div>
			<%
				String usertype = (String) request.getParameter("usertype");
				if(usertype.equalsIgnoreCase("mod") || usertype.equalsIgnoreCase("admin")){
					out.println("<div class='col-lg-3'><b>Emergency Contact Number:</b></div>");
					out.println("<div class='col-lg-3'><input type='number' name='emnumber' placeholder='Enter your number'/></div></div>");
				}
				out.println("<div class='row'><div class='col-lg-12'><h1></h1></div></div>");
				
				if(usertype.equalsIgnoreCase("mod")){
					out.println("<div class='row'>");
					out.println("<div class='col-lg-3'><b>Enter Qualifications:<b></div>");
					out.println("<div class='col-lg-3'><input type='text' placeholder='Comma-seperated Enteries' name='qualification' /></div>");
					out.println("<div class='col-lg-3'></div>");
					out.println("<div class='col-lg-3'></div>");
				}
			%>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1></h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-3">
				<input type='reset' value="Reset" />
			</div>
			<div class="col-lg-3">
				<input type='submit' name="register" value="Register Me" />
			</div>
			<div class="col-lg-3"></div>
		</div>
	</form>
	<% }
	
		String error_no_string = request.getParameter("err");
		if(error_no_string!=null){
			int error_no = Integer.valueOf(error_no_string);
	   		if(error_no==1)
	   			out.println("<script>alert(\"Username already exists in out database. Please try with some unique username.\")</script>");
	   		else if(error_no==2)
	   	   		out.println("<script>alert(\"Please enter some unique primary and alternate email address.\")</script>");
	   		else if(error_no==3)
   	   		out.println("<script>alert(\"Couldn't reactivate your profile. Please try again.\")</script>");
		}
	
	%>
</div>