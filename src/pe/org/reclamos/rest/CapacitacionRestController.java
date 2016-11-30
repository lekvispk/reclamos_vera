package pe.org.reclamos.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.org.reclamos.entidad.CapacitacionItem;
import pe.org.reclamos.service.CapacitacionService;

import com.google.gson.Gson;

@Controller
@RequestMapping("/capacitacion")
public class CapacitacionRestController {

	private static final Logger logger = Logger.getLogger( CapacitacionRestController.class );
	
	@Autowired
	private CapacitacionService capacitacionService;
	
	@RequestMapping(value = "/{idCapacitacion}/detalleItem/{idDetalleFactura}", method = RequestMethod.GET )
	public @ResponseBody String detalleItem(@PathVariable String idCapacitacion, @PathVariable String idDetalleFactura , HttpServletRequest request, HttpServletResponse response) {
		logger.debug("capacitacion = " + idCapacitacion +" item= " + idDetalleFactura );
		String json = "";
		try {
			response.setContentType("application/json; charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			CapacitacionItem ci = capacitacionService.obtenerCapacitacionItem( new Long( idCapacitacion ) , new Long(idDetalleFactura) );
			if( ci == null){
				ci = new CapacitacionItem();
				ci.setEstado(-1);
			}
			Gson gson = new Gson();
		    json = gson.toJson( ci );
		    
		} catch (Exception e) {
			 json = "";
		}
		return json;
	}
	
}
