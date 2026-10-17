package smartgrids;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public abstract class Planta {
	private static int contadorId = 1;
	private final int id;
	private final String nombre;
	private final String direccion;
	private final double capacidadMax;
	private boolean activa;
	private final LocalDateTime fechaHoraIniciacion;
	private LinkedList<RegistroMonitoreo> registrosMonitoreo;
	private LinkedList<PeriodoInactividad> periodosInactividad;
	
	
	public Planta(String nombre, String direccion, double capacidadMax, LocalDateTime fechaHoraIniciacion) {
		if(nombre == null || nombre.trim().isEmpty()) {
			throw new RuntimeException("El nombre de la planta no puede ser nulo ni vacío.");
		}
		if(direccion == null || direccion.trim().isEmpty()) {
			throw new RuntimeException("La dirección de la planta no puede ser nula ni vacía.");
		}
		if(capacidadMax <= 0) {
			throw new RuntimeException("La capacidad máxima no puede ser negativa.");
		}
		if(fechaHoraIniciacion == null) {
			throw new RuntimeException("La fecha y hora de iniciación no puede ser nula.");
		}
		this.id = contadorId++;
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMax = capacidadMax;
		this.activa = true;
		this.fechaHoraIniciacion = fechaHoraIniciacion;
	}
	
	public boolean estaActiva() {
		return activa;
	}
	
	
	public void cambiarEstado(boolean estado) {
		this.activa = estado;
	}
	
	
	public LinkedList<RegistroMonitoreo> getRegistrosMonitoreo() {
		return registrosMonitoreo;
	}
	
	
	public void agregarRegistroMonitoreo(RegistroMonitoreo registro) {
		registrosMonitoreo.add(registro);
	}
	
	
	public LinkedList<PeriodoInactividad> getPeriodosInactividad() {
		return periodosInactividad;
	}
	
	
	public void agregarPeriodoInactividad(PeriodoInactividad periodo) {
		periodosInactividad.add(periodo);
	}
	
	
	public int getId() {
		return id;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	
	public String getDireccion() {
		return direccion;
	}
	
	
	public double getCapacidadMax() {
		return capacidadMax;
	}
	
	
	public LocalDateTime getFechaHoraIniciacion() {
		return fechaHoraIniciacion;
	}
	
	public abstract double capacidadMinimaCuota();
	
	public boolean cumplioCuota(RegistroMonitoreo registro) {
	    return registro.getEnergiaGenerada() >= capacidadMinimaCuota();
	}
	
	@Override
	public String toString() {
	    String estado; 
	    if (activa) {
	        estado = "ACTIVA";
	    } else {
	        estado = "INACTIVA";
	    }
	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	    return String.format(
	        "%s (%s): %s",
	        nombre,
	        estado,
	        fechaHoraIniciacion.format(formato)
	    );
	}
	
}
