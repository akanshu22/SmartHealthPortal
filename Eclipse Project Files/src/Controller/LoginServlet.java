package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import View.Admin;
import View.Mod;
import View.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("deactivate")!=null){
			User user1 = null;
			Mod user2 = null;
			Admin user3 = null;
			int user_type=(int) request.getSession().getAttribute("Type");
			Login control=new Login();
			Boolean status = null;
			
			switch(user_type){
				case 1:
				case 2:
				case 3: user1 = (User) request.getSession().getAttribute("User");
					status=control.deactivateMyProfile(user1.getUserName());
					break;
				case 4: user2 = (Mod) request.getSession().getAttribute("User");
					status=control.deactivateMyProfile(user2.getUserName());
					break;
				case 5: user3 = (Admin) request.getSession().getAttribute("User");
					status=control.deactivateMyProfile(user3.getUserName());
					break;
			}
			
			if(status)
				response.sendRedirect("index.jsp?success=1");
			else
				response.sendRedirect("index.jsp?error=2");
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		if(request.getParameter("login")!=null || request.getParameter("reactivate")!=null){
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String register = request.getParameter("register");
			
			Login control=new Login();
			
			if(request.getParameter("reactivate")!=null){
				Boolean status = control.reactivateAccount(email, password);
				if(status)
				{
					response.sendRedirect("index.jsp?success=2");
					//request.getRequestDispatcher("index.jsp?success=2").forward(request, response);
				}
//					
				else
					response.sendRedirect("index.jsp?error=3");
					//request.getRequestDispatcher("index.jsp?success=3").forward(request, response);
			}else{
				String result=control.authenticateUser(email, password).toString();
				
				if(result.equalsIgnoreCase("True")){
					User user1 = null;
					Mod user2 = null;
					Admin user3 = null;
					int user_type=control.getUserType(email);
					HttpSession session = request.getSession();
					session.setAttribute("Type", user_type);
					
					switch(user_type){
						case 1:
						case 2:
						case 3: user1 = control.getUserDetails(email);
							user1.setHealth_data((new HealthData()).retrieveHealthData(user1.getUserName()));
							session.setAttribute("User",user1);
							session.setAttribute("Username", user1.getUserName());
							Friendship f = new Friendship();
							session.setAttribute("FriendList", f.getMyFriendList(user1.getUserName()));
							session.setAttribute("UsersList", f.getUsersList(user1.getUserName()));
							break;
						case 4: user2 = control.getModeratorDetails(email);
							session.setAttribute("Username", user2.getUserName());
							session.setAttribute("User",user2);
							break;
						case 5: user3 = control.getAdminDetails(email);
							session.setAttribute("Username", user3.getUserName());
							session.setAttribute("User",user3);
							break;
					}
					response.sendRedirect("./home.jsp");
				}else{
					response.sendRedirect("index.jsp?error=1&index=login");
				}
			}
		}
	}

}
