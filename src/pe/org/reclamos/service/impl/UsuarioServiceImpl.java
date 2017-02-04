package pe.org.reclamos.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.org.reclamos.dao.UsuarioDAO;
import pe.org.reclamos.entidad.Permiso;
import pe.org.reclamos.entidad.Usuario;
import pe.org.reclamos.service.PermisoService;
import pe.org.reclamos.service.UsuarioService;
import pe.org.reclamos.utiles.Utiles;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger logger = Logger.getLogger(UsuarioServiceImpl.class );
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private PermisoService permisoService;
	
	@Override
	public List<Usuario> listarUsuarios(Usuario usuario) {
		return usuarioDAO.listarUsuarios(usuario);
	}

	@Override
	public Usuario obtener(Integer idUsuario) {
		return usuarioDAO.obtener(idUsuario);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void registrar(Usuario usuario) {
		List<Permiso> permisos = null ;
		Boolean nuevo = false;
		
		if( usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0 ){
			usuario.setCreatedAt( new Date() );
			usuario.setPassword( Utiles.hashMd5(usuario.getPassword() ) );	
			nuevo = true;
			permisos = permisoService.listarPermisos();
		}
		
		usuarioDAO.registrar(usuario);
		
		if(nuevo){
			usuarioDAO.registrarPermisos( usuario, permisos);
		}
	}

	@Override
	public void eliminar(Integer idUsuario) {
		usuarioDAO.eliminar(idUsuario);
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		logger.debug("email a buscar = "+email);
		return usuarioDAO.obtenerPorEmail( email );
	}

	@Override
	public Usuario obtenerPorUsername(String username) {
		logger.debug("username a buscar = "+username);
		return usuarioDAO.obtenerUsuarioPorUsername( username );
	}

}
