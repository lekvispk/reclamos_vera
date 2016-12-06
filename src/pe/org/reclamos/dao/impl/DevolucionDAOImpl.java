package pe.org.reclamos.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.DevolucionDAO;
import pe.org.reclamos.entidad.Devolucion;

@Repository
public class DevolucionDAOImpl extends HibernateDaoSupport implements DevolucionDAO {

	@Autowired
	public DevolucionDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	public Devolucion obtenerPorReclamo(Integer idReclamo) {
		try {
			return (Devolucion) this.getHibernateTemplate().find("from Devolucion where reclamo.idReclamo = ? ", idReclamo).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Devolucion obtener(Integer idDevolucion) {
		try {
			return (Devolucion) this.getHibernateTemplate().find("from Devolucion where idDevolucion = ? ", idDevolucion).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void grabar(Devolucion devolucion) {
		this.getHibernateTemplate().saveOrUpdate( devolucion );
	}

}
