package pe.org.reclamos.dao;

import java.util.List;

import pe.org.reclamos.entidad.Permiso;

public interface PermisoDAO {

	public List<Permiso> listarPermisos();

	public List<Permiso> listarPermisos(Integer idPerfil);
	
}
