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

import pe.org.reclamos.entidad.Devolucion;
import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.service.DevolucionService;
import pe.org.reclamos.service.FacturaService;
import pe.org.reclamos.utiles.Utiles;

@Controller
@Scope("session")
@RequestMapping(value="/producto")
public class ProductoController {

	private static final Logger logger = Logger.getLogger( ProductoController.class );
	
	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	private DevolucionService devolucionService;
	
	@RequestMapping(value="/autorizar.htm", method=RequestMethod.GET)
	public String preAutorizar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("preAutorizar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "producto/autorizar";
	}
	
	@RequestMapping(value="/autorizar.htm", method=RequestMethod.POST)
	public String autorizar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		String respuesta = "";
		 try {
			   logger.debug("autorizar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   String idReclamo = request.getParameter("idReclamo");
			   String fechaAutorizacion = request.getParameter("fechaAutorizacion");
			   String numeroActa = request.getParameter("numeroActa");
			   
			   Devolucion devolucion = new Devolucion();
			   devolucion.setNumeroActa(numeroActa);
			   devolucion.setFechaAutorizacion( Utiles.stringToDate( fechaAutorizacion , Utiles.FORMATO_FECHA_CORTA) );
			   devolucion.setReclamo( new Reclamo() );
			   devolucion.getReclamo().setIdReclamo(new Long(idReclamo));
			   devolucionService.grabar(devolucion);
			   
			   respuesta = "{ \"status\":\"1\", \"mensaje\":\"Autoriacion registrada\" } ";
			   response.getWriter().println( respuesta );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
			 respuesta = "";
			 try{ response.getWriter().println(respuesta); } 
			 catch(Exception e2){ }
		   }
		return null;
	}
	
	@RequestMapping(value="/seleccionar.htm", method=RequestMethod.GET)
	public String preSeleccionar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("preAutorizar");
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
		return "producto/seleccionar";
	}
	
	@RequestMapping(value="/seleccionar.htm", method=RequestMethod.POST)
	public String seleccionar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("autorizar");
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
		return "producto/seleccionar";
	}
	
}
