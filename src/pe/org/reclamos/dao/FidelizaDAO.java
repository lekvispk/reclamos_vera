package pe.org.reclamos.dao;

import pe.org.reclamos.entidad.Fideliza;

public interface FidelizaDAO {

	public void registrarFidelizacion(Fideliza fideliza);
	public Fideliza obtenerFidelizacionPorReclamo(Long idReclamo);
	
}
