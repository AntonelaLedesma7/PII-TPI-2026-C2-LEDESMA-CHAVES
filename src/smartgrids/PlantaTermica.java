package smartgrids;

import java.time.LocalDateTime;

public class PlantaTermica extends PlantaNoRenovable{

	public PlantaTermica(String nombre, String direccion, double capacidadMax, LocalDateTime fechaHoraIniciacion,
			String tipoCombustible, double objetivoEmisionCO2, double capacidadAlmacenamientoCombustible) {
		super(nombre, direccion, capacidadMax, fechaHoraIniciacion, tipoCombustible, objetivoEmisionCO2,
				capacidadAlmacenamientoCombustible);
	}
	
	@Override
	public double capacidadMinimaCuota() {
	    return getCapacidadMax() / 2.0;
	}
	
	@Override
	public boolean cumplioCuota(RegistroMonitoreo registro) {
		if(!(registro instanceof RegistroTermico)) {
			return false;
		}
	    RegistroTermico rt = (RegistroTermico) registro;
	    return rt.getEnergiaGenerada() >= capacidadMinimaCuota()
	        && rt.getCo2Emitido() < getObjetivoEmisionCO2();
	}
}
