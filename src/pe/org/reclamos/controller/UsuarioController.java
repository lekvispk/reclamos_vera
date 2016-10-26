package pe.org.reclamos.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.entidad.Usuario;

@Controller
@RequestMapping(value="/usuario")
@Scope("session")
public class UsuarioController {

	private static final Logger logger = Logger.getLogger(UsuarioController.class );
	
	@RequestMapping(value="/lista.htm", method=RequestMethod.GET)
	public String lista(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lUsuarios",  new ArrayList<Usuario>());
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			  model.put("reclamo", new Reclamo() );
		   }
		return "usuarios/lista";
	}
	
}
