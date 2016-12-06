package pe.org.reclamos.service;

import pe.org.reclamos.entidad.Devolucion;

public interface DevolucionService {

	public Devolucion obtenerPorReclamo(Integer idReclamo);
	public Devolucion obtener(Integer idDevolucion);
	public void grabar(Devolucion devolucion);
	
}
