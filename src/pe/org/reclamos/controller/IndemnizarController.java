package pe.org.reclamos.controller;

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

import pe.org.reclamos.entidad.Indemnizacion;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.service.ReclamoService;

@Controller
@RequestMapping(value="/indemnizar")
public class IndemnizarController {

	private static final Logger logger = Logger.getLogger(IndemnizarController.class );
	
	@Autowired
	private ReclamoService reclamoService;
	
	@RequestMapping(value="/lIndemnizar.htm", method=RequestMethod.GET)
	public String lIndemnizar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   Reclamo reclamo = new Reclamo();
			   reclamo.getEstados().add(2);
			   reclamo.getEstados().add(3);
			   
			   model.put("lReclamos", reclamoService.buscar( reclamo ));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   model.put("reclamo", new Reclamo() );
		   }
		return "indemnizar/lIndemnizar";
	}

	@RequestMapping(value="/lIndemnizar.htm", method=RequestMethod.POST)
	public String lBuscar(@Valid Reclamo reclamo, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   reclamo.setEstado( 2 );
			   model.put("lReclamos", reclamoService.buscar( reclamo ));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   model.put("reclamo", reclamo );
		   }
		return "indemnizar/lIndemnizar";
	}

	
	@RequestMapping(value="/indemnizar.htm" , method=RequestMethod.GET)
	public String preIndemnizar( HttpServletRequest request,ModelMap model){
		logger.debug("pre indemnizar ");
		Reclamo rec = new Reclamo();
		try {
			rec = reclamoService.obtener( new Long ( request.getParameter("idReclamo")));	
		} catch (Exception e) {
			logger.error(" ERROR: " + e.getMessage() );
		}finally{
			model.put("reclamo", rec);
		}
		//model.put("lTipoTram", parametroService.listarGrupo( ParametrosUtil.PARAM_GROUP_TIPOTRAM) );
		return "indemnizar/indemnizar";
	}
	
	@RequestMapping(value="/indemnizar.htm" , method=RequestMethod.POST)  
	 public String indemnizar(@Valid Reclamo reclamo, BindingResult result, HttpServletRequest request,  HttpServletResponse response, ModelMap model) {  
		try {
			logger.debug("indemnizar");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			Reclamo rec = reclamoService.obtener( reclamo.getIdReclamo() );
			rec.setIndemnizar( reclamo.getIndemnizar());
			rec.setEstado( 3 );
			
			Indemnizacion inm = new Indemnizacion();
			inm.setTotalIndemnizacion( 0.0 );
			rec.setIndemnizacion( inm );
			
			reclamoService.registrarIndemnizacion(rec);
			
			model.put("mensaje","Se ha grabado satisfactoriamente");
			return "redirect:/indemnizar/lIndemnizar.htm";
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Se han producido errores, por favor verifique: "+e.getMessage() );
			return "indemnizar/indemnizar";
		}finally{
			model.put("reclamo", reclamo);
		}
	 }  
	
	@RequestMapping(value="/noIndemnizar.htm" , method=RequestMethod.POST)  
	 public String niIndemnizar(HttpServletRequest request,  HttpServletResponse response, ModelMap model) {  
		try {
			logger.debug("niIndemnizar");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			String ids = request.getParameter("id_reclamos");
			logger.debug("Ids para no indemnizar " + ids);
			String[] idReclamos = ids.split("_");
			reclamoService.grabarNoIndemnizados(idReclamos);
			
			return "redirect:/indemnizar/lIndemnizar.htm";
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Se han producido errores, por favor verifique: "+e.getMessage() );
			return "indemnizar/lIndemnizar";
		}finally{
			model.put("reclamo", new Reclamo());
		}
	 }
}
