package pe.org.reclamos.controller;

import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;

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

import pe.org.reclamos.entidad.Capacitacion;
import pe.org.reclamos.entidad.CapacitacionItem;
import pe.org.reclamos.entidad.Cliente;
import pe.org.reclamos.entidad.Detallefactura;
import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.service.CapacitacionService;
import pe.org.reclamos.service.FacturaService;
import pe.org.reclamos.utiles.Utiles;

@Controller
@Scope("session")
@RequestMapping(value="/capacitacion")
public class CapacitacionesController {

	private static final Logger logger = Logger.getLogger( CapacitacionesController.class );
	
	@Autowired
	private FacturaService facturaService;
	@Autowired
	private CapacitacionService capacitacionService;
	
	@RequestMapping(value="/lCapacitaciones.htm", method=RequestMethod.GET)
	public String listaCapacitaciones(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		try {
		   logger.debug("listaCapacitaciones");
		   response.setContentType("text/html;charset=ISO-8859-1");
		   request.setCharacterEncoding("UTF8");
		   
		  /* Factura f = new Factura();
		   f.setCliente(cliente);
		   model.put("lFacturas", facturaService.buscarFacturasConReclamos( f ));*/
		   model.put("cliente", new Cliente() );
		   
		} catch (Exception e) {
		 e.printStackTrace();
		 model.put("msgError", "Error: "+ e.getMessage() );
		}finally{
			//model.put("reclamo", new Reclamo() );
		}
		return "capacitacion/lCapacitaciones";
	}
	
	@RequestMapping(value="/lCapacitaciones.htm", method=RequestMethod.POST)
	public String listaCapacitacionesPost(@Valid Cliente cliente, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		final String METHODNAME = "listaCapacitacionesPost - ";
		logger.debug(METHODNAME + "INI");
		 try {
			   
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   Factura f = new Factura();
			   f.setCliente(cliente);
			   
			   model.put("lFacturas", facturaService.buscarFacturasConReclamos( f ));
			   model.put("cliente", cliente );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   logger.debug(METHODNAME + "FIN");
		   }
		return "capacitacion/lCapacitaciones";
	}
	
	@RequestMapping(value="/preCapacitacion.htm", method=RequestMethod.POST)
	public String preCapacitacion(HttpServletRequest request, HttpServletResponse response, ModelMap model){
	
	 try {
		logger.debug("preCapacitacion");
	    response.setContentType("text/html;charset=ISO-8859-1");
	    request.setCharacterEncoding("UTF8");
	   
	    String idFactura = request.getParameter("idFactura");
	    Factura f = facturaService.obtener( new Long(idFactura) );
	    Detallefactura df = new Detallefactura();
	    df.setFactura( f );
	    
	    //idFactura
	    Capacitacion cap = null;
	    cap = capacitacionService.obtenerPorFactura( Integer.valueOf(idFactura) );
	    if( cap == null ){
	    	cap = new Capacitacion();	
	    	cap.setFactura( f );
		    capacitacionService.grabar( cap );
	    }
	    
	    model.put("factura", f );
	    model.put("capacitacion", cap );
	    model.put("lItems", facturaService.listarDetalleFactura( df ) );
		   
	   } catch (Exception e) {
		 e.printStackTrace();
		 model.put("msgError", "Error: "+ e.getMessage() );
	   }
	   return "capacitacion/capacitacion";
	}
	
	@RequestMapping(value="/capacitacion.htm", method=RequestMethod.POST)
	public String capacitacion(HttpServletRequest request, HttpServletResponse response, ModelMap model){
	try {
		logger.debug("capacitacion");
	    response.setContentType("text/html;charset=ISO-8859-1");
	    request.setCharacterEncoding("UTF8");
	   
	    String idFactura = request.getParameter("idFactura");
	    Factura f = facturaService.obtener( new Long(idFactura) );
	    Detallefactura df = new Detallefactura();
	    df.setFactura( f );
	    model.put("factura", f );
	    model.put("lItems", facturaService.listarDetalleFactura( df ) );
		   
	   } catch (Exception e) {
		 e.printStackTrace();
		 model.put("msgError", "Error: "+ e.getMessage() );
	   }
	   return "capacitacion/capacitacion";
	}
	
	@RequestMapping(value="/updateCapacitacion.htm", method=RequestMethod.POST)
	public String updateCapacitacion(@Valid Capacitacion capacitacion, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
	try {
		logger.debug("updateCapacitacion");
	    response.setContentType("text/html;charset=ISO-8859-1");
	    request.setCharacterEncoding("UTF8");
	    
	    Capacitacion cp2 = capacitacionService.obtener( new Long(capacitacion.getIdCapacitacion()) );
	    
	    cp2.setFechaCapacitacion( Utiles.stringToDate( request.getParameter("fechaCapacitacion"), Utiles.FORMATO_FECHA_CORTA));
	    cp2.setHoraCapacitacion( new Time( Utiles.stringToDate( request.getParameter("horaCapacitacion"), "hh:mm a" ).getTime() ) );
	    
	    capacitacionService.grabar( cp2 );
	    
	   } catch (Exception e) {
		 e.printStackTrace();
		 model.put("msgError", "Error: "+ e.getMessage() );
	   }
	   //return "capacitacion/capacitacion";
	   return "redirect:/capacitacion/lCapacitaciones.htm";
	}
	
