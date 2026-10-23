package smartgrids;

import java.time.LocalDateTime;

public abstract class PlantaRenovable extends Planta {
	private final String fuentePrimaria;
	private final double eficienciaPromedio;
	
	public PlantaRenovable(String nombre, String direccion, double capacidadMax, LocalDateTime fechaHoraIniciacion, String fuentePrimaria, double eficienciaPromedio) {
		super(
				nombre,
				direccion, 
				capacidadMax, 
				fechaHoraIniciacion);
	
		if(fuentePrimaria == null || fuentePrimaria.trim().isEmpty()) {
			throw new RuntimeException("El campo fuente primaria no puede ser nulo ni vacío.");
		}
		if(eficienciaPromedio < 0) {
			throw new RuntimeException("La eficiencia promedio no puede ser negativa.");
		}
		this.fuentePrimaria = fuentePrimaria;
		this.eficienciaPromedio = eficienciaPromedio;
	}

	public String getFuentePrimaria() {
		return fuentePrimaria;
	}

	public double getEficienciaPromedio() {
		return eficienciaPromedio;
	}
	
	@Override
	public double capacidadMinimaCuota() {
	    return getCapacidadMax() * getEficienciaPromedio();
	}
	
	@Override
	public String toString() {
	    return super.toString()
	            + String.format(
	                " | Fuente primaria: %s | Eficiencia promedio: %.2f",
	                fuentePrimaria,
	                eficienciaPromedio
	            );
	}
}
