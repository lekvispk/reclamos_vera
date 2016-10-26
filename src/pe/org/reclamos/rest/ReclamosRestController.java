package pe.org.reclamos.rest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class ReclamosRestController {

	private static final Logger logger = Logger.getLogger( ReclamosRestController.class );
	
	//http://localhost:8082/reclamos/rest/elvis/reclamos/
	@RequestMapping(value = "/{user}/reclamos/", method = RequestMethod.GET )
	public @ResponseBody String reclamo(@PathVariable String user, ModelMap model) {
		logger.debug("usuario=" + user+" listar reclamos");
		return "[{'idReclamo':'1','fecha':'marzo'},{'idReclamo':'2','fecha':'abril'}]";
	}
	
	//http://localhost:8082/reclamos/rest/elvis/reclamos/1
	@RequestMapping(value = "/{user}/reclamos/{idReclamo}", method = RequestMethod.GET )
	public @ResponseBody String listReclamos(@PathVariable String user,@PathVariable String idReclamo, ModelMap model) {
		logger.debug("usuario=" + user + " idreclamo=" + idReclamo) ;
		return "{'idReclamo':'1','fecha':'marzo'}";
	}
	
	
}
