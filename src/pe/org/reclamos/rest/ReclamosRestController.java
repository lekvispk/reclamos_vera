package pe.org.reclamos.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.org.reclamos.dao.NotificacionesDAO;
import pe.org.reclamos.entidad.Notificacion;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.rest.bean.RespuestaRest;
import pe.org.reclamos.service.ReclamoService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/clientes")
public class ReclamosRestController {

	private static final Logger logger = Logger.getLogger( ReclamosRestController.class );
	
	@Autowired
	private ReclamoService reclamoService;
	@Autowired
	private NotificacionesDAO notificacionesDAO;
	
	/**
	 * Servicio que lista todos los reclamos que ha realizado un cliente
	 * http://localhost:8080/reclamos/rest/clientes/1/reclamos
	 * @param user id usuario de quien realizo los reclamos
	 * @param model otros datos
	 * @return
	 */
	@RequestMapping(value = "/{idCliente}/reclamos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody String listaReclamos(@PathVariable String idCliente, HttpServletRequest request, HttpServletResponse response) {
		final String METHODNAME = "listaReclamos - "; 
		logger.debug(METHODNAME + "INI");		
		logger.debug(METHODNAME + "usuario=" + idCliente+" listar reclamos");
		String json = "";
		try {
			
			response.setContentType("application/json; charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			Reclamo rec = new Reclamo();
			rec.setIdCliente( new Long(idCliente));
			List<Reclamo> lista = reclamoService.buscar( rec );
	
			String format = "yyyy-MM-dd";
			Gson gson2 = new GsonBuilder()
			   .setDateFormat( format ).create();
			
			json = gson2.toJson(lista);
		    	
		} catch (Exception e) {
			json = "";
		}
		logger.debug(METHODNAME + "FIN");	
	    return json;
	}
	
	/**
	 * Registro de reclamo
	 * http://localhost:8082/reclamos/rest/clientes/reclamos/
	 * @param user
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reclamos/", method = RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody RespuestaRest reclamoNuevo(@RequestBody Reclamo reclamo , HttpServletRequest request,  ModelMap model) {
		final String METHODNAME = "reclamoNuevo - ";
		logger.debug(METHODNAME + "registrar reclamo" );
		
		logger.debug(METHODNAME + "Rec " + reclamo.toString()  );
		
		RespuestaRest respuesta = new RespuestaRest();
		respuesta.setCodigo(1);
		respuesta.setMensaje("OK");
		
		return respuesta;
	}
	
	/**
	 * http://localhost:8082/reclamos/rest/clientes/1/reclamos/1
	 * @param user
	 * @param idReclamo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{user}/reclamos/{idReclamo}", method = RequestMethod.GET )
	public @ResponseBody String datosReclamo(@PathVariable String user,@PathVariable String idReclamo, ModelMap model) {
		logger.debug("usuario=" + user + " idreclamo=" + idReclamo) ;
		return "{'idReclamo':'1','fecha':'marzo'}";
	}
	
	/**
	 * http://localhost:8082/reclamos/rest/clientes/reclamos/1
	 * @param user
	 * @param idReclamo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reclamos/{idReclamo}", method = RequestMethod.GET )
	public @ResponseBody String reclamoDatos(@PathVariable String idReclamo, ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		String json = "";
		try {
			logger.debug(" idreclamo=" + idReclamo) ;
			response.setContentType("application/json;charset=ISO-8859-1");
	        request.setCharacterEncoding("UTF-8");
	        
			Reclamo reclamo = reclamoService.obtener( new Long(idReclamo));
			
			String format = "yyyy-MM-dd";
			Gson gson2 = new GsonBuilder()
			   .setDateFormat( format ).create();
			
			//Gson gson = new Gson();
			json = gson2.toJson(reclamo);
			
			
		} catch (Exception e) {
			logger.debug("Error: " + e.getMessage()) ;
			json = "{  \"status\":\"2\",  \"mensaje\":\""+e.getMessage()+"\" } ";
		}
		return json;
	}
	
	@RequestMapping(value = "/{idCliente}/notificaciones", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody Notificacion verNotificaciones(@PathVariable String idCliente , HttpServletRequest request,  ModelMap model) {
		final String METHODNAME = "verNotificaciones - ";
		logger.debug(METHODNAME + "INI" );
		
		Notificacion notificacion = new Notificacion();
		
		notificacion = notificacionesDAO.obtenernotificacionPorUsuario( Integer.valueOf(idCliente));
		
		return notificacion;
	}
	
	
}
