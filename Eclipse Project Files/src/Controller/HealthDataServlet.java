package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import View.User;

/**
 * Servlet implementation class HealthDataServlet
 */
@WebServlet("/HealthDataServlet")
public class HealthDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HealthData controller;
	private String username;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HealthDataServlet() {
        super();
        controller = new HealthData();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		username = (String) request.getSession().getAttribute("Username");
		String type=request.getParameter("type");
		if(type!=null){
			String uname = null;
			if(request.getParameter("uname")!=null)
				uname = request.getParameter("uname");
			if(type.equalsIgnoreCase("showHealthData")){
				ArrayList<View.HealthData> list = controller.retrieveHealthData(uname);
				if(list==null || list.size()<1){
					response.sendRedirect("home.jsp?error=7&index=friends");
				}
				else{
					request.getSession().setAttribute("HealthDataList", list);
					response.sendRedirect("home.jsp?friends=healthdata&uname="+uname);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		username = (String) request.getSession().getAttribute("Username");
		if(request.getParameter("addHealthData")!=null){
			int property = Integer.valueOf(request.getParameter("property"));
			String value = request.getParameter("value");
			if(controller.insertHealthData(username,property,value)==true){
				User u = (User)request.getSession().getAttribute("User");
				u.setHealth_data(controller.retrieveHealthData(username));
				request.getSession().setAttribute("User", u);
				response.sendRedirect("home.jsp?success=5&index=profile");
			}else{
				response.sendRedirect("home.jsp?error=8&index=addhealth");
			}
		}
	}

}
