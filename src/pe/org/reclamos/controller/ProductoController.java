package pe.org.reclamos.controller;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
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

import pe.org.reclamos.entidad.Despachador;
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
					   if( dev != null){
						   //String idDevolucion = request.getParameter("idDevolucion");
						   model.put("lProductosDevoluciones", devolucionService.listarDetalleDevolucion( dev.getIdDevolucion() ) );   
					   }
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
		final String METHODNAME = "agregarItem - ";
		logger.debug(METHODNAME + "INI");
		
		String json = "";
		try {
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   String idDetalleFactura = request.getParameter("idDetalleFactura");
			   logger.debug(METHODNAME + "idDetalleFactura=" + idDetalleFactura);
			   
			   Devolucion dev = devolucionService.obtenerPorDetalleFactura( Integer.valueOf( idDetalleFactura) );
			   
			   if( dev != null){
				   logger.debug(METHODNAME + "idDevolucion="+dev.getIdDevolucion());
				   Detallefactura df = facturaService.obtenerDetalleFactura( Integer.valueOf( idDetalleFactura) );
				   logger.debug(METHODNAME + "idDevolucion="+df.getIdDetalleFactura());
				   logger.debug(METHODNAME + "idProducto="+df.getProducto().getIdProducto());
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
				  
			   }else{
				   json = "{  \"status\":\"2\",  \"mensaje\":\"No se ha encontrado autrizacion de cambio\",  \"idDetalleDevolucion\":\"-1\" } ";
			   }
			   response.getWriter().println( json );
			   
		   } catch (Exception e) {
			   e.printStackTrace();
			   logger.debug("Error : "+e.getMessage());
			   json = "{  \"status\":\"2\",  \"mensaje\":\""+e.getMessage()+"\" } ";
			   try{ response.getWriter().println( json ); } 
			   catch(Exception e2){ }
		   }
		logger.debug(METHODNAME + "FIN");
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
	
	@RequestMapping(value="/asignar.htm", method=RequestMethod.GET)
	public String preAsignar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("preAsignar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "producto/asignar";
	}
	
	@RequestMapping(value="/asignar.htm", method=RequestMethod.POST)
	public String asignar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("asignar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   String dia = request.getParameter("dia");
			   logger.debug("dia " + dia);
			   Date date = new Date();
			   Calendar lunes = Calendar.getInstance(); 
			   lunes.setTime(date);
			   logger.debug(" dia actual = " + lunes.get(Calendar.DAY_OF_WEEK) ) ;
			   lunes.set( Calendar.DAY_OF_WEEK , Integer.valueOf( dia ));
			   logger.debug(" dia modificado = " + lunes.get(Calendar.DAY_OF_WEEK) ) ;
			   logger.debug(" fecha modificada = " + Utiles.DateToString( lunes.getTime() , "dd/MM/yyyy") );
			   
			   //Date fecha = Utiles.stringToDate( request.getParameter("fecha"), "dd/MM/yyyy") ; 
			   logger.debug("hora " + request.getParameter("hora"));
			   java.sql.Time hora = Time.valueOf(request.getParameter("hora"));
			   
			   Integer idDetalleDevolucion = Integer.valueOf( request.getParameter("idDetalleDevolucion")); 
			   DetalleDevolucion detDev = devolucionService.obtenerDetalleDevolucion( idDetalleDevolucion );
			   Despachador des = new Despachador();
			   des.setIdDespachador( Integer.valueOf( request.getParameter("idDespachador")));
			   detDev.setDespachador( des );
			   detDev.setFechaEntrega( lunes.getTime() );
			   detDev.setHoraEntrega( hora );
			   
			   devolucionService.grabarDetalle( detDev );
			   
			   String respuesta = "{ \"status\":\"1\", \"mensaje\":\"Asignacion registrada\" } ";
			   response.getWriter().println( respuesta );
			 
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
			 String json = "{  \"status\":\"2\",  \"mensaje\":\""+e.getMessage()+"\" } ";
			 try{ response.getWriter().println( json ); } 
			 catch(Exception e2){ }
		   }
		return null;
	}
	
	@RequestMapping(value="/buscarProductosParaAsignarDespachador.htm", method=RequestMethod.GET)
	public String buscarProductosParaAsignarDespachador(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("buscarProductosParaAsignarDespachador");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("factura", new Factura() );
			   model.put("lProductos", devolucionService.listarProductosParaDevolver() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }
		return "producto/asignar";
	}
	
	@RequestMapping(value="/listaDespachadores.htm", method=RequestMethod.GET)
	public String listaDespachadores(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("listaDespachadores");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("factura", new Factura() );
			   model.put("lDespachadores", devolucionService.listarDespachadores() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }
		return "producto/asignar";
	}
	
}
