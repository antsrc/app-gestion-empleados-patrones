package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.ControlledException;

@WebServlet("/menu")
public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String opcion = request.getParameter("opcion");

		try {
			if ("mostrarEmpleados".equals(opcion)) {
				request.getRequestDispatcher("/empleados").forward(request, response);
			} else if ("buscarEmpleados".equals(opcion)) {
				request.getRequestDispatcher("/empleados").forward(request, response);
			} else if ("buscarSalario".equals(opcion)) {
				request.getRequestDispatcher("/nominas").forward(request, response);
			} else if ("inicio".equals(opcion)) {
				response.sendRedirect("index.jsp");
			} else {
				throw new ControlledException("Página no encontrada");
			}
		} catch (ControlledException e) {
			manejarError(e.getMessage(), request, response);
		} catch (Exception e) {
			e.printStackTrace();
			manejarError("Error inesperado", request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		try {
			if ("enviarCambios".equals(opcion)) {
				request.getRequestDispatcher("/empleados").forward(request, response);
			} else if ("modificarEmpleado".equals(opcion)) {
				request.getRequestDispatcher("/empleados").forward(request, response);
			} else if ("mostrarEmpleadosFiltrados".equals(opcion)) {
				request.getRequestDispatcher("/empleados").forward(request, response);
			} else if ("mostrarSalario".equals(opcion)) {
				request.getRequestDispatcher("/nominas").forward(request, response);
			} else {
				throw new ControlledException("Página no encontrada");
			}
		} catch (ControlledException e) {
			manejarError(e.getMessage(), request, response);
		} catch (Exception e) {
			e.printStackTrace();
			manejarError("Error inesperado", request, response);
		}
	}

	private void manejarError(String message, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("mensaje", message);
		request.getRequestDispatcher("/views/error.jsp").forward(request, response);
	}

}
