package pe.org.reclamos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.service.FacturaService;
import pe.org.reclamos.service.ReclamoService;

@Controller
@RequestMapping(value="/seguimiento")
@Scope("session")
public class SeguimientoController {

	private static final Logger logger = Logger.getLogger(SeguimientoController.class );
	
	@Autowired
	private ReclamoService reclamoService;
	@Autowired
	private FacturaService facturaService;
	
	@RequestMapping(value="/lista.htm")
	public String listar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			  
			   Reclamo reclamo = new Reclamo();
			   //TODO pendiente modificar para que la paginacion funcione
			   model.put("lReclamos", reclamoService.buscar(reclamo));
			   
			   //model.put("lClientes", clienteService.buscar( new Cliente() ));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			  // model.put("lTipoMov", parametroService.listarGrupo( ParametrosUtil.PARAM_GROUP_TIPOMOVTRAM ) );
			  // model.put("lTipoTram", parametroService.listarGrupo( ParametrosUtil.PARAM_GROUP_TIPOTRAM) );
			  // model.put("tramite", tramite );
		   }
		return "seguimiento/lista";
	}
	
	@RequestMapping(value="/ver.htm" , method=RequestMethod.GET)
	public String preModificar(HttpServletRequest request, ModelMap model){
		logger.debug("ver detalle");
		
		Long idReclamo = new Long( request.getParameter("idReclamo") );
		model.put("reclamo", reclamoService.obtener( idReclamo ));
		
		return "seguimiento/reclamo";
	}
	
}
