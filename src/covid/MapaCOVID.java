package covid;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class MapaCOVID {

	private SortedMap<String, SortedSet<DistritoSanitario>> mapa;
	private String nombre;

	public MapaCOVID(String prov, String file) throws FileNotFoundException {
		nombre = prov;
		mapa = new TreeMap<>();
		leerDatos(file);
	}

	public void leerDatos(String file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		sc.useDelimiter("\\s*[():]+\\s*");
		sc.useLocale(Locale.ENGLISH);
		leerDatos(sc);
		sc.close();
	}

	public void leerDatos(Scanner sc) {
		while (sc.hasNextLine()) {
			try (Scanner scLinea = new Scanner(sc.nextLine())) {

				String distrito = sc.next();
				String prov = sc.next();
				int poblacion = sc.nextInt();
				int positivos = sc.nextInt();
				DistritoSanitario ds = new DistritoSanitario(prov, poblacion, positivos);

				agregarDistrito(prov, ds);
			} catch (NoSuchElementException | COVIDException nsee) {
				// ignore
			}
		}
	}

	public void agregarDistrito(String prov, DistritoSanitario ds) {
		SortedSet<DistritoSanitario> set = mapa.getOrDefault(prov, new TreeSet<>());
		set.add(ds);
		mapa.putIfAbsent(prov, set);
	}

	public String getNombre() {
		return nombre;
	}

	public Set<String> getProvincias() {
		return mapa.keySet();
	}

	public Set<DistritoSanitario> getDistritos() {
		Set<DistritoSanitario> distritos = new TreeSet<>();
		for (SortedSet<DistritoSanitario> set : mapa.values()) {
			for (DistritoSanitario ds : set) {
				distritos.add(ds);
			}
		}

		return distritos;
	}

	public int incidenciaProvincia(String prov) {
		int positivos = 0;
		int poblacion = 0;

		SortedSet<DistritoSanitario> distritos = mapa.get(prov);

		if (distritos != null) {
			for (DistritoSanitario ds : mapa.get(prov)) {
				positivos += ds.getPositivos();
				poblacion += ds.getPoblacion();
			}
		}

		return distritos != null ? (100000 * positivos) / poblacion : 0;
	}

	public void mostrarMapa(String file) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(file);
		mostrarMapa(pw);
		pw.close();
	}

	public void mostrarMapa(PrintWriter pw) {
		pw.println(this.nombre.toUpperCase() + ": ");

		for (Map.Entry<String, SortedSet<DistritoSanitario>> e : mapa.entrySet()) {
			pw.println(e.getKey());

			for (DistritoSanitario ds : e.getValue()) {
				pw.println("\t" + ds.toString());
			}
		}
	}

	public Set<String> obtenerInfoCOVID(InfoCOVID info) {
		return info.obtenerInfo(this);
	}

}
