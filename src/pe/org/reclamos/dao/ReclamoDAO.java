package pe.org.reclamos.dao;

import java.util.List;

import pe.org.reclamos.entidad.ItemsReclamo;
import pe.org.reclamos.entidad.Reclamo;

public interface ReclamoDAO {

	public void registrar(Reclamo reclamo);
	public void registrar(ItemsReclamo item);
	public Reclamo obtener(Long reclamo);
	public List<Reclamo> buscar(Reclamo reclamo);
	public void eliminar(Long reclamo);
	
	
}
