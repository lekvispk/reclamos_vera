package pe.org.reclamos.dao;

import java.util.List;

import pe.org.reclamos.entidad.Cliente;
import pe.org.reclamos.entidad.Permiso;
import pe.org.reclamos.entidad.Usuario;

public interface UsuarioDAO {


	public List<Usuario> listarUsuarios( Usuario usuario );
	public Usuario obtener(Integer idUsuario);
	public void registrar(Usuario usuario);
	public Usuario obtenerUsuarioPorUsername(String username);
	public void registrarPermisos(Usuario usuario, List<Permiso> permisos);
	public void eliminar(Integer idUsuario);
	public Usuario obtenerPorEmail(String email);
	public Cliente obteneClientePorPersona(Integer idPersona);
	
}
