package servicios;

import accesodatos.NominaDAO;
import excepciones.ConexionErroneaException;
import excepciones.ControlledException;
import excepciones.DatosNoCorrectosException;

public class NominaService {

	private NominaDAO nominaDAO;

	public NominaService() {
		this.nominaDAO = NominaDAO.getInstance();
	}

	public Integer obtenerSalario(String dni) throws ControlledException {

			try {
				return nominaDAO.obtenerSalario(dni);
			} catch (DatosNoCorrectosException e) {
				throw new ControlledException("Datos no soportados");
			} catch (ConexionErroneaException e) {
				throw new ControlledException("Problema al conectar con la base de datos");
			}

	}
}