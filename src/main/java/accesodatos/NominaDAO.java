package accesodatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.ConnectionManager;
import excepciones.ConexionErroneaException;
import excepciones.DatosNoCorrectosException;

public class NominaDAO {

	private static NominaDAO instance = null;

	private NominaDAO() {

	}

	public static NominaDAO getInstance() {
		if (instance == null) {
			instance = new NominaDAO();
		}
		return instance;
	}

	public Integer obtenerSalario(String dni) throws DatosNoCorrectosException, ConexionErroneaException   {

		Integer salario = null;
		String sql = "SELECT SUELDO FROM NOMINAS WHERE DNI = '" + dni + "'";

		try (Connection con = getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			if (rs.next()) {
				salario = rs.getInt("sueldo");
			}

			return salario;

		} catch (SQLException e) {
			throw new DatosNoCorrectosException();
		}

	}
	
	private Connection getConnection() throws SQLException, ConexionErroneaException {
		return ConnectionManager.getConnection();
	}

}
