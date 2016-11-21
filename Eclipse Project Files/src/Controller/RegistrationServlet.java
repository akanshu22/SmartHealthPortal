package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Register control;
	String redirect="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		if(request.getParameter("register")!=null){
			String username = (String)request.getParameter("username").toLowerCase();
			String usertype = (String)request.getParameter("type");
			String password = (String)request.getParameter("passwd");
			String email1 = (String)request.getParameter("primaryemail");
			String email2 = (String)request.getParameter("alternateemail");
			String fname = (String)request.getParameter("firstname");
			String lname = (String)request.getParameter("lastname");
			String aboutme = (String)request.getParameter("aboutme");
			String address = (String)request.getParameter("postaladdress");
			String url1 = (String)request.getParameter("imageurl1");
			String url2 = (String)request.getParameter("imageurl2");
			String url3 = (String)request.getParameter("imageurl3");
			long phone = 0;
			ArrayList<String> qual_list=new ArrayList<String>();
			control=new Register();
			
			if(control.isUsernameUnique(username)){
				redirect="index.jsp?index=register&err=1&usertype="+usertype;
			}else if(control.isEmailUnique(email1) || control.isEmailUnique(email2) || email1.equalsIgnoreCase(email2)){
				redirect="index.jsp?index=register&err=2&usertype="+usertype;
				//response.sendRedirect("home.jsp?index=register&err=2&usertype="+usertype);
			}else if(request.getParameter("type")!=null){
				if(usertype.equalsIgnoreCase("mod")){
					String qual = (String)request.getParameter("qualification");
					String qual_array[] = qual.split(",");
					for(String a:qual_array){
						if(!a.trim().equals("")){
							qual_list.add(a.trim().toUpperCase());
						}
					}
				}
				
				if(usertype.equalsIgnoreCase("mod") || usertype.equalsIgnoreCase("admin")){
					phone = Long.valueOf(request.getParameter("emnumber"));
				}
				
				switch(usertype){
					case "user": control.registerUser(username, password, email1, email2, fname, lname, aboutme, url1, url2, url3, "", "", "","",address, 1, 1);
						break;
					case "mod": control.registerUser(username, password, email1, email2, fname, lname, aboutme, url1, url2, url3, "", "", "","",address, 4, 1);
						control.addModerator(username,String.valueOf(phone),qual_list);
						break;
					case "admin": control.registerUser(username, password, email1, email2, fname, lname, aboutme, url1, url2, url3, "", "", "","",address, 5, 1);
						control.addAdministrator(username,String.valueOf(phone));
						break;
				}
			}
			redirect = "index.jsp?index=login&success=3";
		}
		response.sendRedirect(redirect);
	}

}
