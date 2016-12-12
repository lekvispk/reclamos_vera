package pe.org.reclamos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.UsuarioDAO;
import pe.org.reclamos.entidad.Usuario;
import pe.org.reclamos.service.UsuarioService;
import pe.org.reclamos.utiles.Utiles;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public List<Usuario> listarUsuarios(Usuario usuario) {
		return usuarioDAO.listarUsuarios(usuario);
	}

	@Override
	public Usuario obtener(Integer idUsuario) {
		return usuarioDAO.obtener(idUsuario);
	}

	@Override
	public void registrar(Usuario usuario) {
		if( usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0 ){
			usuario.setPassword( Utiles.hashMd5(usuario.getPassword() ) );	
		}
		usuarioDAO.registrar(usuario);
	}

}
