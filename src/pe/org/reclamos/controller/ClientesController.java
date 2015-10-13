package pe.org.reclamos.controller;

import java.util.ArrayList;
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
import pe.org.reclamos.service.ClienteService;

@Controller
@RequestMapping(value="/clientes")
public class ClientesController {

	private static final Logger logger = Logger.getLogger(ClientesController.class );

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/lista.htm")
	public String listar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		 try {
			   logger.debug("lista");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			  
			   model.put("lClientes", clienteService.buscar( new Cliente() ));
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			  // model.put("lTipoMov", parametroService.listarGrupo( ParametrosUtil.PARAM_GROUP_TIPOMOVTRAM ) );
			  // model.put("lTipoTram", parametroService.listarGrupo( ParametrosUtil.PARAM_GROUP_TIPOTRAM) );
			  // model.put("tramite", tramite );
		   }
		return "clientes/lista";
	}
	
	@RequestMapping(value="/registro.htm" , method=RequestMethod.GET)
	public String preNuevoCliente( ModelMap model){
		logger.debug("pre registrar cliente");
		model.put("cliente", new Cliente());
		//model.put("lTipoTram", parametroService.listarGrupo( ParametrosUtil.PARAM_GROUP_TIPOTRAM) );
		return "clientes/registro";
	}
	
	@RequestMapping(value="/registro.htm" , method=RequestMethod.POST)  
	 public String nuevoCliente(@Valid Cliente cliente, BindingResult result, HttpServletRequest request,  HttpServletResponse response, ModelMap model) {  
		try {
			logger.debug("grabar un cliente nuevo");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			clienteService.registrar(cliente);
			
			model.put("mensaje","Se ha grabado satisfactoriamente");
			return listar(request, response, model);
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Se han producido errores, por favor verifique: "+e.getMessage() );
			return "clientes/registro";
		}finally{
			model.put("cliente", cliente);
		}
	 }  
	
	
}
