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
import pe.org.reclamos.service.ReclamoService;

@Controller
@RequestMapping(value="/seguimiento")
@Scope("session")
public class SeguimientoController {

	private static final Logger logger = Logger.getLogger(SeguimientoController.class );
	
	@Autowired
	private ReclamoService reclamoService;

	@RequestMapping(value="/lista.htm",method=RequestMethod.GET)
	public String listar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		final String METHODNAME = "listar - ";
		logger.debug(METHODNAME + "INI");
		 try {
			   
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			  
			   logger.debug( METHODNAME + Usuario.getUsuarioBean() );
			   
			   Reclamo reclamo = new Reclamo();
			   reclamo.setIdCliente( new Long( Usuario.getUsuarioBean().getIdUsuario() ) );
			   logger.debug( METHODNAME + reclamo.toString() );
			   
			   model.put("lReclamos", reclamoService.buscar(reclamo));
			   model.put("reclamo", reclamo );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   logger.debug(METHODNAME + "FIN");
		   }
		return "seguimiento/lista";
	}
	
	@RequestMapping(value="/lista.htm",method=RequestMethod.POST)
	public String buscar(@Valid Reclamo reclamo, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		final String METHODNAME = "buscar - ";
		logger.debug(METHODNAME + "INI");
		 try {
			   
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			  
			   reclamo.setIdCliente( new Long( Usuario.getUsuarioBean().getIdUsuario() ) );
			   logger.debug( METHODNAME + reclamo.toString() );
			   
			   model.put("lReclamos", reclamoService.buscar(reclamo));
			   model.put("reclamo", reclamo );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   logger.debug(METHODNAME + "FIN");
		   }
		return "seguimiento/lista";
	}
	
	@RequestMapping(value="/ver.htm" , method=RequestMethod.GET)
	public String preModificar(HttpServletRequest request, ModelMap model){
		logger.debug("ver detalle");
		
		Long idReclamo = new Long( request.getParameter("idReclamo") );
		model.put("reclamo", reclamoService.obtener( idReclamo ));
		
		return "seguimiento/reclamo";
	}
	
}
