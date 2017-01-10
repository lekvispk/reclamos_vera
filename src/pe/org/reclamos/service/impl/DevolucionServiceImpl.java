package pe.org.reclamos.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.DevolucionDAO;
import pe.org.reclamos.entidad.Despachador;
import pe.org.reclamos.entidad.DetalleDevolucion;
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

	@Override
	public Devolucion obtenerPorDetalleFactura(Integer idDetalleFactura) {
		return devolucionDAO.obtenerPorDetalleFactura(idDetalleFactura);
	}

	@Override
	public void grabarDetalle(DetalleDevolucion dd) {
		if( dd.getIdDetalleDevolucion() >0 ){
			dd.setUpdatedAt( new Date() );
		}else{
			dd.setEstado( 1 );
			dd.setCreatedAt( new Date() );
		}
		devolucionDAO.grabarDetalle(dd);
	}

	@Override
	public List<DetalleDevolucion> listarDetalleDevolucion(Integer iddevolucion) {
		return devolucionDAO.listarDetalleDevolucion(iddevolucion);
	}

	@Override
	public List<DetalleDevolucion> listarProductosParaDevolver() {
		return devolucionDAO.listarProductosParaDevolver( );
	}

	@Override
	public List<Despachador> listarDespachadores() {
		return devolucionDAO.listarDespachadores();
	}

}
