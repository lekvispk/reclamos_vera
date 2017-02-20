package pe.org.reclamos.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.NotificacionesDAO;
import pe.org.reclamos.entidad.Notificacion;

@Repository
public class NotificacionesDAOImpl extends HibernateDaoSupport implements NotificacionesDAO {

	@Autowired
	public NotificacionesDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	public Notificacion obtenernotificacionPorUsuario(Integer idUsuario) {
		try {
			return (Notificacion) this.getHibernateTemplate().find(" from Notificacion where cliente.idCliente = ? ", new Long(idUsuario) ).get(0);
		} catch (Exception e) {
			logger.error( e.getMessage() );
			return new Notificacion();
		}
	}

}
