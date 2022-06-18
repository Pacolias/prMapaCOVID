package covid;

public class COVIDException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public COVIDException() {
		super();
	}

	public COVIDException(String mensaje) {
		super(mensaje);
	}

}
