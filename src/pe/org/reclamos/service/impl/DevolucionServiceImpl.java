package pe.org.reclamos.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.DevolucionDAO;
import pe.org.reclamos.entidad.Devolucion;
import pe.org.reclamos.service.DevolucionService;

@Service
public class DevolucionServiceImpl implements DevolucionService {

	@Autowired
	private DevolucionDAO devolucionDAO;
	
	@Override
	public Devolucion obtenerPorReclamo(Integer idReclamo) {
		return devolucionDAO.obtenerPorReclamo(idReclamo);
	}

	@Override
	public Devolucion obtener(Integer idDevolucion) {
		return devolucionDAO.obtener(idDevolucion);
	}

	@Override
	public void grabar(Devolucion devolucion) {
		if(devolucion.getIdDevolucion()!=null ){
			devolucion.setUpdatedAt( new Date() );
		}else{
			devolucion.setEstado(1);
			devolucion.setCreatedAt( new Date() );
		}
		devolucionDAO.grabar(devolucion);
	}

}
