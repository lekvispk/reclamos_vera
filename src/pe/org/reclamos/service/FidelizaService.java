package pe.org.reclamos.service;

import pe.org.reclamos.entidad.Fideliza;

public interface FidelizaService {

	public void registrarFidelizacion(Fideliza fideliza);
	public Fideliza obtenerFidelizacionPorReclamo(Long idReclamo);
	
}
