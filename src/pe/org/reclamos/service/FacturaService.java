package pe.org.reclamos.service;

import java.util.List;

import pe.org.reclamos.entidad.Detallefactura;
import pe.org.reclamos.entidad.Factura;

public interface FacturaService {

	public void registrar(Factura factura);
	public Factura obtener(Long factura);
	public List<Factura> buscar(Factura factura);
	public List<Factura> buscarFacturasParaFidelizacion(Factura factura);
	public List<Factura> buscarFacturasParaFidelizacionDeUnCliente(Factura factura);
	public List<Factura> buscarFacturasConFidelizacion(Factura factura);
	public void eliminar(Long factura);

	public List<Detallefactura>  listarDetalleFactura( Detallefactura detalle );
	
}