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
	
	
}
