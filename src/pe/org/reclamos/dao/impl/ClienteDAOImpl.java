package pe.org.reclamos.dao.impl;

import java.util.List;

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
		this.getHibernateTemplate().saveOrUpdate( cliente.getPersona() );
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

	@Override
	public List<Cliente> buscarClientesParaFidelizacion() {

		StringBuilder sql = new StringBuilder();
		sql.append(" ");		
		sql.append(" SELECT c.idCliente,c.idPersona, c.nomCliente, c.rucCliente, c.representante,c.fecCliente, c.estado, SUM(f.monto) AS monto  FROM factura f "); 
		sql.append(" INNER JOIN reclamo r ON F.idFactura = r.idFactura AND r.estado=2 ");
		sql.append(" INNER JOIN cliente c ON c.idcliente = r.idCliente  ");
		sql.append(" WHERE f.estado=1  ");
		sql.append(" AND f.emision >= CURDATE() - INTERVAL 1 YEAR "); 
		sql.append(" AND f.emision <= CURDATE() ");
		//sql.append(" AND fidelizado=0 ");
		sql.append(" GROUP BY f.idCliente HAVING SUM(f.monto) >= 5000 "); 
		sql.append(" ");
		
		logger.debug("query " + sql.toString() );
		Session session =  getSession();
		Query query = session.createSQLQuery( sql.toString() )
		.addEntity(Cliente.class);
		//.setParameter("stockCode", "7277");
		List<Cliente> result = query.list();
		return result;
	}

}
