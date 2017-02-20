package pe.org.reclamos.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.org.reclamos.rest.bean.UsuarioRest;
import pe.org.reclamos.service.UsuarioService;

@Controller
@RequestMapping("/auth")
public class AutenticacionController {

	private static final Logger logger = Logger.getLogger( AutenticacionController.class );
	@Autowired
	private UsuarioService usuarioService;
	
	//http://localhost:8080/reclamos/rest/auth/basic/elvis
	
	@RequestMapping(value = "/basic/{user}", method = RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UsuarioRest basicLoginByUsernameOrPassword(@PathVariable String user, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		final String METHODNAME = "basicLoginByUsernameOrPassword2 - ";
		logger.debug(METHODNAME+ " :" + user);
		
		UsuarioRest usuario2 = new UsuarioRest();
		
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			logger.error(METHODNAME +"No encontrado usuario con email: " + e.getMessage());
		}
		
		usuario2 = usuarioService.obtenerUsuarioRest(user);
		
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
	
}
