package pe.org.reclamos.dao;

import java.util.List;

import pe.org.reclamos.entidad.Detallefactura;
import pe.org.reclamos.entidad.Factura;

public interface FacturaDAO {

	public void registrar(Factura factura);
	public Factura obtener(Long factura);
	public List<Factura> buscar(Factura factura);
	/**
	 * lista de facturas que cumplen con el cliterio
	 * @param factura
	 * @return
	 */
	public List<Factura> buscarFacturasParaFidelizacion(Factura factura);
	public List<Factura> buscarFacturasParaFidelizacionDeUnCliente(Factura factura);
	
	public List<Factura> buscarFacturasParaAplicarPromocion(Factura factura);
	
	public void eliminar(Long factura);

	public List<Detallefactura> listarDetalleFactura( Detallefactura detalle );
	public List<Factura> buscarFacturasConFidelizacion( Factura factura);
	public List<Factura> buscarFacturasConReclamos(Factura f);
	
}