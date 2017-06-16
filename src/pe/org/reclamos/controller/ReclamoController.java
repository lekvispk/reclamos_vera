package pe.org.reclamos.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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

import pe.org.reclamos.entidad.Cliente;
import pe.org.reclamos.entidad.Detallefactura;
import pe.org.reclamos.entidad.ItemsReclamo;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.service.ClienteService;
import pe.org.reclamos.service.ReclamoService;
import pe.org.reclamos.utiles.Constantes;
import pe.org.reclamos.utiles.Utiles;

@Controller
@RequestMapping(value="/reclamos")
public class ReclamoController {

	private static final Logger logger = Logger.getLogger(ReclamoController.class );

	@Autowired
	private ReclamoService reclamoService;
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/lGestionar.htm", method=RequestMethod.GET)
	public String lGestionar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		final String METHODNAME = "lGestionar - ";
		 try {
			   logger.debug(METHODNAME + "INI");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lReclamos", reclamoService.buscar( new Reclamo() ));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			  model.put("reclamo", new Reclamo() );
		   }
		 logger.debug(METHODNAME + "FIN");
		return "reclamos/lGestionar";
	}
	
	@RequestMapping(value="/lGestionar.htm", method=RequestMethod.POST)
	public String lGestionarPost(@Valid Reclamo reclamo, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lGestionar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   reclamo.setVencimiento( Utiles.stringToDate( request.getParameter("vencimiento") , "dd/MM/yyyy") );
			   model.put("lReclamos", reclamoService.buscar( reclamo ));
			   
		   } catch (Exception e) {
			   e.printStackTrace();
			 logger.error( "ERROR: " + e.getMessage() );
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			  // model.put("lTipoMov", parametroService.listarGrupo( ParametrosUtil.PARAM_GROUP_TIPOMOVTRAM ) );
			  // model.put("lTipoTram", parametroService.listarGrupo( ParametrosUtil.PARAM_GROUP_TIPOTRAM) );
			  model.put("reclamo", reclamo );
		   }
		return "reclamos/lGestionar";
	}
	
	@RequestMapping(value="/eliminarReclamo.htm")
	public String eliminar( HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("eliminar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   Long idReclamo = new Long( request.getParameter("idReclamo") );
			   reclamoService.eliminar( idReclamo );
			   
			  lGestionar(request, response, model);
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }
		return "reclamos/lGestionar";
	}
	
	
	@RequestMapping(value="/lEvaluar.htm",method=RequestMethod.GET)
	public String lEvaluar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lEvaluar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   Reclamo rec = new Reclamo(); 
			   rec.setEstado(1);
			   rec.setRespuesta(null);
			   
			   model.put("lReclamos", reclamoService.buscar( rec));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   model.put("reclamo", new Reclamo() );
		   }
		return "reclamos/lEvaluar";
	}
	
	@RequestMapping(value="/lEvaluar.htm", method=RequestMethod.POST)
	public String lEvaluarPost(@Valid Reclamo reclamo, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lEvaluar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   reclamo.setVencimiento( Utiles.stringToDate( request.getParameter("vencimiento") , "dd/MM/yyyy") );
			   reclamo.setEstado(1);
			   model.put("lReclamos", reclamoService.buscar( reclamo ));
			   
		   } catch (Exception e) {
			 logger.error(" ERROR : " + e.getMessage() );
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			  model.put("reclamo", reclamo );
		   }
		return "reclamos/lEvaluar";
	}
	
	@RequestMapping(value="/evaluar.htm" , method=RequestMethod.GET)
	public String preEvaluar( ModelMap model , HttpServletRequest request){
		logger.debug("pre evaluar ");
		
		String idReclamo = request.getParameter("idReclamo");
		Reclamo rec =  reclamoService.obtener( new Long(idReclamo)) ;
		model.put("reclamo", rec );
		
		return "reclamos/evaluar";
	}
	
	@RequestMapping(value="/evaluar.htm" , method=RequestMethod.POST)  
	 public String evaluar(@Valid Reclamo reclamo, BindingResult result, HttpServletRequest request,  HttpServletResponse response, ModelMap model) {  
		try {
			logger.debug("evaluar");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			Reclamo rec = reclamoService.obtener( reclamo.getIdReclamo() );
			//rec.setEstado( 2 );
			rec.setRespuesta( reclamo.getRespuesta() );
			rec.setFecRespuesta( new Date() );
			rec.setDescripcion( reclamo.getDescripcion() );
			
			reclamoService.actualizar(rec);
			
			model.put("mensaje","Se ha grabado satisfactoriamente");
			return lEvaluar(request, response, model);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Se han producido errores, por favor verifique: "+e.getMessage() );
			return "reclamos/evaluar";
		}finally{
			model.put("reclamo", reclamo);
		}
	 }  
	
	@RequestMapping(value="/lResolucion.htm")
	public String lResolucion(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");

			   Reclamo rec = new Reclamo(); 
			   //TODO mostrar los que estan en estado 2 y 3 
			  /// rec.setEstado(2);
			  
			   model.put("lReclamos", reclamoService.buscar( rec));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   model.put("reclamo", new Reclamo() );
		   }
		return "reclamos/lResolucion";
	}
	
	@RequestMapping(value="/lSolucionar.htm", method=RequestMethod.GET)
	public String lSolucionar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lSolucionar");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");

			   Reclamo rec = new Reclamo(); 
			   rec.setEstado(1);
			   rec.setRespuesta(Constantes.RECLAMO_RESPUESTA_ACEPTADO);
			   //ver estados Aceptado y Solucionado
			  
			   model.put("lReclamos", reclamoService.buscar( rec));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   model.put("reclamo", new Reclamo() );
		   }
		return "reclamos/lSolucionar";
	}
	
	//para el buscar
	@RequestMapping(value="/lSolucionar.htm", method=RequestMethod.POST)
	public String lSolucionarPost(@Valid Reclamo reclamo, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lSolucionarPost");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   reclamo.setVencimiento( Utiles.stringToDate( request.getParameter("vencimiento") , "dd/MM/yyyy") );
			   reclamo.setEstado(2);
			   model.put("lReclamos", reclamoService.buscar( reclamo ));
			   
		   } catch (Exception e) {
			 logger.error(" ERROR: " + e.getMessage());
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			  model.put("reclamo", reclamo );
		   }
		return "reclamos/lSolucionar";
	}
	
	@RequestMapping(value="/solucionar.htm" , method=RequestMethod.GET)
	public String preSolucionar( ModelMap model , HttpServletRequest request){
		logger.debug("pre solucionar ");
		
		String idReclamo = request.getParameter("idReclamo");
		Reclamo rec =  reclamoService.obtener( new Long(idReclamo)) ;
		model.put("reclamo", rec );
		
		return "reclamos/solucion";
	}
	
	@RequestMapping(value="/solucionar.htm" , method=RequestMethod.POST)  
	 public String solucionar(@Valid Reclamo reclamo, BindingResult result, HttpServletRequest request,  HttpServletResponse response, ModelMap model) {  
		try {
			logger.debug("solucionar");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			Reclamo rec = reclamoService.obtener( reclamo.getIdReclamo() );
			rec.setEstado( 2 );
			rec.setSolucion( reclamo.getSolucion() );
			reclamoService.actualizar(rec);
			
			model.put("mensaje","La solucion se ha grabado satisfactoriamente");
			return lSolucionar(request, response, model);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Se han producido errores, por favor verifique: "+e.getMessage() );
			return "reclamos/solucion";
		}finally{
			model.put("reclamo", reclamo);
		}
	 }  
	
	@RequestMapping(value="/registro.htm" , method=RequestMethod.GET)
	public String preNuevo( ModelMap model){
		logger.debug("pre registrar ");
		model.put("reclamo", new Reclamo());
		//model.put("lTipoTram", parametroService.listarGrupo( ParametrosUtil.PARAM_GROUP_TIPOTRAM) );
		return "reclamos/registro";
	}
	
	@RequestMapping(value="/modificar.htm" , method=RequestMethod.GET)
	public String preModificar(HttpServletRequest request, ModelMap model){
		logger.debug("pre modificar ");
		
		Long idReclamo = new Long( request.getParameter("idReclamo") );
		model.put("reclamo", reclamoService.obtener( idReclamo ));
		
		return "reclamos/registro";
	}
	
	@RequestMapping(value="/registro.htm" , method=RequestMethod.POST)  
	 public String nuevo(@Valid Reclamo reclamo, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model /*,  RedirectAttributes redirectAttrs*/ ) {  
		try {
			logger.debug("grabar nuevo");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			logger.debug("Factura " + reclamo.getFactura() ) ;
			
			Integer idDetalleFactura = Integer.parseInt( request.getParameter("rb_item") );
			ItemsReclamo ir = new ItemsReclamo();
			ir.setDetallefactura( new Detallefactura() );
			ir.getDetallefactura().setIdDetalleFactura(idDetalleFactura);
			reclamo.setItemsReclamos( new HashSet<ItemsReclamo>()); 
			reclamo.getItemsReclamos().add( ir );
			reclamoService.registrar(reclamo);
			
			request.setAttribute("mensaje","Se ha grabado satisfactoriamente");
			
			return "redirect:/reclamos/lGestionar.htm";
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Se han producido errores, por favor verifique: "+e.getMessage() );
			return "reclamos/registro";
		}finally{
			model.put("reclamo", reclamo);
		}
	 }  
	
	@RequestMapping(value="/lClienteAuto.htm")
	public String lpersonaAuto(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		PrintWriter out = null;
		try {
			//Para mostrar caracteres raros
			response.setContentType("text/html;charset=ISO-8859-1");
	        request.setCharacterEncoding("UTF8");	        
			
	        out = response.getWriter();
			String para = request.getParameter("queryString");
			if(para==null)
				para = Utiles.toUtf8( Utiles.nullToBlank( request.getParameter("term") ) );
			
			Cliente e = new Cliente();
			e.setRucCliente(  para  );
			if( Utiles.nullToBlank( para ).length() > 0){
				List<Cliente> lista = clienteService.buscar(e);
				logger.debug("Encontrados : " + lista.size() );
				out.println("[");
				int i = 0 ; 
				for(Cliente per : lista ){
					if( i != 0 ){out.print(","); } i++;
					out.println("{ \"id\": \""+per.getIdCliente()+"\", \"label\": \""+per.getRucCliente().replaceAll("\"", "'")+"\", \"value\": \""+per.getRucCliente().replaceAll("\"", "'")+"\" }");
				}
				out.println("]");
			}
			logger.debug("autocomplete");
		} catch (Exception e) {
			model.put("msgError", e.getMessage());
			e.printStackTrace();
		} finally {
            out.close();
        }
		return null;
	}
	
	
}
