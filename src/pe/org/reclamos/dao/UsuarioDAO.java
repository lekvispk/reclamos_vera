package pe.org.reclamos.dao;

import java.util.List;

import pe.org.reclamos.entidad.Usuario;

public interface UsuarioDAO {


	public List<Usuario> listarUsuarios( Usuario usuario );
	public Usuario obtener(Integer idUsuario);
	public void registrar(Usuario usuario);
	
}
