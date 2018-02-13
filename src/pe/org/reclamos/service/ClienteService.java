package pe.org.reclamos.service;

import java.util.List;

import pe.org.reclamos.entidad.Cliente;

public interface ClienteService {

	public void registrar(Cliente cliente);
	public Cliente obtener(Long cliente);
	public Cliente obtenerPorRUC(String ruc);
	public List<Cliente> buscar(Cliente cliente);
	public void eliminar(Long cliente);
	public List<Cliente> buscarClientesParaFidelizacion(String ruc); 
	
}