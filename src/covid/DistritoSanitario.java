package covid;

public class DistritoSanitario implements Comparable<DistritoSanitario> {

	private String nombre;
	private int poblacion;
	private int positivos;

	public DistritoSanitario(String n, int pob, int posi) {
		if (pob <= 0 || posi < 0)
			throw new COVIDException("Parametros de entrada erroneos");

		nombre = n;
		poblacion = pob;
		positivos = posi;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPoblacion() {
		return poblacion;
	}

	public int getPositivos() {
		return positivos;
	}

	public void setPositivos(int pos) {
		if (pos < 0)
			throw new COVIDException("Numero de positivos negativo: " + pos);

		positivos = pos;
	}

	public int incidenciaAcumulada() {
		return (100000 * positivos) / poblacion;
	}

	@Override
	public boolean equals(Object o) {
		boolean ok = o instanceof DistritoSanitario;
		DistritoSanitario d = ok ? (DistritoSanitario) o : null;
		return ok && this.nombre.equalsIgnoreCase(d.nombre);
	}

	@Override
	public int hashCode() {
		return nombre.toUpperCase().hashCode();
	}

	@Override
	public int compareTo(DistritoSanitario d) {
		return d.nombre.compareToIgnoreCase(this.nombre);
	}

	@Override
	public String toString() {
		return "Distrito(" + nombre + ", " + positivos + ")";
	}

}
