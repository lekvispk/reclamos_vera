
package pe.org.reclamos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.CapacitacionDAO;
import pe.org.reclamos.entidad.Capacitacion;
import pe.org.reclamos.entidad.CapacitacionItem;
import pe.org.reclamos.service.CapacitacionService;

@Service
public class CapacitacionServiceImpl implements CapacitacionService {

	@Autowired
	private CapacitacionDAO capacitacionDAO;
	
	@Override
	public void grabar(Capacitacion capacitacion) {
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

}
