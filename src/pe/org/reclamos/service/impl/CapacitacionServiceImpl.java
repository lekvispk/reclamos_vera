
package pe.org.reclamos.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.CapacitacionDAO;
import pe.org.reclamos.entidad.Capacitacion;
import pe.org.reclamos.entidad.CapacitacionItem;
import pe.org.reclamos.entidad.Capacitador;
import pe.org.reclamos.service.CapacitacionService;
import pe.org.reclamos.service.ReclamoService;

@Service
public class CapacitacionServiceImpl implements CapacitacionService {

	private static final Logger logger = Logger.getLogger( CapacitacionServiceImpl.class );
	
	@Autowired
	private CapacitacionDAO capacitacionDAO;
	
	@Autowired
	private ReclamoService reclamoService;
	
	@Override
	public void grabar(Capacitacion capacitacion) {
		if( capacitacion.getIdCapacitacion() > 0 ){
			capacitacion.setUpdatedAt( new Date() );
		}else{
			capacitacion.setEstado(1);
			capacitacion.setCreatedAt( new Date() );
		}
		capacitacionDAO.grabar(capacitacion);
	}

	@Override
	public List<Capacitacion> listar(Capacitacion capacitacion) {
		return capacitacionDAO.listar(capacitacion);
	}

	@Override
	public Capacitacion obtener(Long idCapacitacion) {
		return capacitacionDAO.obtener(idCapacitacion);
	}

	@Override
	public CapacitacionItem obtenerCapacitacionItem(Long idCapacitacion, Long idDetalleFactura) {
		return capacitacionDAO.obtenerCapacitacionItem( idCapacitacion,idDetalleFactura);
	}

	@Override
	public CapacitacionItem obtenerCapacitacionItem(Long idCapacitacionItem) {
		return capacitacionDAO.obtenerCapacitacionItem( idCapacitacionItem );
	}

	@Override
	public void registrarCapacitacionItem(CapacitacionItem capacitacionItem) {
		capacitacionDAO.registrarCapacitacionItem( capacitacionItem );
	}

	@Override
	public Capacitacion obtenerPorFactura(Integer idFactura) {
		return capacitacionDAO.obtenerPorFactura(idFactura);
	}

	@Override
	public List<Capacitacion> buscarCapacitaciones(Capacitacion capacitacion) {
		List<Capacitacion> lista = capacitacionDAO.buscarCapacitaciones(capacitacion);
		logger.debug( "encontrados: " + lista.size() );
		if(lista.size()>0)
			for(Capacitacion cap : lista){
				//logger.debug( "idCap: "+cap.getIdCapacitacion()+ " idFactura: " + cap.getFactura().getIdFactura() );
				cap.setReclamo( reclamoService.obtenerPorIdFactura( cap.getFactura().getIdFactura() ));
			}
		return lista;
	}

	@Override
	public List<Capacitador> listarCapacitador() {
		return capacitacionDAO.listarCapacitador();
	}

	@Override
	public void asignarCapacitador(Integer idCapacitacion, Integer idCapacitador) {
		capacitacionDAO.asignarCapacitador(idCapacitacion,idCapacitador);
	}

}
