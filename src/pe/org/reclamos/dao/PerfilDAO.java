package pe.org.reclamos.dao;

import java.util.List;

import pe.org.reclamos.entidad.Perfil;

public interface PerfilDAO {

	public List<Perfil> listarPerfiles();
	public void eliminar(Long IdPerfil);
	public void registrar(Perfil perfil);
	public void modificar(Perfil perfil);
	public Perfil obtener(Long IdPerfil);
	
}
