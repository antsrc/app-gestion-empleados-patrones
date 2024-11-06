package accesodatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import conexion.ConnectionManager;
import excepciones.ConexionErroneaException;
import excepciones.DatosNoCorrectosException;
import modelo.Empleado;

public class EmpleadoDAO {

	private static EmpleadoDAO instance = null;

	private EmpleadoDAO() {

	}

	public static EmpleadoDAO getInstance() {
		if (instance == null) {
			instance = new EmpleadoDAO();
		}
		return instance;
	}

	public Integer actualizarEmpleado(String dni, String nombre, String sexo, Integer categoria, Integer antiguedad)
			throws DatosNoCorrectosException, ConexionErroneaException {
		String sql = "UPDATE EMPLEADOS SET nombre = '" + nombre + "', sexo = '" + sexo + "', categoria = " + categoria
				+ ", antiguedad = " + antiguedad + " WHERE dni = '" + dni + "'";
		
		try (Connection con = getConnection(); Statement st = con.createStatement()) {
			return st.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e);
			throw new DatosNoCorrectosException();
		}

	}

	public List<Empleado> obtenerEmpleados() throws DatosNoCorrectosException, ConexionErroneaException {

		List<Empleado> empleados = new ArrayList<>();
		String sql = "SELECT * FROM EMPLEADOS";

		try (Connection con = getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {

				String nombre = rs.getString("nombre");
				String dni = rs.getString("dni");
				Character sexo = rs.getString("sexo").charAt(0);
				Integer categoria = rs.getInt("categoria");
				Integer antiguedad = rs.getInt("antiguedad");

				Empleado empl = new Empleado(nombre, dni, sexo, categoria, antiguedad);
				empleados.add(empl);
			}

			return empleados;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatosNoCorrectosException();
		}

	}

	public List<Empleado> obtenerEmpleadosFiltrados(String nombre, String dni, String sexo, Integer categoria,
			Integer antiguedad) throws DatosNoCorrectosException, ConexionErroneaException {
		List<Empleado> empleados = new ArrayList<>();

		String sql = "SELECT nombre, dni, sexo, categoria, antiguedad FROM EMPLEADOS WHERE 1=1"
				+ (nombre != null && !nombre.isEmpty() ? " AND nombre LIKE '%" + nombre + "%'" : "")
				+ (dni != null && !dni.isEmpty() ? " AND dni LIKE '%" + dni + "%'" : "")
				+ (sexo != null && !sexo.isEmpty() ? " AND sexo = '" + sexo.charAt(0) + "'" : "")
				+ (categoria != null ? " AND categoria = " + categoria : "")
				+ (antiguedad != null ? " AND antiguedad = " + antiguedad : "");

		try (Connection con = getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				String nombreEmp = rs.getString("nombre");
				String dniEmp = rs.getString("dni");
				Character sexoEmp = rs.getString("sexo").charAt(0);
				Integer categoriaEmp = rs.getInt("categoria");
				Integer antiguedadEmp = rs.getInt("antiguedad");

				Empleado empl = new Empleado(nombreEmp, dniEmp, sexoEmp, categoriaEmp, antiguedadEmp);
				empleados.add(empl);
			}

			return empleados;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatosNoCorrectosException();
		}

	}

	public Empleado obtenerEmpleado(String dni) throws DatosNoCorrectosException, ConexionErroneaException {

		Empleado empl = null;
		String sql = "SELECT * FROM EMPLEADOS WHERE DNI = '" + dni + "'";

		try (Connection con = getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			if (rs.next()) {
				String nombreEmp = rs.getString("nombre");
				String dniEmp = rs.getString("dni");
				Character sexoEmp = rs.getString("sexo").charAt(0);
				Integer categoriaEmp = rs.getInt("categoria");
				Integer antiguedadEmp = rs.getInt("antiguedad");

				empl = new Empleado(nombreEmp, dniEmp, sexoEmp, categoriaEmp, antiguedadEmp);
			}

			return empl;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatosNoCorrectosException();
		}

	}

	private Connection getConnection() throws ConexionErroneaException {
		return ConnectionManager.getConnection();
	}

}
