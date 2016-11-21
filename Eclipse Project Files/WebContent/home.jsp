<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="View.*" %>

<%
	//Code to redirect invalid user to homepage
	if(session.getAttribute("User")==null)
		request.getRequestDispatcher("index.jsp").forward(request, response);
%>    
    
<%
	//Code to import suitable sidebar and content file
	UserPrototype user;
	String sidebar_type="",content_type="";
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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><%=user.getName() %>'s Home | Smart Health Portal</title>

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
    
    <style type="text/css">
		.button {
		    display: block;
		    width: auto;
		    float: inherit;
		    height: auto;
		    margin-left:10px;
		    background: #4E9CAF;
		    padding: 10px;
		    text-align: center;
		    border-radius: 5px;
		    color: white;
		    font-weight: bold;
		}
    </style>

</head>

<body>

    <div id="wrapper">

        <!-- Sidebar -->
        	<jsp:include page="<%=sidebar_type %>" />
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <jsp:include page="<%=content_type %>" />
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

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
			
<%
	//Code to Logging out a user
	if(request.getParameter("index")!=null && request.getParameter("index").equals("logout")){
		session.removeAttribute("Type");
		session.removeAttribute("User");
		response.sendRedirect("index.jsp");
	}
%>
	
</body>
</html>