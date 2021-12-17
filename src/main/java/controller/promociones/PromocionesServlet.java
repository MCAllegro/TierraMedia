package controller.promociones;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promocion;
import services.PromocionesService;

@WebServlet("/promos/index.do")
public class PromocionesServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 7224537237636487419L;
	private PromocionesService promoService;
	
	@Override
	public void init(){
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		promoService = new PromocionesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		List<Promocion> promos = promoService.getList();
		req.setAttribute("promos", promos);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promos/index.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
