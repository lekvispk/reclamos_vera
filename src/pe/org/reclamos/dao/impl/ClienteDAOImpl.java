package pe.org.reclamos.dao.impl;

import java.util.ArrayList;
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
import pe.org.reclamos.entidad.Factura;
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

	@Override
	public Cliente obtenerPorRUC(String ruc) {
		try {
			return (Cliente) this.getHibernateTemplate().find("from Cliente u where rucCliente = ? ", ruc).get(0);
		} catch (Exception e) {
			logger.error( "no se pudo obtner usuario: "+ e.getMessage() );
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> buscar(Cliente cliente) {
		final String METHODNAME = "buscar - ";
		DetachedCriteria criteria = DetachedCriteria.forClass(Cliente.class);
		logger.debug(" buscar clientes() ");
				
		if(cliente !=null){
			
			logger.debug(METHODNAME + cliente.toString() );
			
			if( cliente.getEstado()>0)
				criteria.add( Restrictions.eq("estado", cliente.getEstado() ) );
			else
				criteria.add( Restrictions.gt("estado", 0 ) );
			
			
			if( cliente.getIdCliente()!=null && cliente.getIdCliente()>0){
				criteria.add( Restrictions.eq("idCliente", cliente.getIdCliente() ) );
			}
			
			if( !Utiles.nullToBlank( cliente.getRucCliente() ).equals("")){
				criteria.add( Restrictions.ilike("rucCliente", cliente.getRucCliente() ,MatchMode.ANYWHERE ) );
			}
			
			if( !Utiles.nullToBlank( cliente.getNomCliente() ).equals("")){
				criteria.add( Restrictions.ilike("nomCliente", cliente.getNomCliente() ,MatchMode.ANYWHERE ) );
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> buscarClientesParaFidelizacion() {
		final String METHODNAME = "buscarClientesParaFidelizacion - ";
		logger.debug(METHODNAME + "INI");
		
		List<Cliente> resultado = new ArrayList<Cliente>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT c.* "); 
		sql.append(" FROM factura f "); 
		sql.append(" INNER JOIN reclamo r ON F.idFactura = r.idFactura AND r.estado=2 ");
		sql.append(" INNER JOIN cliente c ON c.idcliente = r.idCliente ");
		sql.append(" WHERE f.estado=1 ");
		
		logger.debug("query 1=" + sql.toString() );
		Session session =  getSession();
		Query query = session.createSQLQuery( sql.toString() )
							 .addEntity(Cliente.class);
		List<Cliente> clientes = query.list();
		
		for(Cliente c : clientes){
			
			sql = new StringBuilder();
			sql.append(" SELECT  idCliente "); 
			sql.append(" FROM factura f ");
			sql.append(" WHERE idCliente = :idCliente  AND f.estado=1 AND f.emision >= CURDATE() - INTERVAL 1 YEAR  AND f.emision <= CURDATE() ");
			//sql.append(" GROUP BY idCliente  ");
			sql.append(" HAVING SUM(f.monto) >= 5000 ");
			
			logger.debug("query cliente "+c.getIdCliente()+"=" + sql.toString() );
			List<Factura> facturas = session.createSQLQuery( sql.toString() )
			 		.setParameter("idCliente", c.getIdCliente() )
			 		.list();
			
			if( facturas.size() > 0){
				resultado.add(c);
			}
			
		}
		
		logger.debug(METHODNAME + "FIN");
		return resultado;
	}

	
}
