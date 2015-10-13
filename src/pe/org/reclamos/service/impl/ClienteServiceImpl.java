package pe.org.reclamos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.ClienteDAO;
import pe.org.reclamos.entidad.Cliente;
import pe.org.reclamos.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;
	
	@Override
	public void registrar(Cliente cliente) {
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

}
