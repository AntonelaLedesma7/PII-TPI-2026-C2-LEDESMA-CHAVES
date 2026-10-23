package smartgrids;

import java.time.LocalDateTime;

public class PlantaHidraulica extends PlantaRenovable{
	private int cantidadTurbinas;
	private final double capacidadEmbalse;
	public PlantaHidraulica(String nombre, String direccion, double capacidadMax, LocalDateTime fechaHoraIniciacion, String fuentePrimaria, double eficienciaPromedio, int cantidadTurbinas, double capacidadEmbalse) {
		super(
				nombre,
				direccion,
				capacidadMax,
				fechaHoraIniciacion,
				fuentePrimaria,
				eficienciaPromedio);
	
		if(cantidadTurbinas <= 0) {
			throw new RuntimeException("La cantidad de turbinas debe ser mayor a cero.");
		}
		if(capacidadEmbalse < 0) {
			throw new RuntimeException("La capacidad del embalse no puede ser negativa.");
		}
		this.cantidadTurbinas = cantidadTurbinas;
		this.capacidadEmbalse = capacidadEmbalse;
	}
	
	public int getCantidadTurbinas() {
		return cantidadTurbinas;
	}
	
	public void setCantidadTurbinas(int cantidadTurbinas) {
		this.cantidadTurbinas = cantidadTurbinas;
	}
	
	public double getCapacidadEmbalse() {
		return capacidadEmbalse;
	}
	
	@Override
	public String toString() {
	    return super.toString()
	            + String.format(
	                " | Turbinas: %d | Capacidad embalse: %.2f",
	                cantidadTurbinas,
	                capacidadEmbalse
	            );
	}
}
