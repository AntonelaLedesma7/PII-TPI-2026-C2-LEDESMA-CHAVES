package smartgrids;

import java.time.LocalDateTime;

public class PlantaSolar extends PlantaRenovable {
	private final double superficieCubierta;
	private int cantidadPaneles;
	private double horasRadiacion;
		
	public PlantaSolar(String nombre, String direccion, double capacidadMax, LocalDateTime fechaHoraIniciacion, String fuentePrimaria, double eficienciaPromedio, double superficieCubierta, int cantidadPaneles, double horasRadiacion) {
		super(nombre, direccion, capacidadMax, fechaHoraIniciacion, fuentePrimaria, eficienciaPromedio);
		if(superficieCubierta <= 0) {
			throw new RuntimeException("La superficie cubierta no puede ser negativa o igual a cero.");
		}
		if(cantidadPaneles <= 0) {
			throw new RuntimeException("La cantidad de paneles solares no puede ser negativa o igual a cero.");
		}
		if(horasRadiacion < 0 || horasRadiacion > 24) {
			throw new RuntimeException("Las horas de radiacion diarias no pueden ser negativas ni superar las 24hs.");
		}
		this.superficieCubierta = superficieCubierta;
		this.cantidadPaneles = cantidadPaneles;
		this.horasRadiacion = horasRadiacion;
	}

	public int getCantidadPaneles() {
		return cantidadPaneles;
	}

	public void setCantidadPaneles(int cantidadPaneles) {
		this.cantidadPaneles = cantidadPaneles;
	}

	public double getHorasRadiacion() {
		return horasRadiacion;
	}

	public void setHorasRadiacion(double horasRadiacion) {
		this.horasRadiacion = horasRadiacion;
	}

	public double getSuperficieCubierta() {
		return superficieCubierta;
	}

	@Override
	public double capacidadMinimaCuota() {
	    return getCapacidadMax() * getEficienciaPromedio() * (this.horasRadiacion / 24.0);
	}
}
