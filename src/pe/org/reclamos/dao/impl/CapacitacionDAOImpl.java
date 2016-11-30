package pe.org.reclamos.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.CapacitacionDAO;
import pe.org.reclamos.entidad.Capacitacion;
import pe.org.reclamos.entidad.CapacitacionItem;

@Repository
public class CapacitacionDAOImpl extends HibernateDaoSupport implements CapacitacionDAO {

	private static final Logger logger = Logger.getLogger( CapacitacionDAOImpl.class );
	
	@Autowired
	public CapacitacionDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	public void grabar(Capacitacion capacitacion) {
		this.getHibernateTemplate().saveOrUpdate( capacitacion );
	}

	@Override
	public List<Capacitacion> listar(Capacitacion capacitacion) {
		return null;
	}

	@Override
	public Capacitacion obtener(Long idCapacitacion) {
		try {
			return (Capacitacion) this.getHibernateTemplate().find("from Capacitacion where idCapacitacion = ? ", idCapacitacion).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CapacitacionItem obtenerCapacitacionItem(Long idCapacitacion, Long idDetalleFactura) {
		try {
			return (CapacitacionItem) this.getHibernateTemplate().find("from CapacitacionItem where capacitacion.idCapacitacion = ? and detallefactura.idDetalleFactura= ? ", idCapacitacion.intValue(), idDetalleFactura.intValue()  ).get(0);
		} catch (Exception e) {
			logger.error( e.getMessage() );
			return null;
		}
	}

	@Override
	public void registrarCapacitacionItem(CapacitacionItem capacitacionItem) {
		this.getHibernateTemplate().saveOrUpdate( capacitacionItem );
	}

	@Override
	public CapacitacionItem obtenerCapacitacionItem(Long idCapacitacionItem) {
		try {
			return (CapacitacionItem) this.getHibernateTemplate().find("from CapacitacionItem where idCapacitacionItem = ? ", idCapacitacionItem.intValue() ).get(0);
		} catch (Exception e) {
			logger.error( e.getMessage() );
			return null;
		}
	}

	@Override
	public Capacitacion obtenerPorFactura(Integer idFactura) {
		try {
			return (Capacitacion) this.getHibernateTemplate().find("from Capacitacion where factura.idFactura = ? ", new Long(idFactura) ).get(0);
		} catch (Exception e) {
			logger.error( e.getMessage() );
			return null;
		}
	}

}
