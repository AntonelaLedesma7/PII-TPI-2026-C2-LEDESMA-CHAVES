package smartgrids;

import java.time.LocalDateTime;

public class RegistroTermico extends RegistroMonitoreo {
	
	private final double co2Emitido;
	private final double combustibleUtilizado;

	public RegistroTermico(String nombrePlanta, LocalDateTime fechaHoraRegistro, double energiaGenerada,
			int cantIncidencias, double co2Emitido, double combustibleUtilizado) {
		super(nombrePlanta, fechaHoraRegistro, energiaGenerada, cantIncidencias);

		if(co2Emitido < 0) {
			throw new RuntimeException("La emisiones de CO2 no pueden ser neativas.");
		}
		if(combustibleUtilizado < 0) {
			throw new RuntimeException("El combustible utilizado no puede ser negativo.");
		}
		
		this.co2Emitido = co2Emitido;
		this.combustibleUtilizado = combustibleUtilizado;
	}
	
	public double getCo2Emitido() {
		return co2Emitido;
	}
	
	public double getCombustibleUtilizado() {
		return combustibleUtilizado;
	}
	
	@Override
	public String toString() {
		return String.format("%s | CO2: %.2f Kg | Combustible utilizado: %.2f L",
				super.toString(),
				co2Emitido,
				combustibleUtilizado);
	}
}
