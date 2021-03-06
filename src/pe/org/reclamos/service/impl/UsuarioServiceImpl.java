package pe.org.reclamos.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.org.reclamos.dao.UsuarioDAO;
import pe.org.reclamos.entidad.Permiso;
import pe.org.reclamos.entidad.Usuario;
import pe.org.reclamos.rest.bean.UsuarioRest;
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
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public void registrar(Usuario usuario) {
		final String METHODNAME = "registrar - ";
		logger.debug(METHODNAME + "INI");
		
		List<Permiso> permisos = null ;
		Boolean nuevo = false;
		usuario.getPersona().setEmail( usuario.getEmail());
		permisos = permisoService.listarPermisos();
		
		if( usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0 ){
			usuario.setCreatedAt( new Date() );
			usuario.setPassword( Utiles.hashMd5(usuario.getPassword() ) );	
			nuevo = true;
		}else{
			usuario.setUpdatedAt( new Date() );
		}
		
		usuarioDAO.registrar(usuario.getPersona());
		usuarioDAO.registrar(usuario);
		logger.debug(METHODNAME + "usuario registrado");
		
		if(!nuevo){
			logger.debug(METHODNAME + "eliminar permisos de " + usuario.getUsername());
			usuarioDAO.eliminarPermisos( usuario.getUsername() );
		}
		
		usuarioDAO.registrarPermisos( usuario, permisos);
		
		logger.debug(METHODNAME + "FIN");
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

	@Override
	public UsuarioRest obtenerUsuarioRest(String user) {
		final String METHODNAME = "obtenerUsuarioRest - ";
		logger.debug(METHODNAME+ "INI");
		Usuario usuario = null;
		UsuarioRest usuarioRest = new UsuarioRest();
		
		try {
			usuario = usuarioDAO.obtenerPorEmail( user );	
		} catch (Exception e) {
			logger.error(METHODNAME +"No encontrado usuario con email: " + e.getMessage());
		}
		try {
			if( usuario == null){
				usuario = usuarioDAO.obtenerUsuarioPorUsername( user );	
			}
		} catch (Exception e) {
			logger.error(METHODNAME +"No encontrado usuario con username: " + e.getMessage());
		}
		
		if( usuario == null){
			logger.error(METHODNAME +"No encontrado usuario." );
			return null;
		}
		logger.debug(METHODNAME+ "por clonar usuario");
		
		try {
			BeanUtils.copyProperties(usuarioRest, usuario);
			
			usuarioRest.getPersona().setCliente( usuarioDAO.obteneClientePorPersona( usuario.getPersona().getIdPersona() )	);
			
			usuario = null;
		} catch (Exception e) {
			logger.error("No se pudo clonar el objeto");
			e.printStackTrace( );
		}
		logger.debug(METHODNAME+ "FIN");
		return usuarioRest;
	}

}
