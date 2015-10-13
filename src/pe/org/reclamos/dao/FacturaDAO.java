package pe.org.reclamos.dao;

import java.util.List;

import pe.org.reclamos.entidad.Factura;

public interface FacturaDAO {

	public void registrar(Factura factura);
	public Factura obtener(Long factura);
	public List<Factura> buscar(Factura factura);
	public void eliminar(Long factura);
	
}