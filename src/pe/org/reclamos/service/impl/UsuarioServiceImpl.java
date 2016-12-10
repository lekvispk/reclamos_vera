package pe.org.reclamos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.UsuarioDAO;
import pe.org.reclamos.entidad.Usuario;
import pe.org.reclamos.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public List<Usuario> listarUsuarios(Usuario usuario) {
		return usuarioDAO.listarUsuario(usuario);
	}

}
