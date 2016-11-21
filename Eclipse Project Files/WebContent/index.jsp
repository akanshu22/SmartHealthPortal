<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Smart Health Portal</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Sidebar -->
        	<jsp:include page="./include/index/sidebar_main.jsp" />
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <% 
                    	String type = request.getParameter("index");
                    	if(type==null || type.equals("home"))
                    		type="./include/index/home.jsp";
                    	else if(type.equals("login"))
                    		type="./include/index/login.jsp";
                    	else if(type.equals("register"))
                    		type="./include/index/register.jsp";
                    	else if(type.equals("about"))
                    		type="./include/index/about.jsp";
                    	else if(type.equals("contact"))
                    		type="./include/index/contact.jsp";
                    %>
                    <jsp:include page="<%=type %>" />
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->
    
   	<%
   		String error_no_string = request.getParameter("error");
   		String success_string = request.getParameter("success");
   		if(error_no_string!=null){
   			int error_no = Integer.valueOf(error_no_string);
   	   		if(error_no==1)
   	   			out.println("<script>alert(\"Couldn't log you in successfully. Please re-verify your username and password and try again later.\")</script>");
   	   		else if(error_no==2)
   	   	   		out.println("<script>alert(\"Couldn't deactivate your profile. Please try again.\")</script>");
   	   		else if(error_no==3)
	   	   		out.println("<script>alert(\"Couldn't reactivate your profile. Please try again.\")</script>");
   		}
   		if(success_string!=null){
   			int success_no = Integer.valueOf(success_string);
   	   		if(success_no==1)
   	   			out.println("<script>alert(\"Deactivated your profile successfully. Try logging in to reactivate your profile.\")</script>");
   	   		else if(success_no==2)
	   			out.println("<script>alert(\"Reactivated your profile successfully. Try logging in to reactivate your profile.\")</script>");
   	   		else if(success_no==3)
   	   			out.println("<script>alert(\"User has been successfully registered. Thanks!\")</script>");
   		}
   		
   	%>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>

</body>

</html>
