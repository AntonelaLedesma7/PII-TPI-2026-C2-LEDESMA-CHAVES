package smartgrids;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SmartGrids implements ISmartGrids {
	
	private final Map<String, Planta> plantas;
	
	public SmartGrids() {
		this.plantas = new LinkedHashMap<>();
	}
	
	private void validarNombreYUnicidad(String nombre) {
		if(nombre == null || nombre.trim().isEmpty()) {
			throw new RuntimeException("El nombre de la planta no puede ser nulo ni estar vacío.");
		}
		if(this.plantas.containsKey(nombre)) {
			throw new RuntimeException("Ya existe una planta registrada con el nombre: " + nombre);
		}
	}
	
	private LocalDateTime parsearFechaHora(String fechaHoraStr) {
		if(fechaHoraStr == null) {
			throw new RuntimeException("La fecha y hora no puede ser nula.");
		}
		try {
			return LocalDateTime.parse(fechaHoraStr);
		} catch (DateTimeParseException e){
			throw new RuntimeException("El formato de la fecha y hora es inválido: "+ fechaHoraStr, e);
		}
	}

	@Override
	public void registrarPlantaSolar(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
		
		validarNombreYUnicidad(nombre);
		LocalDateTime fechaHoraIniciacion = parsearFechaHora(fechaHoraPuestaEnFuncionamiento);
		
		PlantaSolar solar = new PlantaSolar(
				nombre,
				direccion,
				capacidadMaxima,
				fechaHoraIniciacion, 
				fuentePrimaria,
				eficienciaPromedio,
				superficieCubierta,
				cantidadPaneles,
				horasRadiacionDiaria);
		
		plantas.put(nombre, solar);

	}

	@Override
	public void registrarPlantaEolica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			int cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {
		
		validarNombreYUnicidad(nombre);
		LocalDateTime fechaHoraIniciacion = parsearFechaHora(fechaHoraPuestaEnFuncionamiento);
		
		PlantaEolica eolica = new PlantaEolica(
				nombre,
				direccion,
				capacidadMaxima,
				fechaHoraIniciacion,
				fuentePrimaria,
				eficienciaPromedio,
				cantidadAerogeneradores,
				alturaTorres,
				velocidadMinimaViento);
				
		plantas.put(nombre, eolica);
	}

	@Override
	public void registrarPlantaHidraulica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			int cantidadTurbinas, double capacidadEmbalse) {
	
		validarNombreYUnicidad(nombre);
		LocalDateTime fechaHoraIniciacion = parsearFechaHora(fechaHoraPuestaEnFuncionamiento);

		PlantaHidraulica hidraulica = new PlantaHidraulica(
				nombre,
				direccion,
				capacidadMaxima,
				fechaHoraIniciacion,
				fuentePrimaria,
				eficienciaPromedio,
				cantidadTurbinas,
				capacidadEmbalse);
		
		plantas.put(nombre, hidraulica);
	}
	

	@Override
	public void registrarPlantaTermica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String tipoCombustible, double objetivoEmisionesCo2PorHora,
			double capacidadAlmacenamientoCombustible) {
		
		validarNombreYUnicidad(nombre);
		LocalDateTime fechaHoraIniciacion = parsearFechaHora(fechaHoraPuestaEnFuncionamiento);
		
		PlantaTermica termica = new PlantaTermica(
				nombre,
				direccion,
				capacidadMaxima,
				fechaHoraIniciacion,
				tipoCombustible,
				objetivoEmisionesCo2PorHora,
				capacidadAlmacenamientoCombustible);
		
		plantas.put(nombre, termica);
	}

	@Override
	public List<String> consultarPlantas() {
		List<String> resultado = new ArrayList<>();
		for(Planta planta: plantas.values()) {
			resultado.add(planta.toString());
		}
		return resultado;
	}

	@Override
	public double capacidadMinimaCuota(String nombre) {
		return 0;
	}

	@Override
	public void activarPlanta(String nombre, String fechaHora) {

	}

	@Override
	public void desactivarPlanta(String nombre, String fechaHora) {

	}

	@Override
	public boolean estaActiva(String nombre) {
		return false;
	}

	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias) {

	}

	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
			double velocidadViento) {

	}

	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
			double co2Emitido, double combustibleUtilizado) {
}

	@Override
	public double totalGenerado(String nombre) {
		return 0;
	}

	@Override
	public int[] incidenciasPorHora(String nombre) {
		return null;
	}

	@Override
	public int[] histogramaCumplimiento(String nombre, String fechaDesde, String fechaHasta) {
		return null;
	}

	@Override
	public Map<String, Integer> plantasRenovablesPorFuente() {
		return null;
	}

	@Override
	public List<String> plantasQueNuncaCumplieronCuota() {
		return null;
	}

	@Override
	public String horasSinVelocidadMinimaPorPlantaEolica() {
		return null;
	}

	@Override
	public int limpiarMonitoreosSinDatos() {
		return 0;
	}

	@Override
	public List<String> plantasOrdenadasPorEnergiaGenerada() {
		return null;
	}

	@Override
	public List<String> plantasInactivasEn(String fechaHora) {
		return null;
	}

}
