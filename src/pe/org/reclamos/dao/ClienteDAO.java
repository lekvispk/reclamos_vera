package pe.org.reclamos.dao;

import java.util.List;

import pe.org.reclamos.entidad.Cliente;

public interface ClienteDAO {

	public void registrar(Cliente cliente);
	public Cliente obtener(Long cliente);
	public List<Cliente> buscar(Cliente cliente);
	public void eliminar(Long cliente);
	public List<Cliente> buscarClientesParaFidelizacion(String ruc);
	public Cliente obtenerPorRUC(String ruc);
	
}