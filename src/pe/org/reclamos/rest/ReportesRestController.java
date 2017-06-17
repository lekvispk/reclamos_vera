package pe.org.reclamos.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.org.reclamos.rest.bean.ReporteDataBean;
import pe.org.reclamos.service.ReclamoService;

@Controller
@RequestMapping("/reportes")
public class ReportesRestController {

	private static final Logger logger = Logger.getLogger( ReportesRestController.class );
	
	@Autowired
	private ReclamoService reclamoService;
	
	/**
	 * http://localhost:8080/reclamos/rest/reportes/reclamos/mes/atentido 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/reclamos/mes/atentido", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody String reclamosPorMesAtendidos(HttpServletRequest request, HttpServletResponse response) {
		final String METHODNAME = "reclamosPorMesAtendidos - "; 
		logger.debug(METHODNAME + "INI");
		
		String json = "";
		List<ReporteDataBean> reclamos = null;
		try {
			
			response.setContentType("application/json; charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			reclamos = reclamoService.obtenerReclamosPorMesAtendidosAnioActual();			
			Gson gson2 = new GsonBuilder().create();			
			json = gson2.toJson(reclamos);
		    	
		} catch (Exception e) {
			json = "[]";
		}
		logger.debug(METHODNAME + "FIN");	
	    return json;
	}
	
	/**
	 * http://localhost:8080/reclamos/rest/reportes/reclamos/masrepetidos
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/reclamos/masrepetidos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody String masrepetidos(HttpServletRequest request, HttpServletResponse response) {
		final String METHODNAME = "masrepetidos - "; 
		logger.debug(METHODNAME + "INI");
		
		String json = "";
		List<ReporteDataBean> reclamos = null;
		try {
			
			response.setContentType("application/json; charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			reclamos = reclamoService.obtenerReclamosMasRepetidosEnElAnio();
			Gson gson2 = new GsonBuilder().create();			
			json = gson2.toJson(reclamos);
		    	
		} catch (Exception e) {
			json = "[]";
		}
		logger.debug(METHODNAME + "FIN");	
	    return json;
	}
	
	/**
	 * http://localhost:8080/reclamos/rest/reportes/reclamos/mes/noatendidos
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/reclamos/mes/noatendidos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody String noatendidos(HttpServletRequest request, HttpServletResponse response) {
		final String METHODNAME = "noatendidos - "; 
		logger.debug(METHODNAME + "INI");
		
		String json = "";
		List<ReporteDataBean> reclamos = null;
		try {
			
			response.setContentType("application/json; charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			reclamos = reclamoService.obtenerReclamosPorMesNoAtendidosEnElAnio();
			Gson gson2 = new GsonBuilder().create();			
			json = gson2.toJson(reclamos);
		    	
		} catch (Exception e) {
			json = "[]";
		}
		logger.debug(METHODNAME + "FIN");	
	    return json;
	}
	
	/**
	 * http://localhost:8080/reclamos/rest/reportes/reclamos/mes/porestado
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/reclamos/mes/porestado", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody String porestado(HttpServletRequest request, HttpServletResponse response) {
		final String METHODNAME = "porestado - "; 
		logger.debug(METHODNAME + "INI");
		
		String json = "";
		List<ReporteDataBean> reclamos = null;
		try {
			
			response.setContentType("application/json; charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			reclamos = reclamoService.obtenerReclamosPorEstadoMesActual();
			Gson gson2 = new GsonBuilder().create();			
			json = gson2.toJson(reclamos);
		    	
		} catch (Exception e) {
			json = "[]";
		}
		logger.debug(METHODNAME + "FIN");	
	    return json;
	}
	
}
