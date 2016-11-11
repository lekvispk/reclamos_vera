package pe.org.reclamos.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.FidelizaDAO;
import pe.org.reclamos.entidad.Fideliza;

@Repository
public class FidelizaDAOImpl extends HibernateDaoSupport implements FidelizaDAO {

	@Autowired
	public FidelizaDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	public void registrarFidelizacion(Fideliza fideliza) {
		this.getHibernateTemplate().saveOrUpdate( fideliza );
	}

	@Override
	public Fideliza obtenerFidelizacionPorReclamo(Long idReclamo) {
		try {
			return (Fideliza) this.getHibernateTemplate().find("from Fideliza u where reclamo.idReclamo = ? ", idReclamo).get(0);
		} catch (Exception e) {
			logger.debug( e.getMessage() );
			return null;
		}
	}

}
