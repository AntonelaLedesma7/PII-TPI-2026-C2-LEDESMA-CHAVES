package smartgrids;

import java.time.LocalDateTime;

public class RegistroMonitoreo {
	private static int contadorId = 1;
	private final int id;
	private final String nombrePlanta;
	private final LocalDateTime fechaHoraRegistro; 
	private final double energiaGenerada;
	private final int cantIncidencias;
	
	public RegistroMonitoreo(String nombrePlanta, LocalDateTime fechaHoraRegistro, double energiaGenerada, int cantIncidencias) {
		if(nombrePlanta == null || nombrePlanta.trim().isEmpty()) {
			throw new RuntimeException("El nombre de la planta asociada no puede ser nulo ni vacío.");
		}
		if(fechaHoraRegistro == null) {
			throw new RuntimeException("La fecha y hora del registro de monitoreo no puede ser nula.");
		}
		if(energiaGenerada < 0) {
			throw new RuntimeException("La energía generada no puede ser negativa.");
		}
		if(cantIncidencias < 0) {
			throw new RuntimeException("La cantidad de incidencias no puede ser negativa.");
		}
		
		this.id = contadorId++;
		this.nombrePlanta = nombrePlanta;
		this.fechaHoraRegistro = fechaHoraRegistro;
		this.energiaGenerada = energiaGenerada;
		this.cantIncidencias = cantIncidencias;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNombrePlanta() {
		return nombrePlanta;
	}
	
	public LocalDateTime getFechaHoraRegistro() {
		return fechaHoraRegistro;
	}
	
	public double getEnergiaGenerada() {
		return energiaGenerada;
	}
	
	public int getCantIncidencias() {
		return cantIncidencias;
	}
	
	public boolean estaSinDatos() {
		return this.energiaGenerada == 0.0 && this.cantIncidencias == 0;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Registro #%d%n[Planta: %s, | Fecha y hora de registro: %s]%nEnergía Generada: %.2f MWh | Incidencias: %d",
				id,
				nombrePlanta,
				fechaHoraRegistro.toString(),
				energiaGenerada,
				cantIncidencias);
	}
}
