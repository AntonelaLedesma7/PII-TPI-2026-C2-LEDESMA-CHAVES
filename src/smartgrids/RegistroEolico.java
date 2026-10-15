package smartgrids;

import java.time.LocalDateTime;

public class RegistroEolico extends RegistroMonitoreo{
	private final double velocidadVientoPromedio;

	public RegistroEolico(String nombrePlanta, LocalDateTime fechaHoraRegistro, double energiaGenerada,
			int cantIncidencias, double velocidadVientoPromedio) {
		super(nombrePlanta, fechaHoraRegistro, energiaGenerada, cantIncidencias);
		
		if(velocidadVientoPromedio < 0) {
			throw new RuntimeException("La velocidad del viento promedio no puede ser negativa.");
		}
		
		this.velocidadVientoPromedio = velocidadVientoPromedio;
	}
	
	public double getVelocidadVientoPromedio() {
		return velocidadVientoPromedio;
	}
	
	@Override
	public String toString() {
		return String.format("%s | Viento: %.2f m/s", super.toString(), velocidadVientoPromedio);
	}
	
}
