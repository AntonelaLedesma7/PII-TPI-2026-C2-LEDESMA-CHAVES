package smartgrids;

import java.time.LocalDateTime;

public abstract class PlantaNoRenovable extends Planta {
	private final String tipoCombustible;
	private double objetivoEmisionCO2;
	private final double capacidadAlmacenamientoCombustible;
	
	
	public PlantaNoRenovable(String nombre, String direccion, double capacidadMax, LocalDateTime fechaHoraIniciacion, String tipoCombustible, double objetivoEmisionCO2, double capacidadAlmacenamientoCombustible) {
		super(nombre, direccion, capacidadMax, fechaHoraIniciacion);
		if(tipoCombustible == null || tipoCombustible.trim().isEmpty()) {
			throw new RuntimeException("El campo tipo de combustible no puede ser nulo ni vacío.");
		}
		if(objetivoEmisionCO2 < 0) {
			throw new RuntimeException("El objetivo de emision de CO2 no puede ser negativo.");
		}
		if(capacidadAlmacenamientoCombustible < 0) {
			throw new RuntimeException("La capacidad de almacenamiento de combustible no puede ser negativa.");
		}
		this.tipoCombustible = tipoCombustible;
		this.objetivoEmisionCO2 = objetivoEmisionCO2;
		this.capacidadAlmacenamientoCombustible = capacidadAlmacenamientoCombustible;
	}


	public double getObjetivoEmisionCO2() {
		return objetivoEmisionCO2;
	}


	public void setObjetivoEmisionCO2(double objetivoEmisionCO2) {
		this.objetivoEmisionCO2 = objetivoEmisionCO2;
	}


	public String getTipoCombustible() {
		return tipoCombustible;
	}


	public double getCapacidadAlmacenamientoCombustible() {
		return capacidadAlmacenamientoCombustible;
	}
	
	
}
