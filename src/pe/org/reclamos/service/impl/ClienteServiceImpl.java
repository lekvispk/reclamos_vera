package pe.org.reclamos.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.ClienteDAO;
import pe.org.reclamos.entidad.Cliente;
import pe.org.reclamos.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private static final Logger logger = Logger.getLogger(ClienteServiceImpl.class );
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Override
	public void registrar(Cliente cliente) {
		final String METHODNAME = "registrar - ";
		logger.debug(METHODNAME + "INI");
		if( cliente.getIdCliente()==null ){
			logger.debug(METHODNAME + "CLIETNE NUEV");
			cliente.setFecCliente( new Date() );
			cliente.setEstado( 1 );
			
			cliente.getPersona().setEstado(1);
			cliente.getPersona().setCreatedAt( new Date() );
			
		}else{
			logger.debug(METHODNAME + "CLIETNE MODIFICADO");
			cliente.getPersona().setUpdatedAt( new Date() );
		}
		clienteDAO.registrar(cliente);
	}

	@Override
	public Cliente obtener(Long cliente) {
		return clienteDAO.obtener(cliente);
	}

	@Override
	public List<Cliente> buscar(Cliente cliente) {
		return clienteDAO.buscar(cliente);
	}

	@Override
	public void eliminar(Long cliente) {
		clienteDAO.eliminar(cliente);
	}

	@Override
	public List<Cliente> buscarClientesParaFidelizacion(String ruc) {
		return clienteDAO.buscarClientesParaFidelizacion( ruc );
	}

	@Override
	public Cliente obtenerPorRUC(String ruc) {
		return clienteDAO.obtenerPorRUC(ruc);
	}

}
