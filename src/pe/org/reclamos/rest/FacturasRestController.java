package pe.org.reclamos.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.org.reclamos.entidad.Cliente;
import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.service.FacturaService;

import com.google.gson.Gson;

@Controller
@RequestMapping("/")
public class FacturasRestController {

	private static final Logger logger = Logger.getLogger( FacturasRestController.class );
	
	@Autowired
	FacturaService facturaService;
	
	//http://localhost:8082/reclamos/rest/1/facturas/
	@RequestMapping(value = "/{user}/facturas/", method = RequestMethod.GET )
	public @ResponseBody String listaReclamos(@PathVariable String user, ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		logger.debug("usuario=" + user+" listar facturas");
		 String json = "";
		try {
			response.setContentType("application/json;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			Factura fact = new Factura();
			fact.setCliente( new Cliente() );
			fact.getCliente().setIdCliente( new Long (user));
			List<Factura> lista = facturaService.buscar( fact );
			
			Gson gson = new Gson();
		    json = gson.toJson(lista);
		    
		} catch (Exception e) {
			 json = "";
		}
		return json;
	}
	
}
