package pe.org.reclamos.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.FacturaDAO;
import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.utiles.Utiles;

@Repository
public class FacturaDAOImpl extends HibernateDaoSupport implements FacturaDAO {

	@Autowired
	public FacturaDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	public void registrar(Factura reclamo) {
		this.getHibernateTemplate().saveOrUpdate( reclamo );
	}

	@Override
	public Factura obtener(Long reclamo) {
		try {
			return (Factura) this.getHibernateTemplate().find("from Factura u where idFactura = ? ", reclamo).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Factura> buscar(Factura factura) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Factura.class);
		logger.debug(" buscar Factura() " + factura);
				
		if(factura !=null){
			logger.debug(" estado " + factura.getEstado());
			
			if( factura.getEstado()>0)
				criteria.add( Restrictions.eq("estado", factura.getEstado() ) );
			else
				criteria.add( Restrictions.gt("estado", 0 ) );
			
			if( factura.getIdFactura() != null && factura.getIdFactura() > 0 ){
				criteria.add( Restrictions.eq("idFactura", factura.getIdFactura() ) );
			}
			
			if( !Utiles.nullToBlank( factura.getEmision() ).equals("")){
				criteria.add( Restrictions.ge("fecFactura", factura.getEmision() ) );
			}
			
			if( !Utiles.nullToBlank( factura.getEmisionFin() ).equals("")){
				criteria.add( Restrictions.le("fecFacturaFin", factura.getEmisionFin() ) );
			}
			
			if( factura.getMonto()!=null && factura.getMonto().compareTo( BigDecimal.ZERO ) > 0 )
				criteria.add( Restrictions.gt("monto", factura.getMonto() ) );
			
			if(factura.getCliente()!=null && factura.getCliente().getIdCliente()!=null){
				criteria.add( Restrictions.eq("cliente.idCliente", factura.getCliente().getIdCliente()  ) );
			}
			
			if( StringUtils.isNotBlank( factura.getNumero() ) )
				criteria.add( Restrictions.eq("numero", factura.getNumero() ) );
			
			
			/*if( !Utiles.nullToBlank( exp.getNumeroExpediente() ).equals("")){
				criteria.add( Restrictions.ilike("numeroExpediente", exp.getNumeroExpediente() ,MatchMode.ANYWHERE ) );
			}
			if( exp.getEntidad()!= null && !Utiles.nullToBlank( exp.getEntidad().getIdEntidad() ).equals("-1")){
				logger.debug( " idEntidad "+ exp.getEntidad().getIdEntidad() );
				criteria.add( Restrictions.eq("entidad.idEntidad", exp.getEntidad().getIdEntidad() ) );
			}
		*/
			}
			//criteria.addOrder( Order.desc("fechaIngreso") );
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void eliminar(Long reclamo) {
		logger.debug("eliminar " + reclamo );
		this.getHibernateTemplate().bulkUpdate("update Factura u set estado=0 where idFactura = ?  ", reclamo );
	}

}
