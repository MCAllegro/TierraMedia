package controller.users;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.UserService;

import java.io.IOException;


@WebServlet("/users/delete.do")
public class DeleteUserServlet extends HttpServlet implements Servlet {
       
  
	private static final long serialVersionUID = 2407425456750122917L;

	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		userService.erase(id);

		resp.sendRedirect("/TierraMedia/users/index.do");
	}

}
