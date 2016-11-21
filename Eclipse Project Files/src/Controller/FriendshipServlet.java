package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FriendshipServlet
 */
@WebServlet("/FriendshipServlet")
public class FriendshipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Friendship friendship;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendshipServlet() {
        super();
        friendship = new Friendship();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		if(type!=null){
			String uname = null;
			if(request.getParameter("uname")!=null)
				uname = request.getParameter("uname");
			if(type.equalsIgnoreCase("unfriend")){
				if(friendship.unFriend(request.getSession().getAttribute("Username").toString(), uname)){
					request.getSession().setAttribute("FriendList", friendship.getMyFriendList(request.getSession().getAttribute("Username").toString()));
					request.getSession().setAttribute("UsersList", friendship.getUsersList(request.getSession().getAttribute("Username").toString()));
					response.sendRedirect("home.jsp?success=1&index=friends");
				}
				else
					response.sendRedirect("home.jsp?error=1&index=friends");
			}else if(type.equalsIgnoreCase("acceptRequest")){
				if(friendship.acceptFriendRequest(request.getSession().getAttribute("Username").toString(), uname))
					response.sendRedirect("home.jsp?success=2&index=request");
				else
					response.sendRedirect("home.jsp?error=3&index=request");
			}else if(type.equalsIgnoreCase("withdrawRequest")){
				if(friendship.withdrawRequest(request.getSession().getAttribute("Username").toString(), uname))
					response.sendRedirect("home.jsp?success=3&index=request");
				else
					response.sendRedirect("home.jsp?error=4&index=request");
			}else if(type.equalsIgnoreCase("sendRequest")){
				ArrayList<UsersList> search_result = friendship.searchUser(uname); 
				if(search_result==null || search_result.size()==0 || uname.equalsIgnoreCase((String)request.getSession().getAttribute("Username")))
					response.sendRedirect("home.jsp?error=5&index=home");
				else{
					if(friendship.sendRequest((String)request.getSession().getAttribute("Username"), uname))
						response.sendRedirect("home.jsp?index=request");
					else
						response.sendRedirect("home.jsp?error=5&index=home");
				}
			}else if(type.equalsIgnoreCase("rejectRequest")){
				if(friendship.rejectFriendRequest(request.getSession().getAttribute("Username").toString(), uname))
					response.sendRedirect("home.jsp?success=4&index=request");
				else
					response.sendRedirect("home.jsp?error=6&index=request");
			}
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
