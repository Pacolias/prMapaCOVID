package covid;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class InfoPoblacion implements InfoCOVID {

	private int minimo;
	private int maximo;

	public InfoPoblacion(int min, int max) {
		minimo = min;
		maximo = max;
	}

	@Override
	public Set<String> obtenerInfo(MapaCOVID mapa) {
		Set<String> set = new TreeSet<>();

		for (DistritoSanitario ds : mapa.getDistritos()) {
			if (ds.getPoblacion() >= minimo && ds.getPoblacion() <= maximo)
				set.add(ds.getNombre());
		}

		return set;
	}
}
