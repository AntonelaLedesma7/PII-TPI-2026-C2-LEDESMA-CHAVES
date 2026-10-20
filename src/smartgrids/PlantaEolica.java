package smartgrids;

import java.time.LocalDateTime;

public class PlantaEolica extends PlantaRenovable {
	private int cantidadAerogeneradores;
	private final double alturaTorres;
	private final double velocidadMinNecesaria;
	
	
	public PlantaEolica(String nombre, String direccion, double capacidadMax, LocalDateTime fechaHoraIniciacion,
			String fuentePrimaria, double eficienciaPromedio, int cantidadAerogeneradores, double alturaTorres,
			double velocidadMinNecesaria) {
		super(nombre, direccion, capacidadMax, fechaHoraIniciacion, fuentePrimaria, eficienciaPromedio);
		if(cantidadAerogeneradores <= 0) {
			throw new RuntimeException("La cantidad de aerogeneradores debe ser mayor a cero.");
		}
		if(alturaTorres <= 0) {
			throw new RuntimeException("La altura de las torres debe ser mayor a cero.");
		}
		if(velocidadMinNecesaria < 0) {
			throw new RuntimeException("La velocidad mínima necesaria no puede ser negativa.");
		}
		this.cantidadAerogeneradores = cantidadAerogeneradores;
		this.alturaTorres = alturaTorres;
		this.velocidadMinNecesaria = velocidadMinNecesaria;
	}


	public int getCantidadAerogeneradores() {
		return cantidadAerogeneradores;
	}


	public void setCantidadAerogeneradores(int cantidadAerogeneradores) {
		this.cantidadAerogeneradores = cantidadAerogeneradores;
	}


	public double getAlturaTorres() {
		return alturaTorres;
	}


	public double getVelocidadMinNecesaria() {
		return velocidadMinNecesaria;
	}
	

	public String reporteHorasSinVelocidadMinima() {
	    boolean[] tieneRegistros = new boolean[24];
	    boolean[] alcanzoMinima = new boolean[24];
	    double[] velocidadMaxima = new double[24];
	    for (RegistroMonitoreo registro : getRegistrosMonitoreo()) {
	        RegistroEolico registroEolico = (RegistroEolico) registro;
	        int hora = registroEolico.getFechaHoraRegistro().getHour();
	        double velocidad = registroEolico.getVelocidadVientoPromedio();
	        tieneRegistros[hora] = true;
	        if (velocidad >= velocidadMinNecesaria) {
	            alcanzoMinima[hora] = true;
	        }
	        if (velocidad > velocidadMaxima[hora]) {
	            velocidadMaxima[hora] = velocidad;
	        }
	    }
	    StringBuilder reporte = new StringBuilder();
	    reporte.append("- ")
	    		.append(getNombre())
	    		.append(": ")
	    		.append(velocidadMinNecesaria)
	    		.append(" m/s\n");
	    for (int hora = 0; hora < 24; hora++) {
	        if (tieneRegistros[hora] && !alcanzoMinima[hora]) {
	            reporte.append(
	                String.format("       %02d: %s m/s%n",
	                    hora, velocidadMaxima[hora])
	            );
	        }
	    }
	    return reporte.toString();
	}

}
