package smartgrids;

import java.time.LocalDateTime;

public class PeriodoInactividad {
	private final LocalDateTime fechaHoraInicio;
	private final LocalDateTime fechaHoraFin;
	
	public PeriodoInactividad(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
		if(fechaHoraInicio == null) {
			throw new RuntimeException("La fecha y hora de inicio de inactividad no puede ser nula.");
		}
		if(fechaHoraFin == null) {
			throw new RuntimeException("La fecha y hora de fin de inactividad no puede ser nula.");
		}
		if(fechaHoraFin.isBefore(fechaHoraInicio)) {
			throw new RuntimeException("La fecha de fin de inactividad no puede ser anterior a la de inicio");
		}
		
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
	}
	
	// Evalúa si una fecha y hora dada se encuenta dentro del periodo de inactividad.
	// Recibe por parámetro fechaHora 
	// Devuelve true si fechaHoraInicio <= fechaHora <= fechaHoraFin
	
	public boolean perteneceAPeriodoInactividad(LocalDateTime fechaHora) {
		if(fechaHora == null) {
			return false;
		}
		
		boolean despuesDeInicio = !fechaHora.isBefore(fechaHoraInicio);
		boolean antesDeFin = !fechaHora.isAfter(fechaHoraFin);
		
		return despuesDeInicio && antesDeFin;
	}
	
	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}
	
	public LocalDateTime getFechaHoraFin() {
		return fechaHoraFin;
	}
	
	@Override
	public String toString() {
		return String.format("{%s a %s}", fechaHoraInicio.toString(), fechaHoraFin.toString());
	}
}