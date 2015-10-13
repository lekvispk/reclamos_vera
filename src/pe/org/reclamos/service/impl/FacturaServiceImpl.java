package pe.org.reclamos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.FacturaDAO;
import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaDAO facturaDAO;
	
	@Override
	public void registrar(Factura cliente) {
		facturaDAO.registrar(cliente);
	}

	@Override
	public Factura obtener(Long cliente) {
		return facturaDAO.obtener(cliente);
	}

	@Override
	public List<Factura> buscar(Factura cliente) {
		return facturaDAO.buscar(cliente);
	}

	@Override
	public void eliminar(Long cliente) {
		facturaDAO.eliminar(cliente);
	}

}