	/**
	 * via ajax
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/grabarDetalle.htm", method=RequestMethod.POST)
	public String grabarDetalle(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		CapacitacionItem cap = null;
		PrintWriter out = null;
		try {
			logger.debug("capacitacion");
		    response.setContentType("text/html;charset=ISO-8859-1");
		    request.setCharacterEncoding("UTF8");
		    out = response.getWriter();
		    
		    String idCapacitacionItem = request.getParameter("idCapacitacionItem");
		    String idCapacitacion = request.getParameter("idCapacitacion");
		    String idDetalleFactura = request.getParameter("idDetalleFactura");
		    String detalle = request.getParameter("detalle");
		    
		    if( !Utiles.nullToBlank( idCapacitacionItem).equals("") ){
		    	cap = capacitacionService.obtenerCapacitacionItem ( new Long(idCapacitacionItem) );	
		    	cap.setUpdatedAt( new Date() );
		    }else{
		    	cap = new CapacitacionItem();
		    	cap.setCapacitacion( new Capacitacion( Integer.valueOf( idCapacitacion )));
		    	cap.setDetallefactura( new Detallefactura( Integer.valueOf( idDetalleFactura )));
		    	cap.setEstado(1);
		    	cap.setCreatedAt( new Date());
		    }
		    cap.setDetalle(detalle);
		    capacitacionService.registrarCapacitacionItem( cap );
		    out.println("{ \"status\" : \"1\" }");
		    
	   } catch (Exception e) {
		   out.println("{ \"status\" : \"-1\" }");
		   e.printStackTrace();
		   model.put("msgError", "Error: "+ e.getMessage() );
	   } finally {
           out.close();
       }
	   return null;
	}
	
	
	@RequestMapping(value="/lPosponer.htm", method=RequestMethod.GET)
	public String listaPosponer(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lPosponer");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("capacitacion", new Capacitacion() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "capacitacion/lPosponer";
	}
	
	@RequestMapping(value="/lPosponer.htm", method=RequestMethod.POST)
	public String listaPosponerPost(@Valid Capacitacion capacitacion, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("listaPosponerPost");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lCapacitacion", capacitacionService.buscarCapacitaciones( capacitacion ) );
			   model.put("capacitacion", new Capacitacion() );
			   
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
			   Capacitacion cap = capacitacionService.obtener( new Long(idCapacitacion) );
			   model.put("capacitacion", cap );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "capacitacion/posponer";
	}
	
	@RequestMapping(value="/posponer.htm", method=RequestMethod.POST)
	public String posponer(@Valid Capacitacion capacitacion, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("prePosponer");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   Capacitacion cap = capacitacionService.obtener( new Long(capacitacion.getIdCapacitacion()) );

			   cap.setFechaCapacitacion( Utiles.stringToDate( request.getParameter("fechaCapacitacion"), Utiles.FORMATO_FECHA_CORTA));
			   cap.setHoraCapacitacion( new Time( Utiles.stringToDate( request.getParameter("horaCapacitacion"), "hh:mm a" ).getTime() ) );
			   cap.setMotivoPospuesto( capacitacion.getMotivoPospuesto() );
			    
			   capacitacionService.grabar( cap );
			   //model.put("capacitacion", new Factura() );
			   return "redirect:/capacitacion/lPosponer.htm";
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
			 return "capacitacion/posponer";
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		
	}
	
	
	@RequestMapping(value="/lCapacitador.htm", method=RequestMethod.GET)
	public String lCapacitador(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lPosponer");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lCapacitador", capacitacionService.listarCapacitador());
			   model.put("cliente", new Cliente() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "capacitacion/lAsignaCapacitador";
	}
	
	@RequestMapping(value="/lCapacitador.htm", method=RequestMethod.POST)
	public String lCapacitadorPost(@Valid Cliente cliente, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("listaPosponerPost");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   Capacitacion cap = new Capacitacion();
			   cap.setFactura( new Factura());
			   cap.getFactura().setCliente(cliente);
			   model.put("lCapacitaciones", capacitacionService.buscarCapacitaciones( cap ) );
			   
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   model.put("lCapacitador", capacitacionService.listarCapacitador());
			   model.put("cliente", new Cliente() );
		   }
		return "capacitacion/lAsignaCapacitador";
	}
	
	@RequestMapping(value="/asignarCapacitador.htm", method=RequestMethod.POST)
	public String asignarCapacitadorPost( HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("asignarCapacitadorPost");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   //asignar capacitador
			   Integer idCapacitacion = Integer.valueOf( request.getParameter( "idCapacitacion") ); 
			   Integer idCapacitador = Integer.valueOf( request.getParameter( "idCapacitador") );
			   capacitacionService.asignarCapacitador( idCapacitacion, idCapacitador);
			   logger.debug("idCapacitacion="+  idCapacitacion);
			   logger.debug("idCapacitador=" +  idCapacitador);
			   response.getWriter().println("{ \"status\":\"1\", \"mensaje\":\"capacitador asignado\" } ");
		   } catch (Exception e) {
			logger.debug("Error: " + e.getMessage());
			try {
				response.getWriter().println("{  \"status\":\"2\",  \"mensaje\":\""+e.getMessage()+"\" } ");
			} catch (Exception e2) {
				
			}
		   }
		return null;
	}
	
}
