package pe.org.reclamos.dao.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.org.reclamos.dao.FacturaDAO;
import pe.org.reclamos.entidad.Detallefactura;
import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.utiles.Utiles;

@Repository
@Transactional
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
				criteria.add( Restrictions.ge("emision", factura.getEmision() ) );
			}
			
			if( !Utiles.nullToBlank( factura.getEmisionFin() ).equals("")){
				criteria.add( Restrictions.le("emision", factura.getEmisionFin() ) );
			}
			
			if( factura.getMonto()!=null && factura.getMonto().compareTo( BigDecimal.ZERO ) > 0 )
				criteria.add( Restrictions.gt("monto", factura.getMonto() ) );
			
			if(factura.getCliente()!=null ){
				
				logger.debug(" idCliente " + factura.getCliente().getIdCliente() );
				logger.debug(" nomCliente " + factura.getCliente().getNomCliente() );
				DetachedCriteria criteria2 = criteria.createCriteria("cliente");
		        //criteria2.add( Restrictions.like("name", "F%") );
		        
				if( factura.getCliente().getIdCliente()!=null)
					criteria.add( Restrictions.eq("cliente.idCliente", factura.getCliente().getIdCliente()  ) );
				
				if( !StringUtils.isEmpty( factura.getCliente().getRucCliente() ) )
					criteria2.add( Restrictions.eq("rucCliente", factura.getCliente().getRucCliente()  ) );
				
				if( !StringUtils.isEmpty( factura.getCliente().getNomCliente()) )
					criteria2.add( Restrictions.ilike("nomCliente", factura.getCliente().getNomCliente() ,MatchMode.ANYWHERE  ) );
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
			criteria.setFetchMode("capacitacions", FetchMode.JOIN);
			criteria.setFetchMode("detallefacturas", FetchMode.JOIN);
			
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void eliminar(Long reclamo) {
		logger.debug("eliminar " + reclamo );
		this.getHibernateTemplate().bulkUpdate("update Factura u set estado=0 where idFactura = ?  ", reclamo );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Detallefactura>  listarDetalleFactura(Detallefactura detalle) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Detallefactura.class);
		logger.debug(" buscar Factura() " + detalle);
				
		if(detalle !=null){
			logger.debug(" estado " + detalle.getEstado());
			
			if( detalle.getEstado()>0)
				criteria.add( Restrictions.eq("estado", detalle.getEstado() ) );
			else
				criteria.add( Restrictions.gt("estado", 0 ) );
			
			if( detalle.getFactura() != null  ){
				
				DetachedCriteria criteria2 = criteria.createCriteria("factura");
				
				if( detalle.getFactura().getIdFactura() != null && detalle.getFactura().getIdFactura() > 0 ){
					criteria2.add( Restrictions.eq("idFactura", detalle.getFactura().getIdFactura() ) );
				}
				if( !Utiles.nullToBlank( detalle.getFactura().getNumero()).equals("") ){
					criteria2.add( Restrictions.eq("numero", detalle.getFactura().getNumero() ) );
				}
			}
			
			//criteria.setFetchMode("producto", FetchMode.SELECT);
			//criteria.setFetchMode("propertyName", FetchMode.SELECT); 
		}
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Factura> buscarFacturasParaFidelizacion(Factura factura) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT f.* FROM factura f inner join reclamo r ");
		sql.append(" ON F.idFactura = r.idFactura and r.fidelizado=0 ");
		sql.append(" WHERE f.idCliente IN ( ");
		sql.append(" 	SELECT f.idCliente FROM factura f ");
		sql.append(" 	INNER JOIN reclamo r  ");
		sql.append(" 	ON F.idFactura = r.idFactura ");
		sql.append(" 	AND r.estado=2 ");
		//sql.append(" 	AND r.fidelizado=0 "); // no validare esto, xq si no ya no veria sus demas facturas
		sql.append(" 	WHERE f.estado=1 ");
		sql.append(" 	AND f.emision >= DATE('2016-10-01') ");
		sql.append(" 	AND f.emision <= DATE('2016-11-01') ");
		sql.append(" 	GROUP BY f.idCliente HAVING SUM(f.monto) >= 5000 ");
		sql.append(") AND f.estado=1 ");
		sql.append(" AND f.emision >= DATE('2016-10-01') ");
		sql.append(" AND f.emision <= DATE('2016-11-01') ");
		sql.append(" ");
		
		logger.debug("query " + sql.toString() );
		Session session =  getSession();
		Query query = session.createSQLQuery( sql.toString() )
		.addEntity(Factura.class);
		//.setParameter("stockCode", "7277");
		List<Factura> result = query.list();
		return result;
	}

	/**
	 * lista de facturas de un cliente </br>
	 * que sean del ultimo anio</br>
	 * que esten relacionadas con un reclamo Solucionado
	 * y que no hayan sido ya registrdas en fidelizacion
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Factura> buscarFacturasParaFidelizacionDeUnCliente( Factura factura) {

		Calendar cal = Calendar.getInstance();
		//Date today = cal.getTime();
		cal.add(Calendar.YEAR, -1); // to get previous year add -1
		Date lastYear = cal.getTime();

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT F.* FROM factura F ");
		sql.append(" 	INNER JOIN reclamo r  ");
		sql.append(" 	ON F.idFactura = r.idFactura AND r.estado=2 AND f.emision >= :emision ");
		sql.append(" 	LEFT OUTER JOIN fideliza fi ON r.idReclamo=fi.idReclamo ");
		sql.append(" 	WHERE idFideliza IS NULL and r.idCliente = :cliente ");
		sql.append(" ");
		
		Session session =  getSession();
		Query query = session.createSQLQuery( sql.toString() )
		.addEntity(Factura.class)
		.setParameter("emision", lastYear )
		.setParameter("cliente", factura.getCliente().getIdCliente() );
		List<Factura> result = query.list();
		return result;
		
		
	}

	@Override
	public List<Factura> buscarFacturasConFidelizacion( Factura factura) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT F.* FROM factura F ");
		sql.append(" INNER JOIN reclamo r ON F.idFactura = r.idFactura ");
		sql.append(" INNER JOIN fideliza fi ON r.idreclamo = fi.idReclamo ");
		//sql.append(" WHERE r.estado=1 ");
		//sql.append(" 	WHERE r.idCliente = :cliente ");
		sql.append(" ");
		
		Session session =  getSession();
		Query query = session.createSQLQuery( sql.toString() )
		.addEntity(Factura.class);
		//.setParameter("cliente", factura.getCliente().getIdCliente() );
		List<Factura> result = query.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Factura> buscarFacturasParaAplicarPromocion(Factura factura) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT f.*, fi.idFideliza FROM factura f ");
		sql.append(" INNER JOIN reclamo r ON r.idFactura = f.idFactura AND r.fidelizado=1 ");
		sql.append(" INNER JOIN fideliza fi ON fi.idReclamo = r.idReclamo ");
		//sql.append(" WHERE r.estado=1 ");
		//sql.append(" 	WHERE r.idCliente = :cliente ");
		sql.append(" ");
		
		Session session =  getSession();
		Query query = session.createSQLQuery( sql.toString() )
		.addEntity(Factura.class);
		//query.setResultTransformer(Transformers.aliasToBean(Factura.class));
		//.setParameter("cliente", factura.getCliente().getIdCliente() );
		List<Factura> result = query.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Factura> buscarFacturasConReclamos(Factura f) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT f.* FROM factura f ");
		sql.append(" INNER JOIN reclamo r ON r.idFactura = f.idFactura ");
		sql.append(" INNER JOIN cliente c ON c.idCliente = f.idCliente ");
		sql.append(" LEFT OUTER JOIN capacitacion cap ON cap.idFactura=f.idFactura ");
		sql.append(" WHERE f.estado=1 ");
		sql.append(" AND idCapacitacion is NULL ");
		if( f.getCliente() != null){
			if( !Utiles.nullToBlank( f.getCliente().getNomCliente() ).equals("") ){ 
				sql.append(" and c.nomCliente like '%"+ f.getCliente().getNomCliente() +"%'"); 
			}
			if( !Utiles.nullToBlank( f.getCliente().getRucCliente() ).equals("") ){ 
				sql.append(" and c.rucCliente = '"+ f.getCliente().getRucCliente() +"'"); 
			}
		}
		
		Session session =  getSession();
		Query query = session.createSQLQuery( sql.toString() )
		.addEntity(Factura.class);
		List<Factura> result = query.list();
		return result;
		/*
		 * DetachedCriteria criteria = DetachedCriteria.forClass(Factura.class);
		logger.debug(" buscar Factura() " + factura);
				
		if(factura !=null){
			
			if(factura.getCliente()!=null ){
				
				DetachedCriteria criteria2 = criteria.createCriteria("cliente");
		        
				if( factura.getCliente().getIdCliente()!=null)
					criteria.add( Restrictions.eq("cliente.idCliente", factura.getCliente().getIdCliente()  ) );
				
				if( !StringUtils.isEmpty( factura.getCliente().getRucCliente() ) )
					criteria2.add( Restrictions.eq("rucCliente", factura.getCliente().getRucCliente()  ) );
				
				if( !StringUtils.isEmpty( factura.getCliente().getNomCliente()) )
					criteria2.add( Restrictions.ilike("nomCliente", factura.getCliente().getNomCliente() ,MatchMode.ANYWHERE  ) );
			}
			
		if( StringUtils.isNotBlank( factura.getNumero() ) )
			criteria.add( Restrictions.eq("numero", factura.getNumero() ) );	
		}
		
		//criteria.add( Restrictions.gt("", "") );
	
		//criteria.addOrder( Order.desc("fechaIngreso") );
		//criteria.setFetchMode("capacitacions", FetchMode.JOIN);
		//criteria.setFetchMode("detallefacturas", FetchMode.JOIN);
		
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return this.getHibernateTemplate().findByCriteria(criteria);
		*/
	}

	@Override
	public Detallefactura obtenerDetalleFactura(Integer idDetalleFactura) {
		try {
			return (Detallefactura) this.getHibernateTemplate().find("from Detallefactura where idDetalleFactura = ? ", idDetalleFactura).get(0);
		} catch (Exception e) {
			logger.debug("Error: " + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Factura> buscarFacturasNoUsadas(Long idCliente) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT * FROM factura f1 WHERE f1.idFactura NOT IN ( ");
		sql.append(" SELECT f.idFactura FROM factura f INNER JOIN reclamo r ON f.idFactura = r.idFactura AND f.idCliente = :cliente ");
		sql.append(" ) AND f1.idCliente = :cliente  ");
		
		logger.debug("QUERY : " + sql.toString() );
		
		Session session =  getSession();
		Query query = session.createSQLQuery( sql.toString() )
		.addEntity(Factura.class)
		.setParameter("cliente", idCliente );
		List<Factura> result = query.list();
		return result;
	}

}
