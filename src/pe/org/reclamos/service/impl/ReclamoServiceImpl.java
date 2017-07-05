package pe.org.reclamos.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.org.reclamos.dao.ReclamoDAO;
import pe.org.reclamos.entidad.ItemsReclamo;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.rest.bean.ReporteDataBean;
import pe.org.reclamos.service.ReclamoService;

@Service
public class ReclamoServiceImpl implements ReclamoService {

	private static final Logger logger = Logger.getLogger(ReclamoServiceImpl.class );
	
	@Autowired
	private ReclamoDAO reclamoDAO;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void registrar(Reclamo reclamo) throws Exception {
		final String METHODNAME = "registrar - ";
		logger.debug(METHODNAME + "INI" );
		
		//validar que no exista un reclamo con esa factura 		
		if(reclamo.getFactura() == null ||  reclamo.getFactura().getIdFactura() == null ){
			throw new Exception("No se a recibido el numero de la facura");
		}
		
		if( reclamo.getIdReclamo() == null || reclamo.getIdReclamo() < 1){
			Reclamo rec = new Reclamo();
			rec.setFactura( reclamo.getFactura() );
			List<Reclamo> lista = reclamoDAO.buscar( rec );
			if( lista.size() > 0){
				throw new Exception("Ya existe un reclamo con la factura indicada");
			}
		}
		
		//identificar que es un reclamo nuevo y no un update
		if(reclamo.getFecReclamo()==null){
			reclamo.setFecReclamo(new Date());
			reclamo.setCreatedAt( new Date() ); // TODO: hacer mediante aspecto
			Calendar c = Calendar.getInstance();
			c.setTime( reclamo.getFecReclamo() );
			c.add(Calendar.DATE, 30);  
			
			reclamo.setVencimiento( c.getTime() );
			
		}
		
		reclamoDAO.registrar(reclamo);
		//registrar itemdetalle
		if( reclamo.getItemsReclamos() != null){
			
			//eliminar items de reclamo
			reclamoDAO.eliminarItems( reclamo.getIdReclamo() );	
			for(ItemsReclamo item : reclamo.getItemsReclamos()){
				logger.debug( METHODNAME + "por insertar item ");
				item.setEstado( 1 );
				item.setReclamo( reclamo );
				reclamoDAO.registrar( item );		
			}
		}
		logger.debug(METHODNAME + "FIN" );		
	}

	@Override
	public Reclamo obtener(Long reclamo) {
		return reclamoDAO.obtener(reclamo);
	}

	@Override
	public List<Reclamo> buscar(Reclamo reclamo) {
		logger.debug( "buscar");
		logger.debug( "reclamo " + reclamo);
		List<Reclamo> lista = reclamoDAO.buscar(reclamo); 
		logger.debug( "lista " + lista );
		return  lista;
	}

	@Override
	public void eliminar(Long reclamo) {
		reclamoDAO.eliminar(reclamo);
	}

	@Override
	public void actualizar(Reclamo reclamo) {
		
		reclamo.setUpdatedAt( new Date() ); // TODO: hacer mediante aspecto
		reclamoDAO.registrar(reclamo);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void grabarNoIndemnizados(String[] idReclamos) {
		for( String rec : idReclamos){
			reclamoDAO.grabarNoIndemnizado( new Long(rec) );
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void registrarIndemnizacion(Reclamo rec) {
		reclamoDAO.registrar( rec );
		//registrar 
		rec.getIndemnizacion().setReclamo( rec );
		rec.getIndemnizacion().setEstado(1);
		rec.getIndemnizacion().setCreatedAt( new Date());
		rec.getIndemnizacion().setFechaIndemnizacion( new Date());
		reclamoDAO.registrar(rec.getIndemnizacion());
	}

	@Override
	public Reclamo obtenerPorIdFactura(Long idFactura) {
		return reclamoDAO.obtenerPorIdFactura(idFactura);
	}

	@Override
	public ItemsReclamo obtenerItemReclamo(Long idReclamo) {
		return reclamoDAO.obtenerItemReclamo(idReclamo);
	}

	@Override
	public List<ReporteDataBean> obtenerReclamosPorMesAtendidosAnioActual() {
		final String METHODNAME = "obtenerReclamosPorMesAtendidosAnioActual - ";
		logger.debug(METHODNAME + "INI");
		List<ReporteDataBean> reclamos = reclamoDAO.obtenerReclamosPorMesAtendidosAnioActual();
		logger.debug(METHODNAME + "FIN");
		return reclamos;
	}

	@Override
	public List<ReporteDataBean> obtenerReclamosMasRepetidosEnElAnio() {
		final String METHODNAME = "obtenerReclamosMasRepetidosEnElAnio - ";
		logger.debug(METHODNAME + "INI");
		List<ReporteDataBean> reclamos = reclamoDAO.obtenerReclamosMasRepetidosEnElAnio();
		logger.debug(METHODNAME + "FIN");
		return reclamos;
	}
	
	@Override
	public List<ReporteDataBean> obtenerReclamosPorMesNoAtendidosEnElAnio() {
		final String METHODNAME = "obtenerReclamosPorMesNoAtendidosEnElAnio - ";
		logger.debug(METHODNAME + "INI");
		List<ReporteDataBean> reclamos = reclamoDAO.obtenerReclamosPorMesNoAtendidosEnElAnio();
		logger.debug(METHODNAME + "FIN");
		return reclamos;
	}

	@Override
	public List<ReporteDataBean> obtenerReclamosPorEstadoMesActual() {
		final String METHODNAME = "obtenerReclamosPorEstadoMesActual - ";
		logger.debug(METHODNAME + "INI");
		List<ReporteDataBean> reclamos = reclamoDAO.obtenerReclamosPorEstadoMesActual();
		logger.debug(METHODNAME + "FIN");
		return reclamos;
	}

}
