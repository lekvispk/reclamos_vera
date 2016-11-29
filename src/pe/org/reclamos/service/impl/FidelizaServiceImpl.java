package pe.org.reclamos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.FidelizaDAO;
import pe.org.reclamos.entidad.Factura;
import pe.org.reclamos.entidad.Fideliza;
import pe.org.reclamos.service.FidelizaService;

@Service
public class FidelizaServiceImpl implements FidelizaService {

	@Autowired
	private FidelizaDAO fidelizaDAO;
	
	@Override
	public void registrarFidelizacion(Fideliza fideliza) {
		fidelizaDAO.registrarFidelizacion(fideliza);
	}

	@Override
	public Fideliza obtenerFidelizacionPorReclamo(Long idReclamo) {
		return fidelizaDAO.obtenerFidelizacionPorReclamo(idReclamo);
	}

	/**
	 * Obtener la fidelizacion del cliente en base a los datos
	 */
	@Override
	public Fideliza obtenerFidelizacionPorCliente(Factura factura) {
		return fidelizaDAO.obtenerFidelizacionPorReclamo(factura);
	}

	@Override
	public Fideliza obtenerFidelizacion(Long idFidelizacion) {
		return fidelizaDAO.obtenerFidelizacion(idFidelizacion);
	}

}
