package pe.org.reclamos.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.org.reclamos.dao.ReclamoDAO;
import pe.org.reclamos.entidad.Indemnizacion;
import pe.org.reclamos.entidad.ItemsReclamo;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.utiles.Utiles;

@Repository
public class ReclamoDAOImpl extends HibernateDaoSupport implements ReclamoDAO {

	@Autowired
	public ReclamoDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void registrar(Reclamo reclamo) {
		logger.debug( "insertar reclamo " );
		this.getHibernateTemplate().saveOrUpdate( reclamo );
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void registrar(ItemsReclamo item) {
		logger.debug(" insertar item de reclamo ");
		this.getHibernateTemplate().saveOrUpdate( item );
	}
	
	@Override
	public Reclamo obtener(Long reclamo) {
		try {
			return (Reclamo) this.getHibernateTemplate().find("from Reclamo u where idReclamo = ? ", reclamo).get(0);
		} catch (Exception e) {
			logger.debug( "Error: " + e.getMessage() );
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reclamo> buscar(Reclamo reclamo) {
		final String METHODNAME = "buscar - ";
		DetachedCriteria criteria = DetachedCriteria.forClass(Reclamo.class);
		logger.debug(METHODNAME+"INI");
				
		if(reclamo !=null){
			logger.debug(METHODNAME+" estado " + reclamo.getEstado());
			
			//TODO para pantalla de solucionar e imdenizar se deben validar que estados se mostraran.
			// cuando la pantalla puede ver dos estados especificos
			if( reclamo.getEstados().size() > 0 ){
				criteria.add( Restrictions.in("estado", reclamo.getEstados() ) );
			}else{

				if( reclamo.getEstado()!= null && reclamo.getEstado()>0)
					criteria.add( Restrictions.eq("estado", reclamo.getEstado() ) );
				else
					criteria.add( Restrictions.gt("estado", 0 ) );
					
			}
			if( !Utiles.nullToBlank( reclamo.getRespuesta() ).equals("") ){
				criteria.add( Restrictions.eq("respuesta", reclamo.getRespuesta() ) );
			}else{
				criteria.add( Restrictions.isNull("respuesta") );
			}
			
			if( reclamo.getIdReclamo() != null ){
				criteria.add( Restrictions.eq("idReclamo", reclamo.getIdReclamo() ) );
			}
			
			if( reclamo.getIdCliente() != null ){
				criteria.add( Restrictions.eq("idCliente", reclamo.getIdCliente() ) );
			}
			
			if( reclamo.getPrioridad()!=null &&  reclamo.getPrioridad() > 0 ){
				criteria.add( Restrictions.eq("prioridad", reclamo.getPrioridad() ) );
			}
			
			if( !Utiles.nullToBlank( reclamo.getFecReclamo() ).equals("")){
				criteria.add( Restrictions.ge("fecReclamo", reclamo.getFecReclamo() ) );
			}
			
			if( !Utiles.nullToBlank( reclamo.getVencimiento() ).equals("")){
				logger.debug(METHODNAME+" vencimiento " + reclamo.getVencimiento() );
				criteria.add( Restrictions.le("vencimiento", reclamo.getVencimiento() ) );
			}
			
			if( reclamo.getFactura() != null ){
				logger.debug(METHODNAME + "validar factura " );
				
				DetachedCriteria criteria1 = criteria.createCriteria("factura");
				
				if( reclamo.getFactura().getIdFactura() != null ){
					logger.debug(METHODNAME + "IDfactura " +  reclamo.getFactura().getIdFactura() );
					criteria1.add( Restrictions.eq("idFactura", reclamo.getFactura().getIdFactura() ) );
				}
				if( reclamo.getFactura().getNumero() != null ){
					logger.debug(METHODNAME + "numero de factura " +  reclamo.getFactura().getNumero() );
					criteria1.add( Restrictions.eq("numero", reclamo.getFactura().getNumero() ) );
				}
				if( reclamo.getFactura().getCliente() != null ){
					//factura.cliente.nomClient	
					if( !StringUtils.isEmpty( reclamo.getFactura().getCliente().getNomCliente()) ){
						logger.debug(METHODNAME + "nomCliente " + reclamo.getFactura().getCliente().getNomCliente() );
						DetachedCriteria criteria2 = criteria.createCriteria("factura.cliente");
						criteria2.add( Restrictions.ilike("nomCliente", reclamo.getFactura().getCliente().getNomCliente() , MatchMode.ANYWHERE ) );
					}
				}
			}
			
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
			//criteria.setFetchMode("capacitacions", FetchMode.JOIN);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			logger.debug(METHODNAME + "FIN");
			return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void eliminar(Long reclamo) {
		logger.debug("eliminar " + reclamo );
		this.getHibernateTemplate().bulkUpdate("update Reclamo u set estado=0 where idReclamo = ?  ", reclamo );
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void grabarNoIndemnizado(Long idReclamo) {
		this.getHibernateTemplate().bulkUpdate("update Reclamo set indemnizar='no', estado=2 where idReclamo = ?  ", idReclamo );
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void registrar(Indemnizacion indemnizacion) {
		this.getHibernateTemplate().saveOrUpdate( indemnizacion );
	}

	@Override
	public Reclamo obtenerPorIdFactura(Long idFactura) {
		try {
			return (Reclamo) this.getHibernateTemplate().find("from Reclamo u where factura.idFactura = ? ", idFactura).get(0);
		} catch (Exception e) {
			logger.debug( "Error: " + e.getMessage() );
			return null;
		}
	}

	@Override
	public ItemsReclamo obtenerItemReclamo(Long idReclamo) {
		try {
			ItemsReclamo item = (ItemsReclamo) this.getHibernateTemplate().find("from ItemsReclamo where idReclamo = ? ", idReclamo ).get(0);
			logger.debug( "item: " + item );
			return item ;
		} catch (Exception e) {
			logger.debug( "Error: " + e.getMessage() );
			return null;
		}
	}

	@Override
	public Reclamo obtenerUltimoReclamoRegistrado(Integer idUsuario) {
		try {
			return (Reclamo) this.getHibernateTemplate().find("from Reclamo u where idCliente = ? order by idReclamo desc", new Long(idUsuario) ).get(0);
		} catch (Exception e) {
			logger.debug( "Error: " + e.getMessage() );
			return null;
		}
	}

	@Override
	public void eliminarItems(Long idReclamo) {
		logger.debug("eliminar ItemsReclamo " + idReclamo );
		this.getHibernateTemplate().bulkUpdate("delete ItemsReclamo where idReclamo = ?  ", idReclamo );
	}

}
