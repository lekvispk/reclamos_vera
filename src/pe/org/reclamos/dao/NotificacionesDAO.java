package pe.org.reclamos.dao;

import pe.org.reclamos.entidad.Notificacion;
import pe.org.reclamos.rest.bean.NotificacionRest;

public interface NotificacionesDAO {

	public Notificacion obtenernotificacionPorUsuario( Integer idUsuario );
	public NotificacionRest obtenerUltimaNotificacionPorUsuario(Integer idUsuario);
	
}
