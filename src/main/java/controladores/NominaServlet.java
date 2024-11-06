package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.ControlledException;
import servicios.NominaService;

@WebServlet("/nominas")
public class NominaServlet extends HttpServlet {

	private NominaService nominaService;

	@Override
	public void init() throws ServletException {
		super.init();
		nominaService = new NominaService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");

		if ("buscarSalario".equals(opcion)) {
			buscarSalario(request, response);
		} else {
			throw new ControlledException("Página no encontrada");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");

		if ("mostrarSalario".equals(opcion)) {
			mostrarSalario(request, response);
		} else {
			throw new ControlledException("Página no encontrada");
		}
	}

	private void buscarSalario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/buscarSalario.jsp").forward(request, response);
	}

	private void mostrarSalario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dni = request.getParameter("dni");
		Integer salario;

		salario = nominaService.obtenerSalario(dni);
		request.setAttribute("salario", salario);
		request.setAttribute("dni", dni);
		request.getRequestDispatcher("/views/mostrarSalario.jsp").forward(request, response);

	}

}
