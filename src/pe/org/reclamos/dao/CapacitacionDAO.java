package pe.org.reclamos.dao;

import java.util.List;

import pe.org.reclamos.entidad.Capacitacion;
import pe.org.reclamos.entidad.CapacitacionItem;

public interface CapacitacionDAO {

	public void grabar( Capacitacion capacitacion);
	public List<Capacitacion> listar(Capacitacion capacitacion);
	public Capacitacion obtener (Long idCapacitacion);
	public CapacitacionItem obtenerCapacitacionItem(Long idCapacitacion,Long idDetalleFactura);
	public void registrarCapacitacionItem(CapacitacionItem capacitacionItem);
	public CapacitacionItem obtenerCapacitacionItem(Long idCapacitacionItem);
	public Capacitacion obtenerPorFactura(Integer idFactura);
	
}
