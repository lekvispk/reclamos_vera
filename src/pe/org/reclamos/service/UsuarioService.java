package pe.org.reclamos.service;

import java.util.List;

import pe.org.reclamos.entidad.Usuario;

public interface UsuarioService {

	public List<Usuario> listarUsuarios( Usuario usuario );
	public Usuario obtener(Integer idUsuario);
	public void registrar(Usuario usuario);
	public void eliminar(Integer valueOf);
	public Usuario obtenerPorEmail(String email);
	public Usuario obtenerPorUsername(String username);
	
}
