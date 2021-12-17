package controller.itinerario;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Ofertable;

import model.User;
import persistence.commons.DAOFactory;
import services.ItinerarioService;


@WebServlet("/itinerario/index.do")
public class ItinerarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private ItinerarioService itiService;

	@Override
	public void init(){
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		itiService = new ItinerarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		User user = (User) req.getSession().getAttribute("user");
		List<Ofertable> listaIti = itiService.getListaIti(user);
		User currentUser = DAOFactory.getUserDAO().find(user.getId());
		req.getSession().setAttribute("user", currentUser);
		req.setAttribute("listaIti", listaIti);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/itinerario/index.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
