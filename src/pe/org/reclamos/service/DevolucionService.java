package pe.org.reclamos.service;

import java.util.List;

import pe.org.reclamos.entidad.Despachador;
import pe.org.reclamos.entidad.DetalleDevolucion;
import pe.org.reclamos.entidad.Devolucion;

public interface DevolucionService {

	public Devolucion obtenerPorReclamo(Integer idReclamo);
	public Devolucion obtener(Integer idDevolucion);
	public void grabar(Devolucion devolucion);
	public Devolucion obtenerPorDetalleFactura(Integer idDetalleFactura);
	public void grabarDetalle(DetalleDevolucion dd);
	public List<DetalleDevolucion> listarDetalleDevolucion(Integer iddevolucion);
	public List<DetalleDevolucion> listarProductosParaDevolver();
	public List<Despachador> listarDespachadores();
	public DetalleDevolucion obtenerDetalleDevolucion(Integer idDetalleDevolucion);
	public Devolucion obtenerAutorizacionDeReclamo(Long idReclamo);
	public Integer obtenerSiguienteNumeroDeActa();
	
}
