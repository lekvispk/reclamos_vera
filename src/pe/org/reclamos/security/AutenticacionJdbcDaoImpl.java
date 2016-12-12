package pe.org.reclamos.security;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.UsuarioDAO;
import pe.org.reclamos.entidad.Usuario;


@Repository("userLoginService")
public class AutenticacionJdbcDaoImpl extends JdbcDaoImpl{

	private Logger logger = Logger.getLogger(AutenticacionJdbcDaoImpl.class);
		
	@Autowired
	UsuarioDAO usuarioDao;
	
	@Autowired
	public AutenticacionJdbcDaoImpl(DataSource dataSource){
		setDataSource(dataSource);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		try {
			
			this.setUsersByUsernameQuery("SELECT u.username as username,u.password as password, u.estado as enabled  FROM usuario u WHERE u.username = ?");
			this.setAuthoritiesByUsernameQuery("SELECT username ,authority FROM authorities WHERE username = ?");
			
			logger.debug("usuario login "+ username );
			UserDetails user = super.loadUserByUsername(username);
			Usuario usuario = usuarioDao.obtenerUsuarioPorUsername(username);
			
			Usuario ubean = new Usuario(user.getUsername(), user.getPassword(), user.isEnabled(), user.getAuthorities());
			ubean.setUsername(user.getUsername());
			ubean.setPassword(user.getPassword());
			//ubean.setNombres(usuario.getNombres());
			//ubean.setApePaterno(usuario.getApePaterno());
			//ubean.setApeMaterno(usuario.getApeMaterno());
			ubean.setEstado( user.isEnabled()==true?1:0);
			logger.debug("********* => "+ubean.toString());
			return ubean;	
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("No hay notaria relacionada a este usuario");
		}
	}

	
}
