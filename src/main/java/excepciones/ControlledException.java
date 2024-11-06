package excepciones;

import javax.servlet.ServletException;

public class ControlledException extends ServletException {
	// Es necesario que extienda a ServletException para poder propagarla
	// de un servlet secundario al controlador principal, donde se gestiona
	
	public ControlledException() {
		super();
	}
	
	public ControlledException(String message) {
		super(message);
	}
	
}
