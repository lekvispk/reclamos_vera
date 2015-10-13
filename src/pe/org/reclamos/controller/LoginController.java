package pe.org.reclamos.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class );
	
	//@Autowired
	//private UsuarioService usuarioService;
	
	@RequestMapping("/login.htm")  
	 public String login() {  
	   logger.debug("pantalla de login");
	   return "login";  
	 }  

	@RequestMapping("/ingresar.htm")  
	 public String ingresar( HttpServletRequest request , ModelMap model) {  
	   logger.debug("ingreso al index");
	   
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
	
	@RequestMapping("/index.htm")  
	 public String index() {  
	   logger.debug("ingreso al index");
	   return "index";  
	 }  
	
	
}
