
package smartgrids;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class Planta {

    private static int contadorId = 1;
    private final int id;
    private final String nombre;
    private final String direccion;
    private final double capacidadMax;
    private boolean activa;
    private final LocalDateTime fechaHoraIniciacion;
    private final LinkedList<RegistroMonitoreo> registrosMonitoreo;
    private final LinkedList<PeriodoInactividad> periodosInactividad;
    private LocalDateTime inicioInactividadActual;
    private LocalDateTime ultimoCambioEstado;

    public Planta(String nombre, String direccion, double capacidadMax, LocalDateTime fechaHoraIniciacion) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new RuntimeException("El nombre no puede ser nulo ni vacio.");
        }
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new RuntimeException("La direccion no puede ser nula ni vacia.");
        }
        if (!Double.isFinite(capacidadMax) || capacidadMax <= 0) {
            throw new RuntimeException("La capacidad maxima debe ser positiva.");
        }
        if (fechaHoraIniciacion == null) {
            throw new RuntimeException("La fecha de iniciacion no puede ser nula.");
        }
        if (fechaHoraIniciacion.isAfter(LocalDateTime.now())) {
            throw new RuntimeException("La fecha de iniciacion no puede ser futura.");
        }

        this.id = contadorId++;
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidadMax = capacidadMax;
        this.activa = true;
        this.fechaHoraIniciacion = fechaHoraIniciacion;
        this.registrosMonitoreo = new LinkedList<>();
        this.periodosInactividad = new LinkedList<>();
        this.inicioInactividadActual = null;
        this.ultimoCambioEstado = null;
    }

    public boolean estaActiva() {
        return activa;
    }

    private void validarFechaCambioEstado(LocalDateTime fechaHora) {
        if (fechaHora == null) {
            throw new RuntimeException("La fecha del cambio no puede ser nula.");
        }
        if (fechaHora.isBefore(fechaHoraIniciacion)) {
            throw new RuntimeException("El cambio no puede ser anterior a la activación.");
        }
        if (ultimoCambioEstado != null && !fechaHora.isAfter(ultimoCambioEstado)) {
            throw new RuntimeException("El cambio debe ser posterior al anterior.");
        }
    }

    public void desactivar(LocalDateTime fechaHora) {
        validarFechaCambioEstado(fechaHora);

        if (!activa) {
            throw new RuntimeException("La planta ya esta inactiva.");
        }
        
        inicioInactividadActual = fechaHora;
        ultimoCambioEstado = fechaHora;
        activa = false;
    }

    public void activar(LocalDateTime fechaHora) {
        validarFechaCambioEstado(fechaHora);

        if (activa) {
            throw new RuntimeException("La planta ya esta activa.");
        }
        
        PeriodoInactividad periodo = new PeriodoInactividad(inicioInactividadActual,fechaHora);
        
        periodosInactividad.add(periodo);
        inicioInactividadActual = null;
        ultimoCambioEstado = fechaHora;
        activa = true;
    }

    public boolean estuvoInactivaEn(LocalDateTime fechaHora) {
        if (fechaHora == null) {
            throw new RuntimeException("La fecha no puede ser nula.");
        }
        
        for (PeriodoInactividad periodo : periodosInactividad) {
            if (periodo.perteneceAPeriodoInactividad(fechaHora)) {
                return true;
            }
        }
        
        if (!activa && inicioInactividadActual != null) {
            return !fechaHora.isBefore(inicioInactividadActual);
        }
        return false;
    }

    public LinkedList<PeriodoInactividad> getPeriodosInactividad() {
        return new LinkedList<>(periodosInactividad);
    }

    public void agregarRegistroMonitoreo(RegistroMonitoreo registro) {
        if (registro == null) {
            throw new RuntimeException("El registro no puede ser nulo.");
        }
        if (!estaActiva()) {
            throw new RuntimeException("No se puede agregar un registro de monitoreo en una planta inactiva.");
        }
        LocalDateTime fecha = registro.getFechaHoraRegistro();
        if (fecha == null) {
            throw new RuntimeException("La fecha del registro no puede ser nula.");
        }
        if (fecha.isBefore(fechaHoraIniciacion)) {
            throw new RuntimeException("El registro es anterior a la puesta en marcha.");
        }
        if (estuvoInactivaEn(fecha)) {
            throw new RuntimeException("La planta estaba inactiva en esa fecha.");
        }
        for (RegistroMonitoreo reg : registrosMonitoreo) {
            if (reg.getFechaHoraRegistro().equals(fecha)) {
                throw new RuntimeException("Ya existe un registro en esa fecha y hora.");
            }
        }
        registrosMonitoreo.add(registro);
    }
    

	public int limpiarRegistrosSinDatos() {
	    int eliminados = 0;
	    
	    Iterator<RegistroMonitoreo> iterador = registrosMonitoreo.iterator();
	    
	    while (iterador.hasNext()) {
	        RegistroMonitoreo registro = iterador.next();
	    
	        if (registro.getEnergiaGenerada() == 0 && registro.getCantIncidencias() == 0) {
	            iterador.remove();
	            eliminados++;
	        }
	    }
	    return eliminados;
	}

    public LinkedList<RegistroMonitoreo> getRegistrosMonitoreo() {
        return new LinkedList<>(registrosMonitoreo);
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
        if (registro == null) {
            throw new RuntimeException("El registro no puede ser nulo.");
        }
        return registro.getEnergiaGenerada() >= capacidadMinimaCuota();
    }
    
    public double totalGenerado() {
    	double total = 0.0;

    	for(RegistroMonitoreo registro: registrosMonitoreo) {
    		total += registro.getEnergiaGenerada();
    	}
    	return total;
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
        return String.format("%s (%s): %s", 
        		nombre,
        		estado, 
        		fechaHoraIniciacion.format(formato));
    }
}
