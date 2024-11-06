package controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.ControlledException;
import modelo.Empleado;
import servicios.EmpleadoService;

@WebServlet("/empleados")
public class EmpleadoServlet extends HttpServlet {

	private EmpleadoService empleadoService;

	@Override
	public void init() throws ServletException {
		super.init();
		empleadoService = new EmpleadoService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		if ("mostrarEmpleados".equals(opcion)) {
			mostrarEmpleados(request, response);
		} else if ("buscarEmpleados".equals(opcion)) {
			buscarEmpleados(request, response);
		} else {
			throw new ControlledException("Página no encontrada");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		if ("enviarCambios".equals(opcion)) {
			enviarCambios(request, response);
		} else if ("modificarEmpleado".equals(opcion)) {
			modificarEmpleado(request, response);
		} else if ("mostrarEmpleadosFiltrados".equals(opcion)) {
			mostrarEmpleadosFiltrados(request, response);
		} else {
			throw new ControlledException("Página no encontrada");
		}

	}

	private void mostrarEmpleados(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Empleado> empleados;

		empleados = empleadoService.obtenerEmpleados();
		request.setAttribute("empleados", empleados);
		request.getRequestDispatcher("/views/mostrarEmpleados.jsp").forward(request, response);

	}

	private void buscarEmpleados(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/views/buscarEmpleados.jsp").forward(request, response);
	}

	private void mostrarEmpleadosFiltrados(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		String dni = request.getParameter("dni");
		String sexo = request.getParameter("sexo");
		String categoria = request.getParameter("categoria");
		String antiguedad = request.getParameter("antiguedad");
		List<Empleado> empleados = null;

		empleados = empleadoService.obtenerEmpleadosFiltrados(nombre, dni, sexo, categoria, antiguedad);
		request.setAttribute("empleados", empleados);
		request.getRequestDispatcher("/views/mostrarEmpleados.jsp").forward(request, response);

	}

	private void modificarEmpleado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dni = request.getParameter("dni");
		Empleado empleado = null;

		empleado = empleadoService.obtenerEmpleado(dni);
		request.setAttribute("empleado", empleado);
		request.getRequestDispatcher("/views/modificarEmpleado.jsp").forward(request, response);

	}

	private void enviarCambios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String sexo = request.getParameter("sexo");
		Integer categoria = Integer.parseInt(request.getParameter("categoria"));
		Integer antiguedad = Integer.parseInt(request.getParameter("antiguedad"));

		empleadoService.actualizarEmpleado(dni, nombre, sexo, categoria, antiguedad);
		response.sendRedirect("empleados?opcion=mostrarEmpleados&exito=true");

	}

}