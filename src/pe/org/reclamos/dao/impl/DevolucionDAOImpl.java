package pe.org.reclamos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.DevolucionDAO;
import pe.org.reclamos.entidad.DetalleDevolucion;
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

	@Override
	public Devolucion obtenerPorDetalleFactura(Integer idDetalleFactura) {
		logger.debug("obtenerPorDetalleFactura");
		try {
			 
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT d.* FROM detalleFactura df ");
			sql.append(" INNER JOIN factura f ON f.idFactura = df.idFactura ");
			sql.append(" INNER JOIN reclamo r ON f.idFactura = r.idFactura ");
			sql.append(" INNER JOIN devolucion d ON d.idReclamo=r.idReclamo ");
			sql.append(" WHERE df.idDetalleFactura = :idDetalleFactura ");
			//return (Devolucion) this.getHibernateTemplate().find( sql.toString(), idDetalleFactura).get(0);
			
			logger.debug("query " + sql.toString() );
			Session session =  getSession();
			Query query = session.createSQLQuery( sql.toString() )
			.addEntity(Devolucion.class)
			.setParameter("idDetalleFactura", idDetalleFactura );
			Devolucion result = (Devolucion) query.uniqueResult();
			return result;
			
		} catch (Exception e) {
			logger.debug("Error: " + e.getMessage());
			return null;
		}
	}

	@Override
	public void grabarDetalle(DetalleDevolucion dd) {
		this.getHibernateTemplate().saveOrUpdate( dd );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleDevolucion> listarDetalleDevolucion(Integer iddevolucion) {
		try {
			return this.getHibernateTemplate().find("from DetalleDevolucion where devolucion.idDevolucion = ? ", iddevolucion );
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
