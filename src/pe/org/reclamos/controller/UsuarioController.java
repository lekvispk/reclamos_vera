package pe.org.reclamos.controller;

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

import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.entidad.Usuario;
import pe.org.reclamos.service.PerfilService;
import pe.org.reclamos.service.UsuarioService;

@Controller
@RequestMapping(value="/usuario")
@Scope("session")
public class UsuarioController {

	private static final Logger logger = Logger.getLogger(UsuarioController.class );
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PerfilService perfilService;
	
	@RequestMapping(value="/lista.htm", method=RequestMethod.GET)
	public String lista(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lUsuarios", usuarioService.listarUsuarios( new Usuario() ));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			  model.put("reclamo", new Reclamo() );
		   }
		return "usuarios/lista";
	}
	
	@RequestMapping(value="/lista.htm", method=RequestMethod.POST)
	public String buscar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lUsuarios", usuarioService.listarUsuarios( new Usuario() ));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			  model.put("reclamo", new Reclamo() );
		   }
		return "usuarios/lista";
	}
	
	@RequestMapping(value="/registro.htm" , method=RequestMethod.GET)
	public String preNuevoUsuario( ModelMap model){
		logger.debug("pre registrar usuario");
		model.put("usuario", new Usuario());
		model.put("lPerfiles", perfilService.listarPerfiles() );
		return "usuarios/registro";
	}
	
	@RequestMapping(value="/registro.htm" , method=RequestMethod.POST)  
	 public String nuevoCliente(@Valid Usuario usuario, BindingResult result, HttpServletRequest request,  HttpServletResponse response, ModelMap model) {  
		try {
			logger.debug("grabar un cliente usuario");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			usuarioService.registrar( usuario );
			
			model.put("mensaje","Se ha grabado satisfactoriamente");
			model.put("cliente", new Usuario());
			return "redirect:/usuario/lista.htm";
		} catch (Exception e) {
			e.printStackTrace();
			model.put("usuario", usuario);
			model.put("lPerfiles", perfilService.listarPerfiles() );
			model.put("msgError", "Se han producido errores, por favor verifique: "+e.getMessage() );
			return "usuarios/registro";
		}
	 }
	
	@RequestMapping(value="/modificar.htm" , method=RequestMethod.GET)
	public String preModificarUsuario( ModelMap model,HttpServletRequest request){
		logger.debug("pre modificar usuario");
		Usuario usr = usuarioService.obtener( Integer.valueOf( request.getParameter("idUsuario")));
		model.put("usuario", usr );
		model.put("lPerfiles", perfilService.listarPerfiles() );
		return "usuarios/registro";
	}
	
	@RequestMapping(value="/eliminar.htm" , method=RequestMethod.GET)
	public String eliminarUsuario( ModelMap model,HttpServletRequest request){
		logger.debug("eliminar usuario");
		usuarioService.eliminar( Integer.valueOf( request.getParameter("idUsuario")));
		return "redirect:/usuario/lista.htm";
	}
	
}
