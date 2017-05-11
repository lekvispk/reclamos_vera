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

import pe.org.reclamos.entidad.Cliente;
import pe.org.reclamos.service.ClienteService;

import com.google.gson.Gson;

@Controller
@RequestMapping("/clientes")
public class ClientesRestController {

	private static final Logger logger = Logger.getLogger( ClientesRestController.class );
	
	@Autowired
	ClienteService clienteService;
	
	/**
	 * http://localhost:8082/reclamos/rest/clientes/1
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{user}", method = RequestMethod.GET )
	public @ResponseBody String datosCliente(@PathVariable String user, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("cliente = " + user+" ");
		String json = "";
		try {
			response.setContentType("application/json; charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			Cliente cliente = clienteService.obtener( new Long(user));
			logger.debug("cliente: " +cliente);
			Gson gson = new Gson();
		    json = gson.toJson(cliente);
		    
		} catch (Exception e) {
			logger.debug("Error: " +e.getMessage());
			json = "";
		}
		return json;
	}
	
	/**
	 * http://localhost:8080/reclamos/rest/clientes/ruc/0000000
	 * @param ruc
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ruc/{ruc}", method = RequestMethod.GET )
	public @ResponseBody String obtenerClientePorRuc(@PathVariable String ruc, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("obtenerClientePorRuc = " + ruc +" ");
		String json = "";
		try {
			response.setContentType("application/json; charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			Cliente cliente = clienteService.obtenerPorRUC( ruc );
			logger.debug("cliente: " +cliente);
			Gson gson = new Gson();
		    json = gson.toJson(cliente);
		    
		} catch (Exception e) {
			logger.debug("Error: " +e.getMessage());
			json = "";
		}
		return json;
	}
	
}
