package pe.org.reclamos.dao;

import pe.org.reclamos.entidad.Devolucion;

public interface DevolucionDAO {

	public Devolucion obtenerPorReclamo(Integer idReclamo);
	public Devolucion obtener(Integer idDevolucion);
	public void grabar(Devolucion devolucion);
	
}
