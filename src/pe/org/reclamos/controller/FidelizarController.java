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
import pe.org.reclamos.entidad.ItemsReclamo;
import pe.org.reclamos.entidad.Promocion;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.service.ClienteService;
import pe.org.reclamos.service.FacturaService;
import pe.org.reclamos.service.FidelizaService;
import pe.org.reclamos.service.PromocionService;
import pe.org.reclamos.service.ReclamoService;
import pe.org.reclamos.utiles.Mail;
import pe.org.reclamos.utiles.Utiles;

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
	@Autowired
	private PromocionService promocionService;
	@Autowired
	private Mail mail ;		
	
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
			   String ruc = Utiles.nullToBlank(  factura.getCliente().getRucCliente() );
			   logger.debug("RUC = " + ruc);
			   model.put("lClientes", clienteService.buscarClientesParaFidelizacion( ruc ) );
			   
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
		final String METHODNAME = "lCompensar - ";
		logger.debug(METHODNAME + "INI");
		 try {
			   
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   Cliente cliente = clienteService.obtener( new Long( request.getParameter("idCliente") ) ) ;
			   logger.debug( METHODNAME + cliente.toString());
			   
			   //model.put("lFacturas", facturaService.buscarFacturasParaFidelizacionDeUnCliente( new Factura( cliente ) ));
			   
			   Factura factura = new Factura();
			   factura.setCliente(cliente);
			   factura.setMonto(new BigDecimal(5000));
			   model.put("factura", factura);
			   
			   model.put("lFacturas", facturaService.buscarFacturasParaFidelizacionDeUnCliente( factura ));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   logger.debug(METHODNAME + "FIN");
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
	
	@RequestMapping(value = "/lPromociones.htm", method = RequestMethod.GET)
	public String lPromociones(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		final String METHODNAME = "lPromociones - ";
		logger.debug(METHODNAME + "INI");
		try {

			String err = request.getParameter("err");
			if (!StringUtils.isEmpty(err)) {
				if (err.equals("1")) {
					model.put("msgError", "No se han encontrado resultados");
				}
			}
			String msg = request.getParameter("msg");
			if (!StringUtils.isEmpty(msg)) {
				if (msg.equals("1")) {
					model.put("mensaje", "Promocion actualizada");
				}
			}

			model.put("factura", new Factura());
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Error: " + e.getMessage());
		} finally {
			logger.debug(METHODNAME + "FIN");
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
	@RequestMapping(value = "/lPromociones.htm", method = RequestMethod.POST)
	public String lPromocionesPost(@Valid Factura factura, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		final String METHODNAME = "lPromocionesPost - ";
		logger.debug(METHODNAME + "INI");
		try {

			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");

			// facturas que tengan registrado fidelizacion
			logger.debug(METHODNAME + "ruc =" + factura.toString() );

			Fideliza fi = fidelizaService.obtenerFidelizacionPorCliente(factura);
			if (fi != null) {
				logger.debug(METHODNAME + "idFideliza=" + fi.toString());
				return "redirect:/fidelizar/promociones.htm?idFideliza=" + fi.getIdFideliza();
			}
			return "redirect:/fidelizar/lPromociones.htm?err=1";
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Error: " + e.getMessage());
			return "redirect:/fidelizar/lPromociones.htm?err=1";
		} finally {
			logger.debug(METHODNAME + "FIN");
		}
	}
	
	@RequestMapping(value="/promociones.htm", method=RequestMethod.GET)
	public String preGrabarPromociones(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		final String METHODNAME = "preGrabarPromociones - ";
		try {
			logger.debug(METHODNAME + "INI");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			   
			Integer idFideliza = Integer.parseInt( request.getParameter("idFideliza") );
			logger.debug(METHODNAME + "idFideliza = " + idFideliza);
			Fideliza fi = fidelizaService.obtenerFidelizacion( new Long(idFideliza) ); 
			logger.debug(METHODNAME + "Fideliza = " + fi);
			Factura f = fi.getReclamo().getFactura();
			logger.debug(METHODNAME + "Factura = " + f);
			ItemsReclamo ir = reclamoService.obtenerItemReclamo( fi.getReclamo().getIdReclamo() );
			logger.debug(METHODNAME + "ItemsReclamo = " + ir );
			model.put("fideliza", fi );
			model.put("factura", f );
			model.put("producto", ir );
			
			//Lista de promociones
			model.put("lPromociones", promocionService.listarPromociones() );
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Error: "+ e.getMessage() );
		}
		logger.debug(METHODNAME + "FIN");
		return "fidelizar/promocion";
	}
	
	@RequestMapping(value="/promociones.htm", method=RequestMethod.POST)
	public String grabarPromociones(@Valid Fideliza fideliza, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		final String METHODNAME = "grabarPromociones - ";
		try {
			logger.debug(METHODNAME + "INI");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			logger.debug(METHODNAME + "promo " + fideliza.getPromocion().getIdPromocion() );
			Fideliza fi = fidelizaService.obtenerFidelizacion( new Long( fideliza.getIdFideliza() ) );
			fi.setPromocion( fideliza.getPromocion() );
			fi.setUpdatedAt( new Date());
			fidelizaService.actualizarPromocion( fi );
			
			Cliente cli = clienteService.obtener(fi.getReclamo().getIdCliente() );
			logger.debug(METHODNAME + "cliente = " + cli.getPersona().getEmail() );
			if( !StringUtils.isEmpty( cli.getPersona().getEmail() ) ){
						
				Promocion pr = promocionService.obtenerPromocion( fideliza.getPromocion() );
				String mensaje = "Se le ha asignado la siguiente promocion: <br>";
				mensaje += " " + pr.getDescripcion() + " <br> ";
				mail.sendMail( "admin@dvertat.com", cli.getPersona().getEmail(), null, "Promocion", mensaje);
				logger.debug(METHODNAME + "mail enviado " );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Error: "+ e.getMessage() );
		}
		logger.debug(METHODNAME + "FIN");
		return "redirect:/fidelizar/lPromociones.htm?msg=1";
	}
	
	
}
