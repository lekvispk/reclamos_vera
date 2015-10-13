package pe.org.reclamos.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.ClienteDAO;
import pe.org.reclamos.entidad.Cliente;
import pe.org.reclamos.utiles.Utiles;

@Repository
public class ClienteDAOImpl extends HibernateDaoSupport implements ClienteDAO {

	@Autowired
	public ClienteDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	public void registrar(Cliente cliente) {
		this.getHibernateTemplate().saveOrUpdate( cliente );
	}

	@Override
	public Cliente obtener(Long cliente) {
		try {
			return (Cliente) this.getHibernateTemplate().find("from Cliente u where idCliente = ? ", cliente).get(0);
		} catch (Exception e) {
			logger.debug( e.getMessage() );
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> buscar(Cliente cliente) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Cliente.class);
		logger.debug(" buscar clientes() ");
				
		if(cliente !=null){
			
			if( cliente.getEstado()>0)
				criteria.add( Restrictions.eq("estado", cliente.getEstado() ) );
			else
				criteria.add( Restrictions.gt("estado", 0 ) );
			
			logger.debug(cliente.getIdCliente());
			if( cliente.getIdCliente()!=null && cliente.getIdCliente()>0){
				criteria.add( Restrictions.eq("idCliente", cliente.getIdCliente() ) );
			}
			
			if( !Utiles.nullToBlank( cliente.getRucCliente() ).equals("")){
				criteria.add( Restrictions.ilike("rucCliente", cliente.getRucCliente() ,MatchMode.ANYWHERE ) );
			}
			/*if( exp.getEntidad()!= null && !Utiles.nullToBlank( exp.getEntidad().getIdEntidad() ).equals("-1")){
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
	public void eliminar(Long cliente) {
		logger.debug("eliminar " + cliente );
		this.getHibernateTemplate().bulkUpdate("update Cliente u set estado=0 where idCliente = ?  ", cliente);
	}

}
