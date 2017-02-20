package pe.org.reclamos.service;

import java.util.List;

import pe.org.reclamos.entidad.Usuario;
import pe.org.reclamos.rest.bean.UsuarioRest;

public interface UsuarioService {

	public List<Usuario> listarUsuarios( Usuario usuario );
	public Usuario obtener(Integer idUsuario);
	public void registrar(Usuario usuario);
	public void eliminar(Integer valueOf);
	public Usuario obtenerPorEmail(String email);
	public Usuario obtenerPorUsername(String username);
	/**
	 * obtiene por username o email
	 * @param user
	 * @return
	 */
	public UsuarioRest obtenerUsuarioRest(String user);
	
}
