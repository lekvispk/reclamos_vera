package pe.org.reclamos.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.org.reclamos.entidad.DetalleDevolucion;
import pe.org.reclamos.entidad.Detallefactura;
import pe.org.reclamos.entidad.Devolucion;
import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.service.DevolucionService;
import pe.org.reclamos.service.FacturaService;
import pe.org.reclamos.utiles.CalendarSerializer;
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
			   logger.debug("preSeleccionar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
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
	public String seleccionar(@Valid Factura factura, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("seleccionar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   Detallefactura df = new Detallefactura();
			   df.setFactura(factura);
			   List<Detallefactura> lista = null;
			   lista = facturaService.listarDetalleFactura( df );
			   model.put("lProductos", lista );
			   model.put("factura", new Factura() );
			   
			   if( lista != null ){
				   Integer idDetalleFactura =  null;
				   for(Detallefactura dd : lista){
					   idDetalleFactura  = dd.getIdDetalleFactura();
				   }
				   if( idDetalleFactura != null ){
					   Devolucion dev = devolucionService.obtenerPorDetalleFactura( Integer.valueOf( idDetalleFactura) );
					   //String idDevolucion = request.getParameter("idDevolucion");
					   model.put("lProductosDevoluciones", devolucionService.listarDetalleDevolucion( dev.getIdDevolucion() ) );
				   }
			   }
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }
		return "producto/seleccionar";
	}
	
	@RequestMapping(value="/agregar.htm", method=RequestMethod.POST)
	public String agregarItem(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		String json = "";
		try {
			   logger.debug("agregarItem");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   String idDetalleFactura = request.getParameter("idDetalleFactura");
			   
			   Devolucion dev = devolucionService.obtenerPorDetalleFactura( Integer.valueOf( idDetalleFactura) );
			   logger.debug("idDevolucion="+dev.getIdDevolucion());
			   Detallefactura df = facturaService.obtenerDetalleFactura( Integer.valueOf( idDetalleFactura) );
			   logger.debug("idDevolucion="+df.getIdDetalleFactura());
			   logger.debug("idProducto="+df.getProducto().getIdProducto());
			   DetalleDevolucion dd = new DetalleDevolucion();
			   dd.setDevolucion( dev );
			   dd.setProducto( df.getProducto() );
			   
			   devolucionService.grabarDetalle( dd );
			   
			   String format = "yyyy-MM-dd";
			   
			   GsonBuilder gsonB = new GsonBuilder();
			   gsonB.registerTypeAdapter(Calendar.class, new CalendarSerializer());
			   gsonB.registerTypeAdapter(GregorianCalendar.class, new CalendarSerializer());
			   Gson gson2 = gsonB.setDateFormat( format ).create();
			   
			   json = gson2.toJson( dd );
			   //json = "{  \"status\":\"1\",  \"mensaje\":\"Gabado\" } ";
			   response.getWriter().println( json );
			   
		   } catch (Exception e) {
			   logger.debug("Error : "+e.getMessage());
			 json = "{  \"status\":\"2\",  \"mensaje\":\""+e.getMessage()+"\" } ";
			 try{ response.getWriter().println( json ); } 
			 catch(Exception e2){ }
		   }
		return null;
	}
	
	//pantalla seleccion cambio de producto
	//
	@RequestMapping(value="/listaProductosAgregados.htm", method=RequestMethod.GET)
	public String listaProductosAgregados( HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("listaProductosAgregados");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   String idDevolucion = request.getParameter("idDevolucion");
			   model.put("lProductosDevoluciones", devolucionService.listarDetalleDevolucion(Integer.valueOf( idDevolucion )) );
			    
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }
		return "producto/lProductos";
	}
}
