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

import pe.org.reclamos.entidad.Cliente;
import pe.org.reclamos.service.ClienteService;

@Controller
@RequestMapping(value="/clientes")
public class ClientesController {

	private static final Logger logger = Logger.getLogger(ClientesController.class );

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/lista.htm" ,  method=RequestMethod.GET)
	public String listar( HttpServletRequest request, HttpServletResponse response, ModelMap model){
		final String METHODNAME = "listar - ";
		 try {
			   logger.debug( METHODNAME + "INI");
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			  
			   model.put("lClientes", clienteService.buscar( new Cliente() ));
			   model.put("cliente", new Cliente() );
			   
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   logger.debug( METHODNAME + "FIN");
		   }
		return "clientes/lista";
	}
	
	@RequestMapping(value="/lista.htm" ,  method=RequestMethod.POST)
	public String listarPost(@Valid Cliente cliente, BindingResult result,  HttpServletRequest request, HttpServletResponse response, ModelMap model){
		final String METHODNAME = "listarPost - ";
		logger.debug( METHODNAME + "INI");
		
		try {
			   response.setContentType("text/html;charset=ISO-8859-1");
			   request.setCharacterEncoding("UTF8");
			  
			   model.put("lClientes", clienteService.buscar( cliente ));
			   model.put("cliente", cliente );
		   } catch (Exception e) {
			 e.printStackTrace();
			 model.put("msgError", "Error: "+ e.getMessage() );
		   }finally{
			   logger.debug( METHODNAME + "FIN");
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
			
			logger.debug("Persona =  " + cliente.getPersona() );
			logger.debug("Cliente "  + cliente );
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
	
	@RequestMapping(value="/modificar.htm" , method=RequestMethod.GET)
	public String preModificarCliente( ModelMap model, HttpServletRequest request){
		logger.debug("pre modificar cliente");
		Cliente cli = clienteService.obtener( new Long(request.getParameter("idCliente")) );
		model.put("cliente", cli );
		return "clientes/registro";
	}
	
	@RequestMapping(value="/eliminar.htm" , method=RequestMethod.GET)
	public String eliminarCliente( ModelMap model, HttpServletRequest request){
		logger.debug("eliminmar cliente");
		clienteService.eliminar( new Long(request.getParameter("idCliente")) );
		return "redirect:/clientes/lista.htm";
	}
	
}
