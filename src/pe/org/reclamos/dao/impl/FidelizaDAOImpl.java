package pe.org.reclamos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.FidelizaDAO;
import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.entidad.Fideliza;
import pe.org.reclamos.utiles.Utiles;

@Repository
public class FidelizaDAOImpl extends HibernateDaoSupport implements FidelizaDAO {

	@Autowired
	public FidelizaDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	public void registrarFidelizacion(Fideliza fideliza) {
		this.getHibernateTemplate().saveOrUpdate( fideliza );
	}

	@Override
	public Fideliza obtenerFidelizacionPorReclamo(Long idReclamo) {
		try {
			return (Fideliza) this.getHibernateTemplate().find("from Fideliza u where reclamo.idReclamo = ? ", idReclamo).get(0);
		} catch (Exception e) {
			logger.debug( e.getMessage() );
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public Fideliza obtenerFidelizacionPorReclamo(Factura factura) {
		try {
			
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT DISTINCT f.* ");
			sql.append(" FROM fideliza f ");
			sql.append(" INNER JOIN  reclamo r ON f.idReclamo=r.idReclamo ");
			sql.append(" INNER JOIN cliente c ON c.idCliente = r.idCliente ");
			sql.append(" INNER JOIN factura fa ON fa.idCliente = c.idCliente ");
			sql.append(" WHERE f.estado=1 ");
			if( !Utiles.nullToBlank( factura.getCliente().getRucCliente() ).equals("") )
				sql.append(" AND c.rucCliente='"+factura.getCliente().getRucCliente()+"' ");
			if( !Utiles.nullToBlank( factura.getCliente().getNomCliente() ).equals("") )
				sql.append(" AND c.nomCliente='"+factura.getCliente().getNomCliente()+"' ");
			if( !Utiles.nullToBlank( factura.getNumero() ).equals("") )
				sql.append(" AND fa.numero = '"+factura.getNumero()+"' ");
			
			logger.debug("query " + sql.toString() );
			Session session =  getSession();
			Query query = session.createSQLQuery( sql.toString() )
						  .addEntity(Fideliza.class);
			//Fideliza fidel = (Fideliza) query.uniqueResult();
			List<Fideliza> result = query.list();
			return result.get(0);
		} catch (Exception e) {
			logger.error( e.getMessage() );
			return null;
		}
	}

	@Override
	public Fideliza obtenerFidelizacion(Long idFidelizacion) {
		try {
			return (Fideliza) this.getHibernateTemplate().find("from Fideliza u where idFideliza = ? ", idFidelizacion.intValue()).get(0);
		} catch (Exception e) {
			logger.debug( e.getMessage() );
			return null;
		}
	}

}
