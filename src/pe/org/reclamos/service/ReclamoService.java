package pe.org.reclamos.service;

import java.util.List;

import pe.org.reclamos.entidad.ItemsReclamo;
import pe.org.reclamos.entidad.Reclamo;

public interface ReclamoService {

	public void registrar(Reclamo reclamo) throws Exception;
	public void actualizar(Reclamo reclamo);
	public Reclamo obtener(Long reclamo);
	public Reclamo obtenerPorIdFactura(Long idFactura);
	public List<Reclamo> buscar(Reclamo reclamo);
	public void eliminar(Long reclamo);
	public void grabarNoIndemnizados(String[] idReclamos);
	public void registrarIndemnizacion(Reclamo rec);
	public ItemsReclamo obtenerItemReclamo(Long idReclamo);
		
}
