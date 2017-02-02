package pe.org.reclamos.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.reclamos.entidad.Perfil;
import pe.org.reclamos.entidad.Permiso;
import pe.org.reclamos.service.PerfilService;
import pe.org.reclamos.service.PermisoService;
import pe.org.reclamos.utiles.Utiles;

@Controller
@RequestMapping(value="/perfil")
@Scope("session")
public class PerfilController {
	
	private static final Logger logger = Logger.getLogger(PerfilController.class );
	
	@Autowired
	PerfilService perfilService;
	
	@Autowired
	PermisoService permisosService;
	
	@RequestMapping(value="/lista.htm", method=RequestMethod.GET)
	public String lista(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lPerfiles",  perfilService.listarPerfiles() );
			   if( Utiles.nullToBlank( request.getParameter("msg") ).equals("1") ){
				   model.put("mensaje", "Perfil registrado correctamente" );
			   }
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			 // model.put("reclamo", new Reclamo() );
		   }
		return "administracion/perfiles/lista";
	}
	
	@RequestMapping(value="/registro.htm", method=RequestMethod.GET)
	public String preRegistro(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("perfil", new Perfil() );
			   model.put("lPermisos", permisosService.listarPermisos() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			
		   }
		return "administracion/perfiles/registro";
	}
	
	@RequestMapping(value="/registro.htm", method=RequestMethod.POST)
	public String registro(@Valid Perfil perfil, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("registro");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   logger.debug( perfil.toString() );
			   
			   String[] lista = request.getParameterValues("listaPermisos");
			   if( lista !=null){
				   perfil.setListaPermisos( new ArrayList<Permiso>());
				   for(String temp : lista ){
					   logger.debug( temp.toString() );
					   perfil.getListaPermisos().add( new Permiso( Integer.valueOf( temp.toString() )) );
				   }
			   }
			   
			   perfilService.registrar( perfil );
			   			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
			 model.put("perfil", perfil );
			 model.put("lPermisos", permisosService.listarPermisos() );
			 return "administracion/perfiles/registro";	   
		   }finally{
			
		   }
		 return "redirect:/perfil/lista.htm?msg=1";
	}

	@RequestMapping(value="/modificar.htm", method=RequestMethod.GET)
	public String preModificar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("preModificar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   Long id = new Long(request.getParameter("id"));
			   Perfil perf = perfilService.obtener( id );
			   model.put("perfil", perf );
			   model.put("lPermisos", permisosService.listarPermisos() );
			   
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
