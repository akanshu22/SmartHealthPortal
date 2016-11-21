package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import View.Posts;

/**
 * Servlet implementation class DiscussionForumServlet
 */
@WebServlet("/DiscussionForumServlet")
public class DiscussionForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected DiscussionForum controller;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiscussionForumServlet() {
        super();
        controller = new DiscussionForum();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String username = (String) request.getSession().getAttribute("Username");
		if(type==null){
			response.sendRedirect("home.jsp");
		}else{
			int f_id = Integer.valueOf(request.getParameter("id"));
			if(type.equalsIgnoreCase("showforum")){
				ArrayList<Posts> posts_list;
				posts_list=controller.fetchPostsAlongWithCommentsWithForumID(f_id);
				request.getSession().setAttribute("Posts",posts_list);
				request.getSession().setAttribute("Forum",controller.getForum(f_id));
				response.sendRedirect("home.jsp?forum=showpost&id="+f_id);
			}else if(type.equalsIgnoreCase("Create Post")){
				int forum_id = f_id;
				String post_text = request.getParameter("text");
				if(controller.createNewPost(username,forum_id,post_text)){
					ArrayList<Posts> posts_list;
					if((posts_list=controller.fetchPostsAlongWithCommentsWithForumID(forum_id))==null){
						response.sendRedirect("home.jsp?error=9&index=forums");
					}else{
						request.getSession().setAttribute("Posts",posts_list);
						request.getSession().setAttribute("Forum",controller.getForum(forum_id));
						response.sendRedirect("home.jsp?success=6&forum=showpost&id="+forum_id);
					}
				}else{
					response.sendRedirect("home.jsp?error=10&forum=showpost&id="+forum_id);
				}
			}else if(type.equalsIgnoreCase("Comment")){
				String comment_text = request.getParameter("text");
				String comment_username = request.getParameter("username");
				String comment_timecreated = request.getParameter("timecreated");
				if(controller.commentOnPost(comment_username, comment_timecreated, username, comment_text)){
					ArrayList<Posts> posts_list;
					if((posts_list=controller.fetchPostsAlongWithCommentsWithForumID(f_id))==null){
						response.sendRedirect("home.jsp?error=9&index=forums");
					}else{
						request.getSession().setAttribute("Posts",posts_list);
						request.getSession().setAttribute("Forum",controller.getForum(f_id));
						response.sendRedirect("home.jsp?success=7&forum=showpost&id="+f_id);
					}
				}else{
					response.sendRedirect("home.jsp?error=11&forum=showpost&id="+f_id);
				}
			}else if(type.equalsIgnoreCase("Rate Post")){
				int rating = Integer.valueOf(request.getParameter("rating"));
				String post_username = request.getParameter("username");
				String post_timecreated = request.getParameter("timecreated");
				if(controller.rateThePost(post_username, post_timecreated, username, rating)){
					ArrayList<Posts> posts_list;
					if((posts_list=controller.fetchPostsAlongWithCommentsWithForumID(f_id))==null){
						response.sendRedirect("home.jsp?error=9&index=forums");
					}else{
						request.getSession().setAttribute("Posts",posts_list);
						request.getSession().setAttribute("Forum",controller.getForum(f_id));
						response.sendRedirect("home.jsp?success=8&forum=showpost&id="+f_id);
					}
				}else{
					response.sendRedirect("home.jsp?error=12&forum=showpost&id="+f_id);
				}
			}else if(type.equalsIgnoreCase("deleteforum")){
				if(controller.deleteForum(username, f_id)){
					response.sendRedirect("home.jsp?index=forums-active&success=1");
				}else{
					response.sendRedirect("home.jsp?index=forums-active&success=1");
				}
			}else if(type.equalsIgnoreCase("Create Forum")){
				String topic = request.getParameter("name");
				String summary = request.getParameter("summary");
				if(controller.createForum(username, topic, summary)){
					response.sendRedirect("home.jsp?index=forums-active&success=2");
				}else{
					response.sendRedirect("home.jsp?error=2&index=forums-new");
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
