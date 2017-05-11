package pe.org.reclamos.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.NotificacionesDAO;
import pe.org.reclamos.dao.ReclamoDAO;
import pe.org.reclamos.entidad.Notificacion;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.rest.bean.NotificacionRest;

@Repository
public class NotificacionesDAOImpl extends HibernateDaoSupport implements NotificacionesDAO {

	@Autowired
	private ReclamoDAO reclamoDAO;
	
	@Autowired
	public NotificacionesDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	public Notificacion obtenernotificacionPorUsuario(Integer idUsuario) {
		final String METHODNAME = "obtenernotificacionPorUsuario - ";
		logger.debug(METHODNAME + "INI" );
		try {
			return (Notificacion) this.getHibernateTemplate().find(" from Notificacion where cliente.idCliente = ? ", new Long(idUsuario) ).get(0);
		} catch (Exception e) {
			logger.error( e.getMessage() );
			return new Notificacion();
		}
	}
	
	@Override
	public NotificacionRest obtenerUltimaNotificacionPorUsuario(Integer idUsuario) {
		final String METHODNAME = "obtenerUltimaNotificacionPorUsuario - ";
		logger.debug(METHODNAME + "INI" );
		NotificacionRest n = null;
		try {
			
			Reclamo ultimo = reclamoDAO.obtenerUltimoReclamoRegistrado(idUsuario);
			if( ultimo != null ){
				//devolver la respuesta del ultimo reclamo 
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				n = new NotificacionRest();
				n.setAsuntoReclamo( ultimo.getAsunto() );
				n.setFechaReclamo( df.format( ultimo.getFecReclamo() ) );
				n.setRazonSocial(  ultimo.getFactura().getCliente().getNomCliente() ); 
				n.setRepresentante( ultimo.getFactura().getCliente().getRepresentante() );
				n.setRespuestaReclamo( StringUtils.trimToEmpty(ultimo.getRespuesta())  );
				
			}
		
		} catch (Exception e) {
			logger.error( e.getMessage() );
			return null;
		}
		return n;
	}

}
