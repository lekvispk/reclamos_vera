package pe.org.reclamos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.reclamos.entidad.Perfil;
import pe.org.reclamos.service.PerfilService;

@Controller
@RequestMapping(value="/perfil")
@Scope("session")
public class PerfilController {
	
	private static final Logger logger = Logger.getLogger(PerfilController.class );
	
	@Autowired
	PerfilService perfilService;
	
	@RequestMapping(value="/lista.htm", method=RequestMethod.GET)
	public String lista(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lPerfiles",  perfilService.listarPerfiles() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			 // model.put("reclamo", new Reclamo() );
		   }
		return "administracion/perfiles/lista";
	}
	
	@RequestMapping(value="/registro.htm", method=RequestMethod.GET)
	public String registro(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("perfil", new Perfil() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			
		   }
		return "administracion/perfiles/registro";
	}
	
	@RequestMapping(value="/modificar.htm", method=RequestMethod.GET)
	public String preModificar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("preModificar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   Long id = new Long(request.getParameter("id"));
			   model.put("perfil", perfilService.obtener( id ) );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			
		   }
		return "administracion/perfiles/registro";
	}
	
	@RequestMapping(value="/eliminar.htm", method=RequestMethod.GET)
	public String eliminar( HttpServletRequest request,HttpServletResponse response, ModelMap model ){
		logger.debug("eliminando perfil");
		try {
			Long id = new Long(request.getParameter("id"));
			perfilService.eliminar( id );
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Error: No se pudo eliminar el perfil" );
		}
		return lista(request, response, model);
	}
	
}
