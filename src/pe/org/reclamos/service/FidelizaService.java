package pe.org.reclamos.service;

import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.entidad.Fideliza;

public interface FidelizaService {

	public void registrarFidelizacion(Fideliza fideliza);
	public Fideliza obtenerFidelizacionPorReclamo(Long idReclamo);
	/**
	 * devuelve una fidelizacion en caso exista, null en caso contratio
	 * @param factura
	 * @return
	 */
	public Fideliza obtenerFidelizacionPorCliente(Factura factura);
	public Fideliza obtenerFidelizacion(Long idFidelizacion);
	public void actualizarPromocion(Fideliza fideliza);
	
}
