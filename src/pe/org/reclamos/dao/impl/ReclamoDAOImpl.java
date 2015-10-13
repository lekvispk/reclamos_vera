package pe.org.reclamos.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.ReclamoDAO;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.utiles.Utiles;

@Repository
public class ReclamoDAOImpl extends HibernateDaoSupport implements ReclamoDAO {

	@Autowired
	public ReclamoDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	public void registrar(Reclamo reclamo) {
		this.getHibernateTemplate().saveOrUpdate( reclamo );
	}

	@Override
	public Reclamo obtener(Long reclamo) {
		try {
			return (Reclamo) this.getHibernateTemplate().find("from Reclamo u where idReclamo = ? ", reclamo).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reclamo> buscar(Reclamo reclamo) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Reclamo.class);
		logger.debug(" buscar reclamo() ");
				
		if(reclamo !=null){
			logger.debug(" estado " + reclamo.getEstado());
			
			if( reclamo.getEstado()>0)
				criteria.add( Restrictions.eq("estado", reclamo.getEstado() ) );
			else
				criteria.add( Restrictions.gt("estado", 0 ) );
			
			if( reclamo.getIdReclamo() != null ){
				criteria.add( Restrictions.eq("idReclamo", reclamo.getIdReclamo() ) );
			}
			
			if( reclamo.getPrioridad() > 0 ){
				criteria.add( Restrictions.eq("prioridad", reclamo.getPrioridad() ) );
			}
			
			if( !Utiles.nullToBlank( reclamo.getFecReclamo() ).equals("")){
				criteria.add( Restrictions.ge("fecReclamo", reclamo.getFecReclamo() ) );
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
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void eliminar(Long reclamo) {
		logger.debug("eliminar " + reclamo );
		this.getHibernateTemplate().bulkUpdate("update Reclamo u set estado=0 where idReclamo = ?  ", reclamo );
	}

}
