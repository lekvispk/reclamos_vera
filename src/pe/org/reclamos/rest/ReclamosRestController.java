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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.service.ReclamoService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/clientes")
public class ReclamosRestController {

	private static final Logger logger = Logger.getLogger( ReclamosRestController.class );
	
	@Autowired
	ReclamoService reclamoService;
	
	/**
	 * Servicio que lista todos los reclamos que ha realizado un cliente
	 * http://localhost:8082/reclamos/rest/clientes/1/reclamos
	 * @param user id usuario de quien realizo los reclamos
	 * @param model otros datos
	 * @return
	 */
	@RequestMapping(value = "/{user}/reclamos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody String listaReclamos(@PathVariable String user, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("usuario=" + user+" listar reclamos");
		String json = "";
		try {
			
			response.setContentType("application/json; charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			Reclamo rec = new Reclamo();
			rec.setIdCliente( new Long(user));
			List<Reclamo> lista = reclamoService.buscar( rec );
			
			//String format = "EEE, dd MMM yyyy HH:mm:ss zzz";
			String format = "yyyy-MM-dd";
			Gson gson2 = new GsonBuilder()
			   .setDateFormat( format ).create();
			
			//Gson gson = new Gson();
			json = gson2.toJson(lista);
		    	
		} catch (Exception e) {
			json = "";
		}
		
	    return json;
		//return "[{'idReclamo':'1','fecha':'marzo'},{'idReclamo':'2','fecha':'abril'}]";
	}
	
	/**
	 * http://localhost:8082/reclamos/rest/clientes/1/reclamos/
	 * @param user
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{user}/reclamos/", method = RequestMethod.POST )
	public @ResponseBody String reclamoNuevo(@PathVariable String user,HttpServletRequest request,  ModelMap model) {
		logger.debug("registrar reclamo" );
		return "{'idReclamo':'1','fecha':'marzo','status':'ok','errorMsg':''}";
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
	
	
}
