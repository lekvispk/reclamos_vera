package pe.org.reclamos.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.service.ClienteService;
import pe.org.reclamos.service.FacturaService;
import pe.org.reclamos.service.ReclamoService;
import pe.org.reclamos.utiles.Utiles;

/**
 * Clase que registra la fidelizacion realizada a los clientes
 * @author ElvisRubén
 *
 */
@Controller
@RequestMapping(value="/fidelizar")
public class FidelizarController {

	private static final Logger logger = Logger.getLogger(FidelizarController.class );

	@Autowired
	private ReclamoService reclamoService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private FacturaService facturaService;

	@RequestMapping(value="/lFidelizar.htm", method=RequestMethod.GET)
	public String lFidelizar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lFidelizar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   Factura fac = new Factura();
			   //TODO pendiente modificar para que la paginacion funcione
			   model.put("lFacturas", facturaService.buscar( fac ));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   model.put("factura", new Factura() );
		   }
		return "reclamos/lFidelizar";
	}

	@RequestMapping(value="/lFidelizar.htm", method=RequestMethod.POST)
	public String lFidelizarPost(@Valid Factura factura, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lFidelizar Post");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   if(!Utiles.nullToBlank( request.getParameter("fecFactura")).equals("") ){}
		   			factura.setFecFactura( Utiles.stringToDate( request.getParameter("fecFactura") , "dd/MM/yyyy")) ;
		  		
			   if(!Utiles.nullToBlank( request.getParameter("fecFacturaFin")).equals("") ){}
		   			factura.setFecFacturaFin( Utiles.stringToDate( request.getParameter("fecFacturaFin") , "dd/MM/yyyy")) ;
		  			  
			   model.put("lFacturas", facturaService.buscar( factura ));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			  model.put("factura", factura );
		   }
		return "reclamos/lFidelizar";
	}
	
	@RequestMapping(value="/lCompensar.htm", method=RequestMethod.GET)
	public String lCompensar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lcompensar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   Factura fac = facturaService.obtener( new Long( request.getParameter("idFactura") ) ) ;
			   
			   model.put("lFacturas", facturaService.buscar( new Factura( fac.getCliente() ) ));
			   
			   logger.debug( fac.getDescripcion() );
			   model.put("factura", fac );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "fidelizar/lCompensar";
	}
	
	@RequestMapping(value="/lCompensar.htm", method=RequestMethod.POST)
	public String lCompensarPost(@Valid Factura factura, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lcompensar Post");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			  
			   model.put("factura", facturaService.obtener( new Long( factura.getIdFactura() ) ));
			   
			   factura.setIdFactura(  null );
			   
			   model.put("lFacturas", facturaService.buscar( factura ));
			  
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "fidelizar/lCompensar";
	}
	
	@RequestMapping(value="/compensar.htm", method=RequestMethod.GET)
	public String compensar(@Valid Factura factura, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("compensar ");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			  
			   Factura fac = facturaService.obtener( new Long( request.getParameter("idFactura")  ) );
			   fac.setEstado(2);
			   facturaService.registrar(fac);
			   
			   model.put("lFacturas", facturaService.buscar( factura ));
			   model.put("factura", facturaService.obtener( new Long( factura.getIdFactura() ) ));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "fidelizar/lCompensar";
	}
	
	@RequestMapping(value="/lPromociones.htm", method=RequestMethod.GET)
	public String lPromociones(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lPromociones");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lFacturas", new ArrayList<Factura>());
			   
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "fidelizar/lPromociones";
	}
	
	@RequestMapping(value="/lPromociones.htm", method=RequestMethod.POST)
	public String lPromocionesPost(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lPromociones");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lFacturas", facturaService.buscar( new Factura( ) ));
			   
			   model.put("factura", new Factura() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "fidelizar/lPromociones";
	}
	
	@RequestMapping(value="/promociones.htm", method=RequestMethod.POST)
	public String grabarPromociones(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("grabarPromociones");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lFacturas", facturaService.buscar( new Factura( ) ));
			   model.put("factura", new Factura() );
			   model.put("mensaje", "Datos grabados");
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "fidelizar/lPromociones";
	}
	
	
}
