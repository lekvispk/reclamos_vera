package pe.org.reclamos.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.org.reclamos.dao.ReclamoDAO;
import pe.org.reclamos.entidad.Indemnizacion;
import pe.org.reclamos.entidad.ItemsReclamo;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.rest.bean.ReporteDataBean;
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
			return (Reclamo) this.getHibernateTemplate().find("from Reclamo u left join fetch u.itemsReclamos where u.idReclamo = ? ", reclamo).get(0);
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
			if( !StringUtils.isEmpty( reclamo.getRespuesta() ) ){
				criteria.add( Restrictions.eq("respuesta", reclamo.getRespuesta() ) );
			}else{
				//para que funcione en la pantalla de lista Evaluaacion
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
				if( !StringUtils.isEmpty( reclamo.getFactura().getNumero() ) ){
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ReporteDataBean> obtenerReclamosPorMesAtendidosAnioActual() {
		final String METHODNAME = "obtenerReclamosPorMesAtendidosAnioActual - ";
		logger.debug(METHODNAME + "INI");
		
		List<ReporteDataBean> lista = null;
		List<Object[]> resultList = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					//TODO aun no hay data con estado 2 
					StringBuilder sql = new StringBuilder();
					sql.append( " SELECT MONTH(fecReclamo) AS MES, COUNT(*) AS Reclamos " );
					sql.append( " FROM reclamo " );
					sql.append( " WHERE " );
					sql.append( " estado=2 AND respuesta IS not NULL AND  " );
					sql.append( " YEAR(fecReclamo) = YEAR(NOW()) " );
					sql.append( " GROUP BY MONTH(fecReclamo) " );
					
					SQLQuery query = session.createSQLQuery(sql.toString());
					List<Object[]> list = query.list();
					return list;
				}
				
		});

		logger.debug(METHODNAME + "resultList="+resultList.size());
		lista = new ArrayList<ReporteDataBean>();
		for(Object[] o : resultList){
			lista.add( new ReporteDataBean( Utiles.nombreCortoMes(o[0]) , o[1].toString() ));
		}
		logger.debug(METHODNAME + "lista="+lista.size());
		
		logger.debug(METHODNAME + "FIN");
		return lista;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ReporteDataBean> obtenerReclamosMasRepetidosEnElAnio() {
		final String METHODNAME = "obtenerReclamosMasRepetidosEnElAnio - ";
		logger.debug(METHODNAME + "INI");
		
		List<ReporteDataBean> lista = null;
		List<Object[]> resultList = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					
					StringBuilder sql = new StringBuilder();
					sql.append( " SELECT p.descripcion,COUNT(*)  " );
					sql.append( " FROM reclamo r " );
					sql.append( " INNER JOIN  items_reclamo ir ON r.`idReclamo` = ir.idReclamo " );
					sql.append( " INNER JOIN detallefactura df ON df.`idDetalleFactura` = ir.idDetalleFactura " );
					sql.append( " INNER JOIN producto p ON df.`idProducto` = p.idProducto  " );
					sql.append( " WHERE  YEAR(fecReclamo) = YEAR(NOW())  " );
					sql.append( " GROUP BY p.descripcion " );
					sql.append( " LIMIT 5 " );
					SQLQuery query = session.createSQLQuery(sql.toString());
					List<Object[]> list = query.list();
					return list;
				}
				
		});

		logger.debug(METHODNAME + "resultList="+resultList.size());
		lista = new ArrayList<ReporteDataBean>();
		for(Object[] o : resultList){
			lista.add( new ReporteDataBean( o[0].toString() , o[1].toString() ));
		}
		logger.debug(METHODNAME + "lista="+lista.size());
		
		logger.debug(METHODNAME + "FIN");
		return lista;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ReporteDataBean> obtenerReclamosPorMesNoAtendidosEnElAnio() {
		final String METHODNAME = "obtenerReclamosPorMesNoAtendidosEnElAnio - ";
		logger.debug(METHODNAME + "INI");
		
		List<ReporteDataBean> lista = null;
		List<Object[]> resultList = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					
					StringBuilder sql = new StringBuilder();
					sql.append( " SELECT MONTH(fecReclamo) AS MES, COUNT(*) AS Reclamos " );
					sql.append( " FROM reclamo " );
					sql.append( " WHERE " );
					sql.append( " estado=1 AND  respuesta IS NULL AND " );
					sql.append( " YEAR(fecReclamo) = YEAR(NOW()) " );
					sql.append( " GROUP BY MONTH(fecReclamo) " );
					
					SQLQuery query = session.createSQLQuery(sql.toString());
					List<Object[]> list = query.list();
					return list;
				}
				
		});

		logger.debug(METHODNAME + "resultList="+resultList.size());
		lista = new ArrayList<ReporteDataBean>();
		for(Object[] o : resultList){
			lista.add( new ReporteDataBean( o[0].toString() , o[1].toString() ));
		}
		logger.debug(METHODNAME + "lista="+lista.size());
		
		logger.debug(METHODNAME + "FIN");
		return lista;
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ReporteDataBean> obtenerReclamosPorEstadoMesActual() {
		final String METHODNAME = "obtenerReclamosPorEstadoMesActual - ";
		logger.debug(METHODNAME + "INI");
		
		List<ReporteDataBean> lista = null;
		List<Object[]> resultList = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					
					StringBuilder sql = new StringBuilder();
					sql.append( " SELECT COUNT(*) as cantidad, estado, respuesta  " );
					sql.append( " FROM reclamo  " );
					sql.append( " WHERE  MONTH(fecReclamo) = MONTH(NOW()) " );
					sql.append( " GROUP BY estado, respuesta " );
					SQLQuery query = session.createSQLQuery(sql.toString());
					List<Object[]> list = query.list();
					return list;
				}
				
		});

		logger.debug(METHODNAME + "resultList="+resultList.size());
		lista = new ArrayList<ReporteDataBean>();
		ReporteDataBean evaluado = new ReporteDataBean( "En estado Evaluado" , "" );
		Integer cantidad = 0 ;
		for(Object[] o : resultList){
			if( Utiles.nullToZero(o[1]) == 1 ){
				if( Utiles.nullToBlank(o[2]).equals("") ){
					lista.add( new ReporteDataBean( "En estado Registrado" , o[0].toString() ));
				}else{
					cantidad += Integer.valueOf( o[0].toString() );
				}
			}
			if( Utiles.nullToZero(o[1]) == 2 ){
				if( Utiles.nullToBlank(o[2]).equals("Aceptado") ){
					lista.add( new ReporteDataBean( "En estado Aprovado" , o[0].toString() ));
				}else{
					lista.add( new ReporteDataBean( "En estado Rechazado" , o[0].toString() ));
				}
			}
		}
		evaluado.setValue( cantidad + "" );
		lista.add( evaluado );
		logger.debug(METHODNAME + "lista="+lista.size());
		logger.debug(METHODNAME + "FIN");
		return lista;
	}

	@Override
	public Reclamo obtenerPorNumeroDeFactura(String numero) {
		try {
			List list = this.getHibernateTemplate().find("from Reclamo u where factura.numero = ? ", numero);
			if (list.size() != 0) {
				return (Reclamo) list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.debug( "Error: " + e.getMessage() );
			return null;
		}
	}

}
