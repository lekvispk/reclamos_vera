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

import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.service.FacturaService;

@Controller
@Scope("session")
@RequestMapping(value="/capacitacion")
public class CapacitacionesController {

	private static final Logger logger = Logger.getLogger( CapacitacionesController.class );
	
	@Autowired
	private FacturaService facturaService;
	
	@RequestMapping(value="/lCapacitaciones.htm", method=RequestMethod.GET)
	public String listaCapacitaciones(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("listaCapacitaciones");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "capacitacion/lCapacitaciones";
	}
	
	@RequestMapping(value="/lCapacitaciones.htm", method=RequestMethod.POST)
	public String listaCapacitacionesPost(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("listaCapacitaciones");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lFacturas", facturaService.buscar( new Factura() ));
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "capacitacion/lCapacitaciones";
	}
	
	@RequestMapping(value="/preCapacitacion.htm", method=RequestMethod.POST)
	public String preCapacitacion(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("listaCapacitaciones");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "capacitacion/capacitacion";
	}
	
	@RequestMapping(value="/lPosponer.htm", method=RequestMethod.GET)
	public String listaPosponer(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lPosponer");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "capacitacion/lPosponer";
	}
	
	@RequestMapping(value="/lPosponer.htm", method=RequestMethod.POST)
	public String listaPosponerPost(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("listaPosponerPost");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lFacturas", facturaService.buscar( new Factura() ));
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "capacitacion/lPosponer";
	}
	
	@RequestMapping(value="/posponer.htm", method=RequestMethod.GET)
	public String prePosponer(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("prePosponer");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   String idCapacitacion = request.getParameter("id");
			   
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "capacitacion/posponer";
	}
	
	@RequestMapping(value="/posponer.htm", method=RequestMethod.POST)
	public String posponer(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("prePosponer");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
			 return "capacitacion/posponer";
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "capacitacion/lPosponer";
	}
	
	
	@RequestMapping(value="/lCapacitador.htm", method=RequestMethod.GET)
	public String lCapacitador(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lPosponer");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "capacitacion/lAsignaCapacitador";
	}
	
	@RequestMapping(value="/lCapacitador.htm", method=RequestMethod.POST)
	public String lCapacitadorPost(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("listaPosponerPost");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lFacturas", facturaService.buscar( new Factura() ));
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "capacitacion/lAsignaCapacitador";
	}
	
}