package controller.promociones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import persistence.commons.DAOFactory;
import services.ComprarPromoService;

@WebServlet("/promos/buy.do")
public class ComprarPromoServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 3807955355317434397L;
	private ComprarPromoService promoComprar;

	@Override
	public void init() {
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		promoComprar = new ComprarPromoService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		int promoId = Integer.parseInt(req.getParameter("id"));
		User user = (User) req.getSession().getAttribute("user");
		promoComprar.comprarPromo(user.getId(), promoId);
		User currentUser = DAOFactory.getUserDAO().find(user.getId());
		req.getSession().setAttribute("user", currentUser);
		req.setAttribute("success", "¡Gracias por comprar!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promos/index.do");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
