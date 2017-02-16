package pe.org.reclamos.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.org.reclamos.dao.UsuarioDAO;
import pe.org.reclamos.entidad.Usuario;
import pe.org.reclamos.rest.bean.UsuarioRest;

@Controller
@RequestMapping("/auth")
public class AutenticacionController {

	private static final Logger logger = Logger.getLogger( AutenticacionController.class );
	@Autowired
	UsuarioDAO usuarioDao;
	
	//http://localhost:8080/reclamos/rest/auth/basic/elvis
	
	@RequestMapping(value = "/basic/{user}", method = RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String basicLoginByUsernameOrPassword(@PathVariable String user, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		final String METHODNAME = "basicLoginByUsernameOrPassword - ";
		logger.debug(METHODNAME+ " :" + user);
		
		Usuario usuario = null;
		String json = "";
		
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");	
			
			usuario = usuarioDao.obtenerPorEmail( user );	
		} catch (Exception e) {
			logger.error(METHODNAME +"No encontrado usuario con email: " + e.getMessage());
		}
		
		try {
			if( usuario == null){
				usuario = usuarioDao.obtenerUsuarioPorUsername( user );	
			}
		} catch (Exception e) {
			logger.error(METHODNAME +"No encontrado usuario con username: " + e.getMessage());
		}
		
		if( usuario == null){
			return "{'status':'0','code':'Usuario no encontrado'}";
		}
		
		UsuarioRest usuario2 = new UsuarioRest();
		try {
			BeanUtils.copyProperties(usuario2, usuario);
			logger.debug("objeto clonado " + usuario2 );
			usuario = null;
		} catch (Exception e) {
			logger.error("No se pudo clonar el objeto");
			e.printStackTrace( );
		}
		
		String format = "yyyy-MM-dd";
		Gson gson2 = new GsonBuilder()
		   .setDateFormat( format ).create(); 
		
		json = gson2.toJson( usuario2 );
		//logger.error("json = " + json );
		//json = json.replaceAll("\"", "\\\"") ;
		//json = json.replaceAll("'", "\"") ;
		logger.error("json = " + json);
		
		return json;
	}
	
	@RequestMapping(value = "/basic2/{user}", method = RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UsuarioRest basicLoginByUsernameOrPassword2(@PathVariable String user, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		final String METHODNAME = "basicLoginByUsernameOrPassword2 - ";
		logger.debug(METHODNAME+ " :" + user);
		
		Usuario usuario = null;
		
		try {
			
			response.setContentType("application/json;charset=ISO-8859-1");
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");	
			
			usuario = usuarioDao.obtenerPorEmail( user );	
		} catch (Exception e) {
			logger.error(METHODNAME +"No encontrado usuario con email: " + e.getMessage());
		}
		
		try {
			if( usuario == null){
				usuario = usuarioDao.obtenerUsuarioPorUsername( user );	
			}
		} catch (Exception e) {
			logger.error(METHODNAME +"No encontrado usuario con username: " + e.getMessage());
		}
		
		if( usuario == null){
			return null;
		}
		logger.debug(METHODNAME+ "por clonar usuario");
		UsuarioRest usuario2 = new UsuarioRest();
		try {
			BeanUtils.copyProperties(usuario2, usuario);
			usuario = null;
		} catch (Exception e) {
			logger.error("No se pudo clonar el objeto");
			e.printStackTrace( );
		}
		logger.debug(METHODNAME+ "FIN");
		return usuario2;
	}
	
	@RequestMapping(value = "/login/", method = RequestMethod.GET)
	public String getDefaultMovie(ModelMap model) {
		model.addAttribute("movie", "this is default movie");
		return "list";
	}
	
	//http://localhost:8082/reclamos/rest/auth/login
	@RequestMapping(value = "/login", method = RequestMethod.POST )
	public String postLogin(HttpServletRequest request, ModelMap model) {

		logger.debug("basicLogin " + request.getParameter("usuario"));
		logger.debug("basicLogin " + request.getParameter("password"));
		
		return "{'usuario':'elvis','apellido':'campos', 'status':'ok'}";

	}
	
	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody UsuarioRest getShopInJSON(@PathVariable String name) {
		
		logger.debug("getShopInJSON INI");
		UsuarioRest usuario = new UsuarioRest();
		usuario.setUsername( name );
		logger.debug("getShopInJSON FIN");
		return usuario;
		
	}	
	
}
