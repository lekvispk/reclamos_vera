package pe.org.reclamos.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.reclamos.entidad.Permiso;
import pe.org.reclamos.service.PermisoService;

@Controller
@RequestMapping(value="/permiso")
@Scope("session")
public class PermisoController {
	
	private static final Logger logger = Logger.getLogger(PermisoController.class );
	
	@Autowired
	PermisoService permisoService;
	
	@RequestMapping(value="/lista.htm", method=RequestMethod.GET)
	public String lista(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lPermisos", permisoService.listarPermisos() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			 // model.put("reclamo", new Reclamo() );
		   }
		return "administracion/permisos/lista";
	}
}
