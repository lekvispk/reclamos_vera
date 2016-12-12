package pe.org.reclamos.service;

import java.util.List;

import pe.org.reclamos.entidad.Permiso;

public interface PermisoService {

	public List<Permiso> listarPermisos();
	public List<Permiso> listarPermisos(Integer idPerfil);
	
}
