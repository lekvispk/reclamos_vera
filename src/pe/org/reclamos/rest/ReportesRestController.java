package pe.org.reclamos.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/reclamos/mes/atentido", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody String reclamosPorMesAtendidos(@PathVariable String idCliente, HttpServletRequest request, HttpServletResponse response) {
		final String METHODNAME = "reclamosPorMesAtendidos - "; 
		logger.debug(METHODNAME + "INI");
		
		String json = "";
		List<ReporteDataBean> reclamos = null;
		try {
			
			response.setContentType("application/json; charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			reclamos = new ArrayList<ReporteDataBean>();
			
			reclamos.add( new ReporteDataBean("ENE","20"));
			reclamos.add( new ReporteDataBean("FEB","10"));
			reclamos.add( new ReporteDataBean("MAR","15"));
			reclamos.add( new ReporteDataBean("ABR","35"));
			reclamos.add( new ReporteDataBean("MAY","40"));
			reclamos.add( new ReporteDataBean("JUN","20"));
			
			Gson gson2 = new GsonBuilder().create();
			
			json = gson2.toJson(reclamos);
		    	
		} catch (Exception e) {
			json = "";
		}
		logger.debug(METHODNAME + "FIN");	
	    return json;
	}
	
}
