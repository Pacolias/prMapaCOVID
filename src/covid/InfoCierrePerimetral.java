package covid;

import java.util.Set;
import java.util.TreeSet;

public class InfoCierrePerimetral implements InfoCOVID {

	@Override
	public Set<String> obtenerInfo(MapaCOVID mapa) {
		Set<String> set = new TreeSet<>();
		for (String provincia : mapa.getProvincias()) {
			if (mapa.incidenciaProvincia(provincia) >= 500)
				set.add(provincia);
		}

		return set;
	}
}
