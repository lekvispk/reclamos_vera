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

import com.google.gson.Gson;

import pe.org.reclamos.entidad.Detallefactura;
import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.service.FacturaService;

@Controller
@RequestMapping("/clientes")
public class FacturasRestController {

	private static final Logger logger = Logger.getLogger( FacturasRestController.class );
	
	@Autowired
	private FacturaService facturaService;
	
	/**
	 * http://localhost:8082/reclamos/rest/clientes/1/facturas
	 * @param user id cliente
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{user}/facturas", method = RequestMethod.GET )
	public @ResponseBody String listarFacturas(@PathVariable String user, ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		logger.debug("usuario=" + user+" listar facturas");
		 String json = "";
		try {
			response.setContentType("application/json; charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			Long idCliente =  new Long (user) ;
			List<Factura> lista = facturaService.buscarFacturasNoUsadas( idCliente );
			
			Gson gson = new Gson();
		    json = gson.toJson(lista);
		    
		} catch (Exception e) {
			 json = "";
		}
		return json;
	}
	
	/**
	 * http://localhost:8082/reclamos/rest/clientes/1/facturas/1/items
	 * @param user
	 * @param factura
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{user}/facturas/{factura}/items", method = RequestMethod.GET )
	public @ResponseBody String listaItems(@PathVariable String user,@PathVariable String factura, ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		logger.debug(" factura =" + factura+" listar items");
		 String json = "";
		try {
			response.setContentType("application/json; charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			Detallefactura detalle = new Detallefactura();  
			detalle.setFactura( new  Factura() );
			detalle.getFactura().setIdFactura( new Long ( factura ));
			List<Detallefactura> lista = facturaService.listarDetalleFactura(detalle);
			
			Gson gson = new Gson();
		    json = gson.toJson(lista);
		    
		} catch (Exception e) {
			 json = "";
		}
		return json;
	}
	
}
