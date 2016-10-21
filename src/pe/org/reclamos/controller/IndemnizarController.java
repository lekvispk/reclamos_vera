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

import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.service.ReclamoService;

@Controller
@RequestMapping(value="/indemnizar")
public class IndemnizarController {

	private static final Logger logger = Logger.getLogger(IndemnizarController.class );
	
	@Autowired
	private ReclamoService reclamoService;
	
	@RequestMapping(value="/lIndemnizar.htm")
	public String lIndemnizar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			   
			   model.put("lReclamos", reclamoService.buscar( new Reclamo() ));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   model.put("reclamo", new Reclamo() );
		   }
		return "indemnizar/lIndemnizar";
	}

	@RequestMapping(value="/indemnizar.htm" , method=RequestMethod.GET)
	public String preIndemnizar( ModelMap model){
		logger.debug("pre indemnizar ");
		model.put("reclamo", new Reclamo());
		//model.put("lTipoTram", parametroService.listarGrupo( ParametrosUtil.PARAM_GROUP_TIPOTRAM) );
		return "indemnizar/indemnizar";
	}
	
	@RequestMapping(value="/indemnizar.htm" , method=RequestMethod.POST)  
	 public String indemnizar(@Valid Reclamo reclamo, BindingResult result, HttpServletRequest request,  HttpServletResponse response, ModelMap model) {  
		try {
			logger.debug("indemnizar");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			model.put("mensaje","Se ha grabado satisfactoriamente");
			return "indemnizar/lIndemnizar";
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Se han producido errores, por favor verifique: "+e.getMessage() );
			return "indemnizar/indemnizar";
		}finally{
			model.put("reclamo", reclamo);
		}
	 }  
	
}