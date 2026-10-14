package smartgrids;

import java.util.List;
import java.util.Map;

public class SmartGrids implements ISmartGrids {

	@Override
	public void registrarPlantaSolar(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
		

	}

	@Override
	public void registrarPlantaEolica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			int cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {

	}

	@Override
	public void registrarPlantaHidraulica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			int cantidadTurbinas, double capacidadEmbalse) {

	}

	@Override
	public void registrarPlantaTermica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String tipoCombustible, double objetivoEmisionesCo2PorHora,
			double capacidadAlmacenamientoCombustible) {

	}

	@Override
	public List<String> consultarPlantas() {
		return null;
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
