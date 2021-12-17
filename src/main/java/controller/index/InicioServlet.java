package controller.index;

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
import services.InicioService;

@WebServlet("/index.do")
public class InicioServlet extends HttpServlet implements Servlet {
	
	private static final long serialVersionUID = 4398609736240875594L;
	private InicioService iniService; 
	
	@Override
	public void init() throws ServletException{
			super.init();
			iniService = new InicioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<Ofertable> ofertables = iniService.getLista();
		req.setAttribute("ofertables", ofertables);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			
				dispatcher.forward(req, resp);
		
	}
}
