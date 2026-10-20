package smartgrids;

import java.time.LocalDate;
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

	private Planta validarYObtenerPlanta(String nombre) {
		if(nombre == null || nombre.trim().isEmpty()) {
			throw new RuntimeException("El nombre de la planta no puede ser nulo ni estar vacío.");
		}
		
		Planta planta = this.plantas.get(nombre);
		
		if(planta == null) {
			throw new RuntimeException("No existe ninguna planta registrada con el nombre: " + nombre);
		}
		return planta;
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
		Planta planta = validarYObtenerPlanta(nombre);
		return planta.capacidadMinimaCuota();
	}

	@Override
	public void activarPlanta(String nombre, String fechaHora) {
		Planta planta = validarYObtenerPlanta(nombre);
		LocalDateTime fecha = parsearFechaHora(fechaHora);
		planta.activar(fecha);
	}

	@Override
	public void desactivarPlanta(String nombre, String fechaHora) {
		Planta planta = validarYObtenerPlanta(nombre);
		LocalDateTime fecha = parsearFechaHora(fechaHora);
		planta.desactivar(fecha);
	}

	@Override
	public boolean estaActiva(String nombre) {
		return validarYObtenerPlanta(nombre).estaActiva();
	}
	
	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias) {
		Planta planta = validarYObtenerPlanta(nombre);
		
		if(planta instanceof PlantaEolica || planta instanceof PlantaTermica) {
			throw new RuntimeException("La Planta" + nombre + "requiere un registro específico de su tipo.");
		}
		
		LocalDateTime fechaHoraRegistro = parsearFechaHora(fechaHora);
		RegistroMonitoreo registro = new RegistroMonitoreo(
				nombre,
				fechaHoraRegistro,
				energiaGenerada,
				incidencias);
		
		planta.agregarRegistroMonitoreo(registro);
	}

	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
			double velocidadViento) {
		Planta planta = validarYObtenerPlanta(nombre);
		
		if(!(planta instanceof PlantaEolica)) {
			throw new RuntimeException("No se puede registrar velocidad del viento en una planta que no es eólica: " + nombre);
		}
		
		LocalDateTime fechaHoraRegistro = parsearFechaHora(fechaHora);
		RegistroEolico registro = new RegistroEolico(
				nombre,
				fechaHoraRegistro,
				energiaGenerada,
				incidencias,
				velocidadViento);

		planta.agregarRegistroMonitoreo(registro);
	}

	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
			double co2Emitido, double combustibleUtilizado) {
		Planta planta = validarYObtenerPlanta(nombre);
		
		if(!(planta instanceof PlantaTermica)) {
			throw new RuntimeException("No se pueden registrar emisiones de Co2 y combustible en una planta que no es térmica: " + nombre);
		}
		
		LocalDateTime fechaHoraRegistro = parsearFechaHora(fechaHora);
		RegistroTermico registro = new RegistroTermico(
				nombre,
				fechaHoraRegistro,
				energiaGenerada,
				incidencias,
				co2Emitido,
				combustibleUtilizado);
		
		planta.agregarRegistroMonitoreo(registro);
	}

	@Override
	public double totalGenerado(String nombre) {
		Planta planta = validarYObtenerPlanta(nombre);
		return planta.totalGenerado();
	}
	
	@Override
	public int[] incidenciasPorHora(String nombre) {
	    Planta planta = validarYObtenerPlanta(nombre);
	    int[] arregloIncidencias = new int[24];
	    for (RegistroMonitoreo registro : planta.getRegistrosMonitoreo()) {
	        int cantIncidencias = registro.getCantIncidencias();
	        int hora = registro.getFechaHoraRegistro().getHour();
	        arregloIncidencias[hora] += cantIncidencias;
	    }
	    return arregloIncidencias;
	}

	@Override
	public int[] histogramaCumplimiento(String nombre, String fechaDesde, String fechaHasta) {
		Planta planta = validarYObtenerPlanta(nombre);
		
		LocalDate desde;
		LocalDate hasta;
		
		try {
			desde = LocalDate.parse(fechaDesde);
			hasta = LocalDate.parse(fechaHasta);
		} catch (DateTimeParseException | NullPointerException e) {
			throw new RuntimeException("El formato de las fechas debe ser aaaa-MM-dd", e);
		}
		if(desde.isAfter(hasta)) {
			throw new RuntimeException("La fecha inicial (" + fechaDesde + ") no puede ser posterior a la fecha final (" + fechaHasta + ").");
		}
		
		int[] histograma = new int[24];
		
		for(RegistroMonitoreo reg : planta.getRegistrosMonitoreo()) {
			LocalDate fechaReg = reg.getFechaHoraRegistro().toLocalDate();
			
			if(!fechaReg.isBefore(desde) && !fechaReg.isAfter(hasta)) {
				if(planta.cumplioCuota(reg)) {
					int hora = reg.getFechaHoraRegistro().getHour();
					histograma[hora]++;
				}
			}
		}
		return histograma;
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
		List<Planta> listaPlantas = new ArrayList<>(this.plantas.values());
		
		listaPlantas.sort((p1,p2) -> Double.compare(p2.totalGenerado(), p1.totalGenerado()));
		
		List<String> plantasOrdenadas = new ArrayList<>();
		for(Planta planta : listaPlantas) {
			plantasOrdenadas.add(planta.getNombre() + ": " + planta.totalGenerado());
		}
		return plantasOrdenadas;
	}

	@Override
	public List<String> plantasInactivasEn(String fechaHora) {
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("--- SISTEMA SMARTGRIDS ---\n");
		sb.append("Cantidad de plantas registradas: ").append(this.plantas.size()).append("\n");
		
		if (this.plantas.isEmpty()) {
			sb.append("No hay plantas registradas en la red.");
		} else {
			sb.append("Listado de plantas: \n");
			for(Planta planta: this.plantas.values()) {
				sb.append(" - ").append(planta.toString()).append("\n");
			}
		}
		
		return sb.toString();
	}

}
