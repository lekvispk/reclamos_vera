package pe.org.reclamos.service;

import java.util.List;

import pe.org.reclamos.entidad.Capacitacion;
import pe.org.reclamos.entidad.CapacitacionItem;
import pe.org.reclamos.entidad.Capacitador;

public interface CapacitacionService {

	public void grabar( Capacitacion capacitacion);
	public List<Capacitacion> listar(Capacitacion capacitacion);
	public Capacitacion obtener (Long idCapacitacion);
	public CapacitacionItem obtenerCapacitacionItem(Long long1, Long long2);
	public CapacitacionItem obtenerCapacitacionItem(Long idCapacitacionItem);
	public void registrarCapacitacionItem(CapacitacionItem capacitacionItem);
	public Capacitacion obtenerPorFactura(Integer idFactura);
	public List<Capacitacion> buscarCapacitaciones(Capacitacion capacitacion);
	public List<Capacitador> listarCapacitador();
	public void asignarCapacitador(Integer idCapacitacion, Integer idCapacitador);
	
}
