package pe.org.reclamos.dao;

import pe.org.reclamos.entidad.Notificacion;

public interface NotificacionesDAO {

	public Notificacion obtenernotificacionPorUsuario( Integer idUsuario );
	
}
