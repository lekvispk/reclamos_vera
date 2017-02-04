package pe.org.reclamos.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.reclamos.entidad.Usuario;
import pe.org.reclamos.service.UsuarioService;
import pe.org.reclamos.utiles.Mail;
import pe.org.reclamos.utiles.Utiles;

/**
 * Clase que maneja el login de los usuarios. 
 * Debe cargar data de las tablas personalizadas para obtener datos completos de los usuarios.
 * @author ElvisRubén
 *
 */
@Controller
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class );
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private Mail mail;
	
	@RequestMapping("/login.htm")  
	 public String login() {  
	   logger.debug("pantalla de login");
	   
	   return "login";  
	 }  

	@RequestMapping("/inicio.htm")  
	 public String inicio( HttpServletRequest request , ModelMap model) {  
	   logger.debug("ingreso al inicio");
//	   try {
//		   Usuario usr = usuarioService.autenticarUsuario( request.getParameter("username") ,request.getParameter("password")    );
//		   request.getSession().setAttribute("usrLogin", usr );
//		   return "index";  
//	   } catch (Exception e) {
//			logger.error( e.getMessage() );
//			model.put("msgError", e.getMessage() );
//			return "login";  
//	   }
	   return "index";
	 }  
	
	@RequestMapping( value="/solicitarClave.htm" , method=RequestMethod.GET )  
	public String preSolicitarClave( HttpServletRequest request , ModelMap model){
		logger.debug("preSolicitarClave");
		 return "administracion/recuperarClave";
	}
	 
	@RequestMapping( value="/solicitarClave.htm" , method=RequestMethod.POST )  
	public String solicitarClave( HttpServletRequest request , ModelMap model){
		logger.debug("solicitarClave");
		
		String email = request.getParameter("email");
		
		Usuario usr = usuarioService.obtenerPorEmail( email );
		
		if( usr == null){
			
			logger.debug("solicitarClave con error");
			model.put("msgError", "El correo indicado no se encuentra registrado en el sistema");
			return "administracion/recuperarClave";
			
		}
		String from = "ecampos@acsserviciosgenerales.com";
		String to = usr.getEmail();
		String subject = "Clave nueva";
		String clavetmp =  Utiles.generarClave( 8 );
		
		StringBuilder msg = new StringBuilder();
		msg.append("Su nueva clave es: ");
		msg.append(" " +clavetmp);
		
		mail.sendMail(from, to, null, subject, msg.toString());
		usr.setResetPassword( 1 );
		usr.setPassword( Utiles.hashMd5( clavetmp ));
		usuarioService.registrar( usr );
		
		
		logger.debug("solicitarClave todo ok");
		model.put("mensaje", "Clave temporal enviada");
		return "administracion/mensajeClave";
		
	} 
	 
	@RequestMapping("/index.htm")  
	public String index() {  
	   logger.debug("index.... solo desde el login");
	   
	   Usuario usr = usuarioService.obtenerPorUsername(  Usuario.getUsuarioBean().getUsername() );
	   
	   logger.debug("validando si debe actualziar la clave " + usr.getResetPassword());
	   if( usr.getResetPassword() == 1 ){
		   logger.debug("pendiente de cambiar clave");
		   return "administracion/cambiarClave"; 
	   }
	  	   
	   return "index";  
	 }  
	
	@RequestMapping(value="/cambiarClave.htm", method=RequestMethod.GET )  
	public String preCambiarClave(){
	   logger.debug("preCambiarClave");
	   return "administracion/cambiarClave";
	 }  
	
	@RequestMapping(value="/cambiarClave.htm", method=RequestMethod.POST )  
	public String cambiarClave( HttpServletRequest request, ModelMap model ){
	   logger.debug("cambiarClave");
	   
	   String claveActual = request.getParameter("passwordTemporal");
	   String claveNueva= request.getParameter("password");
	   String confirmar = request.getParameter("confirmarPassword");
	   
	   Usuario usr = usuarioService.obtenerPorUsername(  Usuario.getUsuarioBean().getUsername() );
	   logger.debug("password="+usr.getPassword());
	   logger.debug("passwordtemporal="+Utiles.hashMd5( claveActual ));
	   
	   if( !usr.getPassword().equals( Utiles.hashMd5( claveActual )) ){
		   logger.debug("clave temporal incorrecta");
		   model.put("msgError", "clave temporal incorrecta" );
		   return "administracion/cambiarClave";
	   }
	   if( !claveNueva.equals( confirmar )){
		   logger.debug("nueva calve no coinide con la confirmacion");
		   model.put("msgError", "clave nueva no coincide" );
		   return "administracion/cambiarClave";
	   }
	   logger.debug("claves ok... actualziar usuario");
	   
	   usr.setResetPassword( 0 );
	   usr.setPassword( Utiles.hashMd5( claveNueva ));
	   usuarioService.registrar( usr );
	   model.put("mensaje", "Clave modificada" );
	   
	   return "redirect:/inicio.htm";
	 }  
}
