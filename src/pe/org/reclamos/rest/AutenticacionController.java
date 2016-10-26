package pe.org.reclamos.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth")
public class AutenticacionController {

	private static final Logger logger = Logger.getLogger( AutenticacionController.class );
	
	//http://localhost:8082/reclamos/rest/auth/basic/elvis
	
	@RequestMapping(value = "/basic/{user}", method = RequestMethod.GET )
	public @ResponseBody String basicLogin(@PathVariable String user, ModelMap model) {
		logger.debug("basicLogin " + user);
		return "{'usuario':'elvis','apellido':'campos'}";
	}
	
	@RequestMapping(value = "/login/", method = RequestMethod.GET)
	public String getDefaultMovie(ModelMap model) {
		model.addAttribute("movie", "this is default movie");
		return "list";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST )
	public String postLogin(HttpServletRequest request, ModelMap model) {

		logger.debug("basicLogin " + request.getParameter("usuario"));
		logger.debug("basicLogin " + request.getParameter("password"));
		
		return "{'usuario':'elvis','apellido':'campos', 'status':'ok'}";

	}
	
}
