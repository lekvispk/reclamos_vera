package pe.org.reclamos.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.CapacitacionDAO;
import pe.org.reclamos.entidad.Capacitacion;
import pe.org.reclamos.entidad.CapacitacionItem;
import pe.org.reclamos.entidad.Capacitador;
import pe.org.reclamos.utiles.Utiles;

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
			return (Capacitacion) this.getHibernateTemplate().find("from Capacitacion where idCapacitacion = ? ", idCapacitacion.intValue() ).get(0);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Capacitacion> buscarCapacitaciones(Capacitacion capacitacion) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Capacitacion.class);
		logger.debug(" buscarCapacitaciones() ");
				
		if(capacitacion !=null){
			
			if( capacitacion.getIdCapacitacion() > 0 ){
				criteria.add( Restrictions.eq("idCapacitacion", capacitacion.getIdCapacitacion() ) );
			}
			
			if( capacitacion.getFechaCapacitacion() != null ){
				criteria.add( Restrictions.eq("fechaCapacitacion", capacitacion.getFechaCapacitacion() ) );
			}
			
			if( capacitacion.getFactura() != null ){
				DetachedCriteria criteria2 = criteria.createCriteria("factura");
				if( capacitacion.getFactura().getCliente() != null ){
					if( !Utiles.nullToBlank( capacitacion.getFactura().getCliente().getNomCliente() ).equals("") ){
						criteria2.add( Restrictions.ilike("cliente.nomCliente", capacitacion.getFactura().getCliente().getNomCliente() ,MatchMode.ANYWHERE ) );	
					}
				}
			}
			
		}
			
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Capacitador> listarCapacitador() {
		try {
			return (List<Capacitador>) this.getHibernateTemplate().find("from Capacitador where estado = 1 "  );
		} catch (Exception e) {
			logger.error( e.getMessage() );
			return null;
		}
	}

}
