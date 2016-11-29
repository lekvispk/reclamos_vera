package pe.org.reclamos.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.reclamos.entidad.Cliente;
import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.entidad.Fideliza;
import pe.org.reclamos.entidad.Promocion;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.service.ClienteService;
import pe.org.reclamos.service.FacturaService;
import pe.org.reclamos.service.FidelizaService;
import pe.org.reclamos.service.ReclamoService;

/**
 * Clase que registra la fidelizacion realizada a los clientes
 * @author cvera
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
	@Autowired
	private FidelizaService fidelizaService;
	
	@RequestMapping(value="/lFidelizar.htm", method=RequestMethod.GET)
	public String lFidelizar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lFidelizar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   String msg = request.getParameter("msg"); 
			   if(!StringUtils.isEmpty( msg )){
				   if(msg.equals("1")){
					   model.put("mensaje", "Fidelizacion registrada" );
				   }
			   }
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   model.put("factura", new Factura() );
		   }
		return "fidelizar/lFidelizar";
	}

	/**
	 * Traer a los clientes que cumplan con lo siguiente : 
	 * 
	 * que el cliente haya relizado un reclamo y su reclamo ya haya sido atendido
	 * (atendido / aceptado o rechazado)
	 * La suma del monto de sus facturas del ultimo anio sea mayor a 5000
	 * 
	 * 
	 * @param factura
	 * @param result
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/lFidelizar.htm", method=RequestMethod.POST)
	public String lFidelizarPost(@Valid Factura factura, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lFidelizar Post");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   String err =request.getParameter("err"); 
			   if(!StringUtils.isEmpty( err )){
				   if(err.equals("1")){
					   model.put("msgError", "No se han encontrado resultados" );
				   }
			   }
			   model.put("lClientes", clienteService.buscarClientesParaFidelizacion() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			  model.put("factura", factura );
		   }
		return "fidelizar/lFidelizar";
	}
	
	@RequestMapping(value="/lCompensar.htm", method=RequestMethod.GET)
	public String lCompensar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lcompensar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   Cliente cliente = clienteService.obtener( new Long( request.getParameter("idCliente") ) ) ;
			   model.put("lFacturas", facturaService.buscarFacturasParaFidelizacionDeUnCliente( new Factura( cliente ) ));
			   
			   logger.debug( cliente.getNomCliente() );
			   Factura factura = new Factura();
			   factura.setCliente(cliente);
			   factura.setMonto(new BigDecimal(5000));
			   model.put("factura", factura);
			   
			   model.put("lFacturas", facturaService.buscarFacturasParaFidelizacionDeUnCliente( factura ));
			   
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
			   
			   factura.setIdFactura( null );
			   
			   model.put("lFacturas", facturaService.buscar( factura ));
			  
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "fidelizar/lCompensar";
	}
	
	/**
	 * graba si es que se va a compensar o no al cliente / factura indicada
	 * @param factura
	 * @param result
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/compensar.htm", method=RequestMethod.POST)
	public String compensar( HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("compensar ");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			  
			   Long idFactura = new Long( request.getParameter("idFactura"));
			   
			   Reclamo recDeFactura = reclamoService.obtenerPorIdFactura(idFactura);
			   Fideliza fideliza = new Fideliza();
			   fideliza.setReclamo(recDeFactura );
			   fideliza.setFecFideliza( new Date() );
			   fideliza.setCreatedAt(  new Date() );
			   fideliza.setEstado( 1 );
			   
			   fidelizaService.registrarFidelizacion(fideliza);
			   logger.debug("registrada la fidelizaion");
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		//return "fidelizar/lCompensar";
		return "redirect:/fidelizar/lFidelizar.htm?msg=1";
	}
	
	@RequestMapping(value="/lPromociones.htm", method=RequestMethod.GET)
	public String lPromociones(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		 try {
			   logger.debug("lPromociones");
			   
			   String err =request.getParameter("err"); 
			   if(!StringUtils.isEmpty( err )){
				   if(err.equals("1")){
					   model.put("msgError", "No se han encontrado resultados" );
				   }
			   }
			   
			   model.put("factura", new Factura() );
	   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
		return "fidelizar/lPromociones";
	}
	
	/**
	 * si existe una fidelizacion entonces redirige a la pantalla para fidelizar, si no, va a la pantalla para seguir buscando
	 * @param factura
	 * @param result
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/lPromociones.htm", method=RequestMethod.POST)
	public String lPromocionesPost(@Valid Factura factura, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lPromociones");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   //facturas que tengan registrado fidelizacion  
			   logger.debug("ruc ="+factura.getCliente().getRucCliente());
			   
			   Fideliza fi =  fidelizaService.obtenerFidelizacionPorCliente(factura);
			   if( fi!= null ){
				   logger.debug("idFideliza ="+ fi.getIdFideliza() );
				   return "redirect:/fidelizar/promociones.htm?idFideliza=" + fi.getIdFideliza();   
			   }
			   return "redirect:/fidelizar/lPromociones.htm?err=1";  
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
			 return "redirect:/fidelizar/lPromociones.htm?err=1";
		   }finally{
			//  model.put("reclamo", new Reclamo() );
		   }
	}
	
	@RequestMapping(value="/promociones.htm", method=RequestMethod.GET)
	public String preGrabarPromociones(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		try {
			logger.debug("preGrabarPromociones");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			   
			Integer idFideliza = Integer.parseInt( request.getParameter("idFideliza") );
			Fideliza fi = fidelizaService.obtenerFidelizacion( new Long(idFideliza) ); 
			Factura f = fi.getReclamo().getFactura();
			model.put("fideliza", fi );
			model.put("factura", f );
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Error: "+ e.getMessage() );
		}
		return "fidelizar/promocion";
	}
	
	@RequestMapping(value="/promociones.htm", method=RequestMethod.POST)
	public String grabarPromociones(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		try {
			logger.debug("grabarPromociones");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			   
			String[] lFacturas = request.getParameterValues("_chk");
			for( String fact : lFacturas){
				logger.debug("factura " + fact );
				//obtener la fidelizacion de esa factura 
				 
				Promocion p = new Promocion();
				p.setIdPromocion( Integer.parseInt( request.getParameter("idPromocion")));
				Fideliza f = new Fideliza();
				f.setPromocion(p);
				f.setUpdatedAt( new Date());
				
				//actualizar la fidelizacion de esa factura
					
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Error: "+ e.getMessage() );
		}
		return "redirect:/fidelizar/lPromociones.htm";
	}
	
	
}
