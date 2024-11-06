package servicios;

import accesodatos.EmpleadoDAO;
import excepciones.ConexionErroneaException;
import excepciones.ControlledException;
import excepciones.DatosNoCorrectosException;
import modelo.Empleado;

import java.util.List;

public class EmpleadoService {

	private EmpleadoDAO empleadoDAO;

	public EmpleadoService() {
			this.empleadoDAO = EmpleadoDAO.getInstance();
	}

	public List<Empleado> obtenerEmpleados() throws ControlledException {
		try {
			return empleadoDAO.obtenerEmpleados();
		} catch (DatosNoCorrectosException e) {
			throw new ControlledException("Datos no soportados");
		} catch (ConexionErroneaException e) {
			throw new ControlledException("Problema al conectar con la base de datos");
		}
	}

	public Empleado obtenerEmpleado(String dni) throws ControlledException {

		try {
			return empleadoDAO.obtenerEmpleado(dni);
		} catch (DatosNoCorrectosException e) {
			throw new ControlledException("Datos no soportados");
		} catch (ConexionErroneaException e) {
			throw new ControlledException("Problema al conectar con la base de datos");
		}

	}

	public List<Empleado> obtenerEmpleadosFiltrados(String nombre, String dni, String sexo, String categoriaStr,
			String antiguedadStr) throws ControlledException {

		Integer categoria = null;
		Integer antiguedad = null;

		if (categoriaStr != null && !categoriaStr.isEmpty()) {
			categoria = Integer.parseInt(categoriaStr);
		}

		if (antiguedadStr != null && !antiguedadStr.isEmpty()) {
			antiguedad = Integer.parseInt(antiguedadStr);
		}

		try {
			return empleadoDAO.obtenerEmpleadosFiltrados(nombre, dni, sexo, categoria, antiguedad);
		} catch (DatosNoCorrectosException e) {
			throw new ControlledException("Datos no soportados");
		} catch (ConexionErroneaException e) {
			throw new ControlledException("Problema al conectar con la base de datos");
		}

	}

	public boolean actualizarEmpleado(String dni, String nombre, String sexo, Integer categoria, Integer antiguedad)
			throws ControlledException {

		try {
			return (empleadoDAO.actualizarEmpleado(dni, nombre, sexo, categoria, antiguedad)) > 0;
		} catch (DatosNoCorrectosException e) {
			throw new ControlledException("Datos no soportados");
		} catch (ConexionErroneaException e) {
			throw new ControlledException("Problema al conectar con la base de datos");
		}

	}
}
